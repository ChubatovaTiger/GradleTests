package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.parallelTests
import jetbrains.buildServer.configs.kotlin.buildSteps.gradle
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.matrix

object Both : BuildType({
    name = "both"

    artifactRules = """
        *.txt => a/b/c/1.zip
        1.txt => 2.zip
    """.trimIndent()

    params {
        param("env.JAVA_HOME", "/opt/homebrew/Cellar/openjdk@21/21.0.6")
    }

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        gradle {
            id = "gradle_runner"
            tasks = "clean build"
            gradleWrapperPath = ""
        }
        script {
            id = "simpleRunner"
            scriptContent = """
                echo %build.number% > 1.txt
                echo %build.number% > 2.txt
            """.trimIndent()
        }
    }

    features {
        parallelTests {
            numberOfBatches = 10
        }
        matrix {
            param("par1", listOf(
                value("val 1"),
                value("val2")
            ))
            param("par2", listOf(
                value("val 1"),
                value("val 2")
            ))
        }
    }
})
