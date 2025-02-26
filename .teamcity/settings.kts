import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.PullRequests
import jetbrains.buildServer.configs.kotlin.buildFeatures.pullRequests
import jetbrains.buildServer.configs.kotlin.buildSteps.script
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

version = "2024.12"

project {

    vcsRoot(G)

    buildType(B2)
    buildType(B1)
    buildType(F)
    buildType(Comp)

    template(Kjk)
}

object B1 : BuildType({
    name = "b1"

    steps {
        script {
            id = "simpleRunner"
            scriptContent = "echo a"
            dockerImage = "python"
        }
    }
})

object B2 : BuildType({
    name = "b2"
})

object Comp : BuildType({
    name = "comp"

    type = BuildTypeSettings.Type.COMPOSITE

    vcs {
        showDependenciesChanges = true
    }
})

object F : BuildType({
    name = "f"

    features {
        pullRequests {
            vcsRootExtId = "${G.id}"
            provider = github {
                authType = vcsRoot()
                filterAuthorRole = PullRequests.GitHubRoleFilter.MEMBER
            }
        }
    }
})

object Kjk : Template({
    name = "kjk"

    params {
        param("asd", "asd")
    }
})

object G : GitVcsRoot({
    name = "g"
    url = "https://github.com/ChubatovaTiger/GradleTests"
    branch = "refs/heads/master"
    authMethod = password {
        userName = "ChubatovaTiger"
        password = "credentialsJSON:09805969-fb4f-4be9-8224-38abf204eb25"
    }
})
