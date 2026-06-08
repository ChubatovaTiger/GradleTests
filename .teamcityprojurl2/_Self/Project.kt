package _Self

import _Self.buildTypes.*
import _Self.vcsRoots.*
import _Self.pipelines.*
import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.Project
import jetbrains.buildServer.configs.kotlin.projectFeatures.githubAppConnection

object Project : Project({

    vcsRoot(HttpsGithubComChubatovaTigerVsdormRefsHeadsMaster)
    vcsRoot(HttpsGithubComChubatovaTiger48974refsHeadsMaster)

    buildType(Build1)
    buildType(Build2)
    buildType(Build_2)
    buildType(Build)

    params {
        param("teamcity.project.server_url", "https://chubatova20261tenant1.dev.sandbox.teamcity.aws.intellij.net")
    }

    features {
        githubAppConnection {
            id = "PROJECT_EXT_6"
            displayName = "TeamCitydorm1"
            appId = "3752993"
            clientId = "Iv23li5OlV9ZZt47XpLl"
            clientSecret = "credentialsJSON:83521135-6d63-40dd-b0cc-1b1f76ad8d37"
            privateKey = "credentialsJSON:c18440db-7c28-4345-a258-5a88716fe79b"
            webhookSecret = "credentialsJSON:6f10b3fa-d77a-4b98-889b-bc4a76cec74a"
            ownerUrl = "https://github.com/ChubatovaTiger"
            useUniqueCallback = true
            allowBuildScopedTokens = true
        }
        githubAppConnection {
            id = "PROJECT_EXT_9"
            displayName = "TeamCityten1-2"
            appId = "3784721"
            clientId = "Iv23liZmRI9eMr4Qyd96"
            clientSecret = "credentialsJSON:caec6db5-85d1-4153-9e26-8b7d89ba8e3e"
            privateKey = "credentialsJSON:75eec951-514f-47d8-bbd4-718617542ad2"
            webhookSecret = "credentialsJSON:11ab895d-0d72-422b-87ec-30e9de635a8e"
            ownerUrl = "https://github.com/ChubatovaTiger"
            useUniqueCallback = true
        }
    }

    pipeline(Ppl2)
    pipeline(Ppl3)

    subProject(SubprojectB.Project)
    subProject(Maven.Project)
    subProject(Python.Project)
})
