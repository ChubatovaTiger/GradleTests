package Maven_id.vcsRoots

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.vcs.GitVcsRoot

object Maven_id_HttpsGithubComChubatovaTigerGradleTestsRefsHeadsMain : GitVcsRoot({
    name = "https://github.com/ChubatovaTiger/GradleTests#refs/heads/main"
    url = "https://github.com/ChubatovaTiger/GradleTests"
    branch = "refs/heads/main"
    branchSpec = "refs/heads/*"
})
