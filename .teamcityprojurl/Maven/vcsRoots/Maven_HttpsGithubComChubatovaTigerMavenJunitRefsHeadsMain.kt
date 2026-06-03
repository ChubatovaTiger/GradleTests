package Maven.vcsRoots

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.vcs.GitVcsRoot

object Maven_HttpsGithubComChubatovaTigerMavenJunitRefsHeadsMain : GitVcsRoot({
    name = "https://github.com/ChubatovaTiger/mavenJunit#refs/heads/main"
    url = "https://github.com/ChubatovaTiger/mavenJunit"
    branch = "refs/heads/main"
    branchSpec = "refs/heads/*"
    authMethod = password {
        userName = "ChubatovaTiger"
        password = "credentialsJSON:6dcf533a-3799-4b1f-9d4b-887c5d0c3879"
    }
    param("pipelines.connectionId", "tc-cloud-github-connection")
})
