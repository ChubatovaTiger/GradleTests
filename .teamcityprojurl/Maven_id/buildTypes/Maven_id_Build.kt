package Maven_id.buildTypes

import jetbrains.buildServer.configs.kotlin.*

object Maven_id_Build : BuildType({
    name = "Build"

    vcs {
        root(Maven_id.vcsRoots.Maven_id_HttpsGithubComChubatovaTigerGradleTestsRefsHeadsMain)
    }
})
