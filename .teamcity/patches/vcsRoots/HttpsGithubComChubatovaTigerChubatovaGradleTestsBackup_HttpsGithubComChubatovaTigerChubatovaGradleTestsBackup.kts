package patches.vcsRoots

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.ui.*
import jetbrains.buildServer.configs.kotlin.vcs.GitVcsRoot

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, create a vcsRoot with id = 'HttpsGithubComChubatovaTigerChubatovaGradleTestsBackup_HttpsGithubComChubatovaTigerChubatovaGradleTestsBackup'
in the project with id = 'HttpsGithubComChubatovaTigerChubatovaGradleTestsBackup', and delete the patch script.
*/
create(RelativeId("HttpsGithubComChubatovaTigerChubatovaGradleTestsBackup"), GitVcsRoot({
    id("HttpsGithubComChubatovaTigerChubatovaGradleTestsBackup_HttpsGithubComChubatovaTigerChubatovaGradleTestsBackup")
    name = "https://github.com/ChubatovaTiger/ChubatovaGradleTestsBackup"
    url = "https://github.com/ChubatovaTiger/ChubatovaGradleTestsBackup"
    branch = "refs/heads/master"
    authMethod = password {
        userName = "ChubatovaTiger"
        password = "credentialsJSON:d55ac7e1-70e9-43d5-a101-541aef084a29"
    }
}))

