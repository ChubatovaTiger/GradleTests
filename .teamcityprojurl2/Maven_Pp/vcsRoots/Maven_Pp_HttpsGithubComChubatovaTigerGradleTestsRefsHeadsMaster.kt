package Maven_Pp.vcsRoots

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.vcs.GitVcsRoot

object Maven_Pp_HttpsGithubComChubatovaTigerGradleTestsRefsHeadsMaster : GitVcsRoot({
    name = "https://github.com/ChubatovaTiger/GradleTests#refs/heads/master"
    url = "https://github.com/ChubatovaTiger/GradleTests"
    branch = "refs/heads/master"
    branchSpec = "refs/heads/*"
    authMethod = token {
        userName = "oauth2"
        tokenId = "tc_token_id:8ecfea6a-f953-49b9-b753-3797a71b2f3f-PROJECT_EXT_9:-1:db4e1cee-d2a5-46a8-95c9-3b513aee97a1"
    }
    param("tokenType", "refreshable")
})
