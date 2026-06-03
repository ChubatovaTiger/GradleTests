package Maven_id

import Maven_id.buildTypes.*
import Maven_id.vcsRoots.*
import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.Project

object Project : Project({
    id("Maven_id")
    name = "в"

    vcsRoot(Maven_id_HttpsGithubComChubatovaTigerGradleTestsRefsHeadsMain)

    buildType(Maven_id_Build)
})
