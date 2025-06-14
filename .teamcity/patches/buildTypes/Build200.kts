package patches.buildTypes

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.PowerShellStep
import jetbrains.buildServer.configs.kotlin.buildSteps.powerShell
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.failureConditions.BuildFailureOnMetric
import jetbrains.buildServer.configs.kotlin.failureConditions.BuildFailureOnText
import jetbrains.buildServer.configs.kotlin.failureConditions.failOnMetricChange
import jetbrains.buildServer.configs.kotlin.failureConditions.failOnText
import jetbrains.buildServer.configs.kotlin.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, change the buildType with id = 'Build200'
accordingly, and delete the patch script.
*/
changeBuildType(RelativeId("Build200")) {
    check(paused == false) {
        "Unexpected paused: '$paused'"
    }
    paused = true

    expectSteps {
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
    steps {
        update<PowerShellStep>(1) {
            enabled = false
            clearConditions()
        }
        insert(2) {
            script {
                id = "simpleRunner_1"
                scriptContent = "sleep %sleep%"
            }
        }
    }

    failureConditions {
        val feature1 = find<BuildFailureOnText> {
            failOnText {
                conditionType = BuildFailureOnText.ConditionType.CONTAINS
                pattern = "turtle"
                failureMessage = "Turtles are not fluffy"
                reverse = false
                stopBuildOnFailure = true
            }
        }
        feature1.apply {
            enabled = false
        }
        val feature2 = find<BuildFailureOnMetric> {
            failOnMetricChange {
                metric = BuildFailureOnMetric.MetricType.BUILD_DURATION
                units = BuildFailureOnMetric.MetricUnit.DEFAULT_UNIT
                comparison = BuildFailureOnMetric.MetricComparison.MORE
                compareTo = value()
                stopBuildOnFailure = true
                param("metricThreshold", "%limit%")
            }
        }
        feature2.apply {
            threshold = 5
        }
    }
}
