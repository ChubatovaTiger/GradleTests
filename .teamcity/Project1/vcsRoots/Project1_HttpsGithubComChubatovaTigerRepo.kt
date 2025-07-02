package Project1.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot

object Project1_HttpsGithubComChubatovaTigerRepo : GitVcsRoot({
    uuid = "8b442fe3-696f-4f84-861e-cfe1f045a33b"
    name = "https://github.com/ChubatovaTiger/repo"
    url = "https://github.com/ChubatovaTiger/repo"
    branch = "refs/heads/main"
    param("useAlternates", "true")
})
