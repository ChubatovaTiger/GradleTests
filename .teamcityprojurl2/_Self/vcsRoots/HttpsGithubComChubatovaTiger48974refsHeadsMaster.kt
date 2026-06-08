package _Self.vcsRoots

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.vcs.GitVcsRoot

object HttpsGithubComChubatovaTiger48974refsHeadsMaster : GitVcsRoot({
    name = "https://github.com/ChubatovaTiger/48974#refs/heads/master"
    url = "https://github.com/ChubatovaTiger/48974"
    branch = "refs/heads/master"
    branchSpec = "refs/heads/*"
    authMethod = password {
        userName = "ChubatovaTiger"
        password = "credentialsJSON:5881298e-3a11-4576-9872-91f7e6cceddd"
    }
})
