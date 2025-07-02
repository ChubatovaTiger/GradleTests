package Project1

import Project1.buildTypes.*
import Project1.vcsRoots.*
import Project1.vcsRoots.Project1_HttpsGithubComChubatovaTigerGradleTests
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project
import jetbrains.buildServer.configs.kotlin.v2019_2.projectFeatures.VersionedSettings
import jetbrains.buildServer.configs.kotlin.v2019_2.projectFeatures.versionedSettings

object Project : Project({
    uuid = "39304c21-a78e-4e0d-b457-4f77d324d3fc"
    id("Project1")
    parentId("_Root")
    name = "project1"

    vcsRoot(Project1_HttpsGithubComChubatovaTigerGradleTests)
    vcsRoot(Project1_HttpsGithubComChubatovaTigerRepo)

    buildType(Project1_Build1)

    features {
        versionedSettings {
            id = "PROJECT_EXT_2"
            mode = VersionedSettings.Mode.ENABLED
            buildSettingsMode = VersionedSettings.BuildSettingsMode.USE_CURRENT_SETTINGS
            rootExtId = "${Project1_HttpsGithubComChubatovaTigerGradleTests.id}"
            showChanges = true
            settingsFormat = VersionedSettings.Format.KOTLIN
            storeSecureParamsOutsideOfVcs = true
            allowEditingOfProjectSettings = true
        }
    }
})
