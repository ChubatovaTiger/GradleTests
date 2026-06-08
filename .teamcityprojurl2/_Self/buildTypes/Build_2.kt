package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.*

object Build_2 : BuildType({
    name = "Build"

    vcs {
        root(_Self.vcsRoots.HttpsGithubComChubatovaTiger48974refsHeadsMaster)
    }
})
