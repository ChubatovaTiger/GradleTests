import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.perfmon
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.projectFeatures.githubAppConnection

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2024.12"

project {

    buildType(Vv)

    features {
        githubAppConnection {
            id = "PROJECT_EXT_2"
            displayName = "TeamCity-dockerrel"
            appId = "1078549"
            clientId = "Iv23liZIkoLCSFHsMAEm"
            clientSecret = "credentialsJSON:8bc2a881-89a5-4fb8-926b-f203a9db92fc"
            privateKey = "credentialsJSON:70e05414-90da-4cad-bd6b-9396c31ad597"
            webhookSecret = "credentialsJSON:e8b3106c-2c83-4205-bc67-5e3222748f13"
            ownerUrl = "https://github.com/ChubatovaTiger"
            useUniqueCallback = true
        }
    }
}

object Vv : BuildType({
    name = "vv"

    artifactRules = "a*"

    steps {
        script {
            id = "simpleRunner"
            scriptContent = "echo %blue% > a%build.number%.txt"
        }
    }

    features {
        perfmon {
        }
    }
})
