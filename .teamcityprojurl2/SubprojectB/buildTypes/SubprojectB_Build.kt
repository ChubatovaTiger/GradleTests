package SubprojectB.buildTypes

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.script


object SubprojectB_Build : BuildType({
    name = "Build"

    vcs {
        root(DslContext.settingsRoot)
    }
        steps {
        script {
            id = "simpleRunner"
            scriptContent = "echo " + DslContext.serverUrl
        }
    }
})
