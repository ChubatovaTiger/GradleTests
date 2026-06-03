package SubprojectB.buildTypes

import jetbrains.buildServer.configs.kotlin.*

object SubprojectB_Build : BuildType({
    name = "Build"

    vcs {
        root(DslContext.settingsRoot)
    }
})
