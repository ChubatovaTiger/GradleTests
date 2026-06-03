package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.script

object Build2 : BuildType({
    name = "Build2"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        script {
            id = "simpleRunner"
            scriptContent = "echo 1"
        }
    }

    requirements {
        contains("teamcity.agent.name", "sdfsdfsdf")
    }
})
