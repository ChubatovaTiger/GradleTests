package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.*

object Build1 : BuildType({
    name = "Build1"

    vcs {
        root(_Self.vcsRoots.HttpsGithubComChubatovaTigerVsdormRefsHeadsMaster)
    }
})
