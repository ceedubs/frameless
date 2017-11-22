package frameless
package ml

import frameless.ops.SmartProject
import org.apache.spark.ml.{Estimator, Model}

/**
  * A TypedEstimator `fit` method takes as input a TypedDataset containing `Inputs` and
  * return an AppendTransformer with `Inputs` as inputs and `Outputs` as outputs
  */
trait TypedEstimator[Inputs, Outputs, M <: Model[M]] {
  val estimator: Estimator[M]

  def fit[T, F[_]](ds: TypedDataset[T])(
    implicit
    smartProject: SmartProject[T, Inputs],
    F: SparkDelay[F]
  ): F[AppendTransformer[Inputs, Outputs, M]] = {
    implicit val sparkSession = ds.dataset.sparkSession
    F.delay {
      val inputDs = smartProject.apply(ds)
      val model = estimator.fit(inputDs.dataset)
      new AppendTransformer[Inputs, Outputs, M] {
        val transformer: M = model
      }
    }
  }
}
