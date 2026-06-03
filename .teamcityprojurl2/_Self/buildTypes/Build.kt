package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.perfmon
import jetbrains.buildServer.configs.kotlin.buildSteps.script

object Build : BuildType({
    name = "build"

    steps {
        script {
            id = "simpleRunner"
            scriptContent = "echo " + DslContext.serverUrl
        }
    }

    features {
        perfmon {
        }
    }
})
