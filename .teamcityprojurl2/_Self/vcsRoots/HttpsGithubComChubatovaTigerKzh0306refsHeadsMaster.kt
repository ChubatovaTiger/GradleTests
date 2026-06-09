package _Self.vcsRoots

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.vcs.GitVcsRoot

object HttpsGithubComChubatovaTigerKzh0306refsHeadsMaster : GitVcsRoot({
    name = "https://github.com/ChubatovaTiger/kzh0306#refs/heads/master"
    url = "https://github.com/ChubatovaTiger/kzh0306"
    branch = "refs/heads/master"
    branchSpec = "+:refs/heads/*"
    authMethod = token {
        userName = "oauth2"
        tokenId = "tc_token_id:8ecfea6a-f953-49b9-b753-3797a71b2f3f-PROJECT_EXT_6:-1:e70d6ab4-404f-455f-9d0a-4f4904edd507"
    }
})
