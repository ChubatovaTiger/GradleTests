import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.CustomChart
import jetbrains.buildServer.configs.kotlin.CustomChart.*
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.projectCustomChart

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

    buildType(Usual2)
    buildType(Usual1)
    buildType(Comp)

    features {
        projectCustomChart {
            id = "PROJECT_EXT_2"
            title = "us"
            seriesTitle = "Serie"
            format = CustomChart.Format.TEXT
            series = listOf(
                Serie(title = "Build Duration (all stages)", key = SeriesKey.BUILD_DURATION, sourceBuildTypeId = "Project1_Usual1")
            )
        }
        projectCustomChart {
            id = "PROJECT_EXT_3"
            title = "comp"
            seriesTitle = "Serie"
            format = CustomChart.Format.TEXT
            series = listOf(
                Serie(title = "Build Duration (all stages)", key = SeriesKey.BUILD_DURATION, sourceBuildTypeId = "Project1_Comp")
            )
        }
        projectCustomChart {
            id = "PROJECT_EXT_4"
            title = "fgfgfg"
            seriesTitle = "Serie"
            format = CustomChart.Format.TEXT
            series = listOf(
                Serie(title = "Build Duration (all stages)", key = SeriesKey.BUILD_DURATION, sourceBuildTypeId = "Project1_Usual1")
            )
        }
    }
}

object Comp : BuildType({
    name = "comp"

    type = BuildTypeSettings.Type.COMPOSITE

    vcs {
        root(DslContext.settingsRoot)

        showDependenciesChanges = true
    }

    dependencies {
        snapshot(Usual1) {
            reuseBuilds = ReuseBuilds.NO
        }
        snapshot(Usual2) {
            reuseBuilds = ReuseBuilds.NO
        }
    }
})

object Usual1 : BuildType({
    name = "usual1"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        script {
            id = "simpleRunner"
            scriptContent = "sleep 12"
        }
    }
})

object Usual2 : BuildType({
    name = "usual2"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        script {
            id = "simpleRunner"
            scriptContent = "sleep 12"
        }
    }
})
