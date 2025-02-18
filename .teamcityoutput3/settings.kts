import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.script

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

    buildType(B2)
    buildType(B1)
}

object B1 : BuildType({
    name = "b1"

    params {
        param("par1", "1")
    }

    outputParams {
        exposeAllParameters = true
        param("par1", "%par1%")
    }

    vcs {
        root(DslContext.settingsRoot)
    }
})

object B2 : BuildType({
    name = "b2"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        script {
            id = "simpleRunner"
            scriptContent = """echo "${B1.depParamRefs["teamcity.build.triggeredBy"]}""""
        }
    }

    dependencies {
        snapshot(B1) {
            reuseBuilds = ReuseBuilds.NO
        }
    }
})
