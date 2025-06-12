import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.powerShell
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.failureConditions.BuildFailureOnMetric
import jetbrains.buildServer.configs.kotlin.failureConditions.BuildFailureOnText
import jetbrains.buildServer.configs.kotlin.failureConditions.failOnMetricChange
import jetbrains.buildServer.configs.kotlin.failureConditions.failOnText
import jetbrains.buildServer.configs.kotlin.matrix



version = "2025.03"

project {

    buildType(Build200)
}

object Build200 : BuildType({
     name = "Matrix with failure condition2"

    params {
        param("limit", "5")
    }

    steps {
        script {
            id = "simpleRunner"
            scriptContent = "echo fluffy %animal%"
        }
        powerShell {
            id = "jetbrains_powershell"
            scriptMode = script {
                content = "Sleep %sleep%"
            }
        }
    }

    failureConditions {
        failOnText {
            conditionType = BuildFailureOnText.ConditionType.CONTAINS
            pattern = "turtle"
            failureMessage = "Turtles are not fluffy"
            reverse = false
            stopBuildOnFailure = true
        }
        failOnMetricChange {
            metric = BuildFailureOnMetric.MetricType.BUILD_DURATION
            units = BuildFailureOnMetric.MetricUnit.DEFAULT_UNIT
            comparison = BuildFailureOnMetric.MetricComparison.MORE
            compareTo = value()
            stopBuildOnFailure = true
            param("metricThreshold", "%limit%")
        }
    }

    features {
        matrix {
            param("animal", listOf(
                value("cat"),
                value("dog"),
                value("bunny"),
                value("turtle")
            ))
            param("sleep", listOf(
                value("0"),
                value("6")
            ))
        }
    }
})
