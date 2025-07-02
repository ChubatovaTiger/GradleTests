package Project1.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.freeDiskSpace

object Project1_Build1 : BuildType({
    uuid = "9ef5aa98-9ff7-4dcf-8759-8c8b3fce64e7"
    name = "build1"

    vcs {
        root(Project1.vcsRoots.Project1_HttpsGithubComChubatovaTigerRepo)
    }

    features {
        freeDiskSpace {
            failBuild = false
        }
    }
})
