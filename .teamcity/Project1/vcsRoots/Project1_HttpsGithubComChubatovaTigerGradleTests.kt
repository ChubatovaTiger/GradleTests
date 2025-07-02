package Project1.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot

object Project1_HttpsGithubComChubatovaTigerGradleTests : GitVcsRoot({
    uuid = "b0fec4d3-05a5-421d-9e7e-aa5f6266457e"
    name = "https://github.com/ChubatovaTiger/GradleTests"
    url = "https://github.com/ChubatovaTiger/GradleTests"
    branch = "refs/heads/master"
    authMethod = password {
        userName = "ChubatovaTiger"
        password = "credentialsJSON:1d245fc8-7918-4ae4-a0d6-8f3d2acfb5db"
    }
    param("useAlternates", "true")
})
