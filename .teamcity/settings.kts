import jetbrains.buildServer.configs.kotlin.*

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

    buildType(Dep)
    buildType(Build)

    params {
        param("def.branch", "refs/heads/main")
    }
}

object Build : BuildType({
    name = "Build"

    params {
        param("def.branch", "refs/heads/master")
    }

    outputParams {
        exposeAllParameters = false
        param("def.branch", "%def.branch%")
    }

    vcs {
        root(DslContext.settingsRoot)
    }
})

object Dep : BuildType({
    name = "dep"

    params {
        param("def.branch", "${Build.depParamRefs["def.branch"]}")
    }

    outputParams {
        exposeAllParameters = false
        param("def.branch", "%def.branch%")
    }

    vcs {
        root(DslContext.settingsRoot)
    }

    dependencies {
        snapshot(Build) {
            reuseBuilds = ReuseBuilds.NO
        }
    }
})
