package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.*

object Mtrx2 : BuildType({
    name = "mtrx2"

    vcs {
        root(DslContext.settingsRoot)
    }
})
