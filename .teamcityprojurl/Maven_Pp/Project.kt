package Maven_Pp

import Maven_Pp.buildTypes.*
import Maven_Pp.vcsRoots.*
import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.Project

object Project : Project({
    id("Maven_Pp")
    name = "pp"

    vcsRoot(Maven_Pp_HttpsGithubComChubatovaTigerGradleTestsRefsHeadsMaster)

    buildType(Maven_Pp_Build)
})
