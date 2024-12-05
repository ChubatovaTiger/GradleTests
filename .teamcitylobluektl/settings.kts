import jetbrains.buildServer.configs.kotlin.*
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

version = "2024.03"

project {

    params {
        param("gf", "nnn00")
    }

    features {
        githubAppConnection {
            id = "PROJECT_EXT_2"
            displayName = "TeamCity-gha2024072"
            appId = "1078554"
            clientId = "Iv23liLdGrQ9toXjC99F"
            clientSecret = "credentialsJSON:b2ce8425-5f45-476a-8642-d6e0c662a40d"
            privateKey = "credentialsJSON:5cb62849-ab04-4c1f-820a-d7a1b31573fc"
            webhookSecret = "credentialsJSON:5f81ca58-b208-4b77-ade9-53e851e06444"
            ownerUrl = "https://github.com/ChubatovaTiger"
            useUniqueCallback = true
        }
    }
}
