package Maven_Pp.buildTypes

import jetbrains.buildServer.configs.kotlin.*

object Maven_Pp_Build : BuildType({
    name = "Build"

    vcs {
        root(Maven_Pp.vcsRoots.Maven_Pp_HttpsGithubComChubatovaTigerGradleTestsRefsHeadsMaster)
    }
})
