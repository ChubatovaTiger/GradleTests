package patches.vcsRoots

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.ui.*
import jetbrains.buildServer.configs.kotlin.vcs.GitVcsRoot

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, create a vcsRoot with id = 'HttpsGithubComChubatovaTigerManytags'
in the root project, and delete the patch script.
*/
create(DslContext.projectId, GitVcsRoot({
    id("HttpsGithubComChubatovaTigerManytags")
    name = "https://github.com/ChubatovaTiger/manytags"
    url = "https://github.com/ChubatovaTiger/manytags"
    branch = "refs/heads/12e"
    useTagsAsBranches = true
    authMethod = password {
        userName = "ChubatovaTiger"
        password = "credentialsJSON:4cc270c4-6e68-4017-9b2b-70cf30cf5ccf"
    }
}))

