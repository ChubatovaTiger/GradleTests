package _Self.vcsRoots

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.vcs.GitVcsRoot

object HttpsGithubComChubatovaTigerVsdormRefsHeadsMaster : GitVcsRoot({
    name = "https://github.com/ChubatovaTiger/vsdorm#refs/heads/master"
    url = "https://github.com/ChubatovaTiger/vsdorm"
    branch = "refs/heads/master"
    branchSpec = "refs/heads/*"
    authMethod = token {
        userName = "oauth2"
        tokenId = "tc_token_id:8ecfea6a-f953-49b9-b753-3797a71b2f3f-PROJECT_EXT_6:-1:8996733d-fbd5-4936-94f7-81cec3007c5a"
    }
    param("tokenType", "refreshable")
})
