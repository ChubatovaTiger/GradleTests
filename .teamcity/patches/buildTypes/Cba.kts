package patches.buildTypes

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.commitStatusPublisher
import jetbrains.buildServer.configs.kotlin.buildSteps.gradle
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, change the buildType with id = 'Cba'
accordingly, and delete the patch script.
*/
changeBuildType(RelativeId("Cba")) {
    check(artifactRules == "") {
        "Unexpected option value: artifactRules = $artifactRules"
    }
    artifactRules = "parsec*=>."

    check(buildNumberPattern == "%build.counter%") {
        "Unexpected option value: buildNumberPattern = $buildNumberPattern"
    }
    buildNumberPattern = "%build.counter%-master"

    params {
        expect {
            param("aa", "an")
        }
        update {
            param("aa", "master")
        }
        add {
            param("env.env", "envmaster")
        }
        add {
            password("parsec", "credentialsJSON:a42e2243-4440-4b74-a541-65893bb2c686")
        }
    }

    expectSteps {
        gradle {
            tasks = "clean build"
            gradleWrapperPath = ""
        }
    }
    steps {
        insert(1) {
            script {
                executionMode = BuildStep.ExecutionMode.RUN_ON_FAILURE
                scriptContent = "echo master"
            }
        }
        insert(2) {
            script {
                name = "New build step"
                executionMode = BuildStep.ExecutionMode.RUN_ON_FAILURE
                scriptContent = "echo %parsec% > parsec.txt"
            }
        }
    }

    failureConditions {

        check(testFailure == true) {
            "Unexpected option value: testFailure = $testFailure"
        }
        testFailure = false

        check(nonZeroExitCode == true) {
            "Unexpected option value: nonZeroExitCode = $nonZeroExitCode"
        }
        nonZeroExitCode = false
    }

    features {
        add {
            commitStatusPublisher {
                vcsRootExtId = "${DslContext.settingsRoot.id}"
                publisher = github {
                    githubUrl = "https://api.github.com"
                    authType = personalToken {
                        token = "credentialsJSON:25aa9774-b33d-4ec7-9045-8ec357f54265"
                    }
                }
            }
        }
    }

    requirements {
        add {
            contains("teamcity.agent.name", "Default")
        }
    }
}
