import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.perfmon
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

version = "2025.11"

project {

    buildType(Build1)
    buildType(Build2)
    buildType(Build3)
}

object Build1 : BuildType({
    name = "Build1"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        script {
            id = "simpleRunner"
            scriptContent = "echo a"
        }
    }

    features {
        perfmon {
        }
    }
})

object Build2 : BuildType({
    name = "Build2"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        script {
            id = "simpleRunner"
            scriptContent = "echo a"
        }
    }

    features {
        perfmon {
        }
    }

    dependencies {
        snapshot(Build1) {
            reuseBuilds = ReuseBuilds.ANY
        }
    }
})

object Build3 : BuildType({
    name = "Build3"

    type = BuildTypeSettings.Type.COMPOSITE

    vcs {
        root(DslContext.settingsRoot)

        showDependenciesChanges = true
    }

    steps {
        script {
            id = "simpleRunner"
            scriptContent = "exit 1"
        }
    }

    features {
        perfmon {
        }
    }

    dependencies {
        snapshot(Build1) {
            reuseBuilds = ReuseBuilds.NO
        }
        snapshot(Build2) {
            reuseBuilds = ReuseBuilds.NO
        }
        snapshot(ParTest_Pipeline1GradleTests) {
            reuseBuilds = ReuseBuilds.NO
        }
        snapshot(ParTest_Pipeline3) {
            reuseBuilds = ReuseBuilds.NO
        }
    }
})
