package Maven.vcsRoots

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.vcs.GitVcsRoot

object Maven_GitGithubComChubatovaTigerMavenJunitGitRefsHeadsMain : GitVcsRoot({
    name = "git@github.com:ChubatovaTiger/mavenJunit.git#refs/heads/main"
    url = "git@github.com:ChubatovaTiger/mavenJunit.git"
    branch = "refs/heads/main"
    branchSpec = "refs/heads/*"
    authMethod = uploadedKey {
        uploadedKey = "rsaopensshnew"
        passphrase = "credentialsJSON:35e00b8a-dca4-44b2-98af-428959f966cd"
    }
})
