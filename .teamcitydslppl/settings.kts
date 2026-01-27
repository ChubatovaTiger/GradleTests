import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.perfmon
import jetbrains.buildServer.configs.kotlin.buildSteps.nodeJS
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

version = "2025.11"

project {

    vcsRoot(HttpsGithubComChubatovaTigerNodejssampleRefsHeadsMaster)

    buildType(Build2)
    buildType(Build1)
    buildType(Build)

    params {
        password("pwd2", "credentialsJSON:c4787ce2-66cb-4ae5-b5c0-a63ebef70042")
    }
}

object Build : BuildType({
    name = "Build"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        nodeJS {
            id = "nodejs_runner"
            shellScript = """
                npm ci
                npm run test
            """.trimIndent()
        }
    }

    features {
        perfmon {
        }
    }

    dependencies {
        snapshot(RelativeId("GradleTests")) {
        }
    }
})

object Build1 : BuildType({
    name = "Build 1"

    vcs {
        root(HttpsGithubComChubatovaTigerNodejssampleRefsHeadsMaster)
    }

    features {
        perfmon {
        }
    }
})

object Build2 : BuildType({
    name = "build2"

    features {
        perfmon {
        }
    }
})

object HttpsGithubComChubatovaTigerNodejssampleRefsHeadsMaster : GitVcsRoot({
    name = "https://github.com/ChubatovaTiger/nodejssample#refs/heads/master"
    url = "https://github.com/ChubatovaTiger/nodejssample"
    branch = "refs/heads/master"
    branchSpec = "refs/heads/*"
})
