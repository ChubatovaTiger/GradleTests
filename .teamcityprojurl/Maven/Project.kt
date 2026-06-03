package Maven

import Maven.buildTypes.*
import Maven.vcsRoots.*
import Maven.pipelines.*
import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.Project

object Project : Project({
    id("Maven")
    name = "maven"

    vcsRoot(Maven_HttpsGitlabComAChubatovaTigerRefsHeadsMain)
    vcsRoot(Maven_HttpsGithubComChubatovaTigerMavenJunitRefsHeadsMain)
    vcsRoot(Maven_GitGithubComChubatovaTigerMavenJunitGitRefsHeadsMain)

    buildType(Maven_Maven)

    pipeline(Maven_Mavenppl)
    pipeline(Maven_MvnPpl3)

    subProject(Maven_id.Project)
    subProject(Maven_Pp.Project)
})
