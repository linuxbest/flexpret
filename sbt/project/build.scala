import sbt._
import Keys._

object BuildSettings {
  val buildOrganization = "edu.berkeley.cs"
  val buildVersion = "1.0"
  val buildScalaVersion = "2.10.3"

  def apply(sourcePath: String) = {
    Defaults.defaultSettings ++ Seq (
      organization := buildOrganization,
      version := buildVersion,
      scalaVersion := buildScalaVersion,
      scalaSource in Compile := Path.absolute(file(sourcePath)),
      libraryDependencies += "edu.berkeley.cs" %% "chisel" % "latest.release"
    )
  }
}

object ChiselBuild extends Build {
   import BuildSettings._
   lazy val common = Project("common", file ("common"), settings = BuildSettings("../src/common"))
   lazy val Core = Project("Core", file("Core"), settings = BuildSettings("../src/Core")) dependsOn(common)
}
