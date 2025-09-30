import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.projectFeatures.buildReportTab
import jetbrains.buildServer.configs.kotlin.projectFeatures.githubAppConnection
import jetbrains.buildServer.configs.kotlin.vcs.GitVcsRoot

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

version = "2025.07"

project {
    description = "Contains all other projects"

    params {
        param("df", "sdf")
    }

    features {
        buildReportTab {
            id = "PROJECT_EXT_1"
            title = "Code Coverage"
            startPage = "coverage.zip!index.html"
        }
        githubAppConnection {
            id = "PROJECT_EXT_3"
            displayName = "TeamCitybbkb"
            appId = "2038995"
            clientId = "Iv23liLADN7iuuqucgTA"
            clientSecret = "credentialsJSON:22c81739-ecc9-4162-b339-306484a8dac6"
            privateKey = "credentialsJSON:da269e3e-90e4-415d-bd0d-bce9b0ada5d6"
            webhookSecret = "credentialsJSON:0f2965fb-3181-46ae-b148-ccf9b50ef42e"
            ownerUrl = "https://github.com/ChubatovaTiger"
            useUniqueCallback = true
        }
    }

    cleanup {
        baseRule {
            preventDependencyCleanup = false
        }
    }

    subProject(Project2)
}


object Project2 : Project({
    name = "project2"

    vcsRoot(Project2_Root1)

    buildType(Project2_Build1)

    params {
        password("par1", "credentialsJSON:e3a7e487-8a72-41ee-ae5b-21211acb8181")
    }
})

object Project2_Build1 : BuildType({
    name = "Build1"

    artifactRules = "1.txt"

    params {
        param("branch", "master")
    }

    steps {
        script {
            id = "simpleRunner"
            scriptContent = "echo %par1% > 1.txt"
        }
    }
})

object Project2_Root1 : GitVcsRoot({
    name = "root1"
    url = "git@github.com:ChubatovaTiger/mavenJunit.git"
    branch = "refs/heads/main"
    authMethod = uploadedKey {
        uploadedKey = "rsaopensshnew"
        passphrase = "credentialsJSON:24f4d1f8-3c36-4550-b010-7b9218f26785"
    }
})
