def commonSettings: Seq[Setting[_]] = Seq(
  version := "1.7.3-SNAPSHOT",
  scalaVersion := "2.12.4",
  organization := "com.graphhopper.forked", // forked from https://github.com/graphhopper/jsprit
  javacOptions in Compile ++= Seq("-Xlint:deprecation"),
  javacOptions ++= Seq("-source", "1.7", "-target", "1.7", "-encoding", "utf8"),
  sources in (Compile, doc) := Seq.empty,
  libraryDependencies ++= Seq(
      "junit" % "junit" % "4.12" % Test,
      "org.mockito" % "mockito-all" % "1.9.5" % Test
  )
)

lazy val JspritCoreProject = Project("Jsprit-Core", file("jsprit-core"))
  .settings(
    commonSettings,
    autoScalaLibrary := false,
    crossPaths := false,
    libraryDependencies ++= Seq(
        "org.apache.commons" % "commons-math3" % "3.4",
        "org.slf4j" % "slf4j-api" % "1.7.25"
    )
  )


lazy val publishedProjects = Seq[ProjectReference](
  JspritCoreProject
)

lazy val JspritLibraries = Project("Jsprit-Libraries", file("."))
  .settings(
    commonSettings
  )
  .aggregate(publishedProjects: _*)

// 別モジュールにする場合に必要(publish時)
AutoMkcol.globalSettings
