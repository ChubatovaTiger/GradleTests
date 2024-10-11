package patches.buildTypes

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.BuildType
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, create a buildType with id = 'Proj5_2_Build1'
in the project with id = 'Proj5_2', and delete the patch script.
*/
create(RelativeId("Proj5_2"), BuildType({
    id("Proj5_2_Build1")
    name = "build1"

    artifactRules = "a*.txt"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        script {
            id = "simpleRunner"
            scriptContent = "echo a > a%system.teamcity.buildType.id%.%build.number%.txt"
        }
    }
}))

