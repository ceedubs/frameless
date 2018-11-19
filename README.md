# Frameless

[![Travis Badge](https://travis-ci.org/typelevel/frameless.svg?branch=master)](https://travis-ci.org/typelevel/frameless)
[![Codecov Badge](https://codecov.io/gh/typelevel/frameless/branch/master/graph/badge.svg)](https://codecov.io/gh/typelevel/frameless)
[![Maven Badge](https://img.shields.io/maven-central/v/org.typelevel/frameless-dataset_2.11.svg)](https://maven-badges.herokuapp.com/maven-central/org.typelevel/frameless-dataset_2.11)
[![Gitter Badge](https://badges.gitter.im/typelevel/frameless.svg)](https://gitter.im/typelevel/frameless)

Frameless is a Scala library for working with [Spark](http://spark.apache.org/) using more expressive types. 
It consists of the following modules:

* `frameless-dataset` for a more strongly typed `Dataset`/`DataFrame` API   
* `frameless-ml` for a more strongly typed Spark ML API based on `frameless-dataset`
* `frameless-cats` for using Spark's `RDD` API with [cats](https://github.com/typelevel/cats)

Note that while Frameless is still getting off the ground, it is very possible that breaking changes will be
made for at least the next few versions.

The Frameless project and contributors support the
[Typelevel](http://typelevel.org/) [Code of Conduct](http://typelevel.org/conduct.html) and want all its
associated channels (e.g. GitHub, Gitter) to be a safe and friendly environment for contributing and learning.


## Versions and dependencies

The compatible versions of [Spark](http://spark.apache.org/) and 
[cats](https://github.com/typelevel/cats) are as follows:   

| Frameless  | Spark | Cats | Cats-Effect
| --- | --- | --- | --- |
| 0.4.0  | 2.2.0  | 1.0.0-IF | 0.4
| 0.4.1  | 2.2.0  | 1.x | 0.8
| 0.5.2  | 2.2.1  | 1.x | 0.8
| 0.6.1  | 2.3.0  | 1.x | 0.8

Versions 0.5.x and 0.6.x have identical features. The first is compatible with Spark 2.2.1 and the second with 2.3.0. 

The **only** dependency of the `frameless-dataset` module is on [shapeless](https://github.com/milessabin/shapeless) 2.3.2. 
Therefore, depending on `frameless-dataset`, has a minimal overhead on your Spark's application jar. 
Only the `frameless-cats` module depends on cats and cats-effect, so if you prefer to work just with `Datasets` and not with `RDD`s, 
you may choose not to depend on `frameless-cats`. 

Frameless intentionally **does not** have a compile dependency on Spark. 
This essentially allows you to use any version of Frameless with any version of Spark. 
The aforementioned table simply provides the versions of Spark we officially compile 
and test Frameless with, but other versions may probably work as well. 

## Why?

Frameless introduces a new Spark API, called `TypedDataset`. 
The benefits of using `TypedDataset` compared to the standard Spark `Dataset` API are as follows:

* Typesafe columns referencing (e.g., no more runtime errors when accessing non-existing columns)
* Customizable, typesafe encoders (e.g., if a type does not have an encoder, it should not compile) 
* Enhanced type signature for built-in functions (e.g., if you apply an arithmetic operation on a non-numeric column, you 
get a compilation error)
* Typesafe casting and projections

Click [here](http://typelevel.org/frameless/TypedDatasetVsSparkDataset.html) for a 
detailed comparison of `TypedDataset` with Spark's `Dataset` API. 

## Documentation

* [TypedDataset: Feature Overview](http://typelevel.org/frameless/FeatureOverview.html)
* [Typed Spark ML](http://typelevel.org/frameless/TypedML.html)
* [Comparing TypedDatasets with Spark's Datasets](http://typelevel.org/frameless/TypedDatasetVsSparkDataset.html)
* [Typed Encoders in Frameless](http://typelevel.org/frameless/TypedEncoder.html)
* [Injection: Creating Custom Encoders](http://typelevel.org/frameless/Injection.html)
* [Job\[A\]](http://typelevel.org/frameless/Job.html)
* [Using Cats with RDDs](http://typelevel.org/frameless/Cats.html)
* [Proof of Concept: TypedDataFrame](http://typelevel.org/frameless/TypedDataFrame.html)

## Quick Start
Frameless is compiled against Scala 2.11.x.

To use Frameless in your project add the following in your `build.sbt` file as needed:

```scala
val framelessVersion = "0.6.1" // for Spark 2.3.0 or use 0.5.2 for Spark 2.2.1

libraryDependencies ++= List(
  "org.typelevel" %% "frameless-dataset" % framelessVersion,
  "org.typelevel" %% "frameless-ml"      % framelessVersion,
  "org.typelevel" %% "frameless-cats"    % framelessVersion  
)
```

An easy way to bootstrap a Frameless sbt project:

- if you have [Giter8][g8] installed then simply:

```bash
g8 imarios/frameless.g8
```
- with sbt >= 0.13.13:

```bash
sbt new imarios/frameless.g8
```

Typing `sbt console` inside your project will bring up a shell with Frameless
and all its dependencies loaded (including Spark).

## Need help?

Feel free to messages us on our [gitter](https://gitter.im/typelevel/frameless) 
channel for any issues/questions.


## Development
We require at least *one* sign-off (thumbs-up, +1, or similar) to merge pull requests. The current maintainers
(people who can merge pull requests) are:

* [adelbertc](https://github.com/adelbertc)
* [imarios](https://github.com/imarios)
* [jeremyrsmith](https://github.com/jeremyrsmith)
* [kanterov](https://github.com/kanterov)
* [non](https://github.com/non)
* [OlivierBlanvillain](https://github.com/OlivierBlanvillain/)

### Testing

Frameless contains several property tests.  To avoid `OutOfMemoryError`s, we
tune the default generator sizes.  The following environment variables may
be set to adjust the size of generated collections in the `TypedDataSet` suite:

| Property                    | Default |
|-----------------------------|--------:|
| FRAMELESS_GEN_MIN_SIZE      |       0 |
| FRAMELESS_GEN_SIZE_RANGE    |      20 |

## License
Code is provided under the Apache 2.0 license available at http://opensource.org/licenses/Apache-2.0,
as well as in the LICENSE file. This is the same license used as Spark.

[g8]: http://www.foundweekends.org/giter8/
