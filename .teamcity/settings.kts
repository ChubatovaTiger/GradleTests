import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.parallelTests
import jetbrains.buildServer.configs.kotlin.buildSteps.gradle

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

    buildType(Partest)
    buildType(Comp)
    buildType(Dum)
}

object Comp : BuildType({
    name = "comp"

    type = BuildTypeSettings.Type.COMPOSITE

    vcs {
        showDependenciesChanges = true
    }

    dependencies {
        snapshot(Partest) {
            reuseBuilds = ReuseBuilds.NO
        }
    }
})

object Dum : BuildType({
    name = "dum"
})

object Partest : BuildType({
    name = "partest"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        gradle {
            id = "gradle_runner"
            tasks = "clean build"
            jdkHome = "%env.JDK_11%"
            param("org.jfrog.artifactory.selectedDeployableServer.defaultModuleVersionConfiguration", "GLOBAL")
        }
    }

    features {
        parallelTests {
            numberOfBatches = 3
        }
    }
})
