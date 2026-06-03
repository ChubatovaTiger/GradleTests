package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.perfmon
import jetbrains.buildServer.configs.kotlin.buildSteps.script

object Build : BuildType({
    name = "build"

    steps {
        script {
            id = "simpleRunner"
            scriptContent = "echo https://chubatova20261tenant1.dev.sandbox.teamcity.aws.intellij.net"
        }
    }

    features {
        perfmon {
        }
    }
})
