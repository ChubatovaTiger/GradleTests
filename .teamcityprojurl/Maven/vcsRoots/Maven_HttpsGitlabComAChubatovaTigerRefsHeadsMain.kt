package Maven.vcsRoots

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.vcs.GitVcsRoot

object Maven_HttpsGitlabComAChubatovaTigerRefsHeadsMain : GitVcsRoot({
    name = "https://gitlab.com/AChubatova/tiger#refs/heads/main"
    url = "https://gitlab.com/AChubatova/tiger"
    branch = "refs/heads/main"
    branchSpec = "refs/heads/*"
    authMethod = token {
        userName = "oauth2"
        tokenId = "tc_token_id:8ecfea6a-f953-49b9-b753-3797a71b2f3f-tc-cloud-gitlab-connection:11:3adb0c39-483a-452c-90f0-6beac3d49f36"
    }
    param("pipelines.connectionId", "tc-cloud-gitlab-connection")
    param("tokenType", "refreshable")
})
