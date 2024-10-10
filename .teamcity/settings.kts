import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.triggers.schedule
import jetbrains.buildServer.configs.kotlin.triggers.vcs

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

    buildType(Testtrigger)
    buildType(Testtrigger2)

    params {
        param("a", "1")
        param("teamcity.optimization.keepOptimizationLog", "true")
    }
}

object Testtrigger : BuildType({
    name = "testtrigger"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        script {
            id = "simpleRunner"
            scriptContent = "sleep 5"
        }
    }
})

object Testtrigger2 : BuildType({
    name = "testtrigger2"

    type = BuildTypeSettings.Type.COMPOSITE
    maxRunningBuilds = 1

    vcs {
        root(DslContext.settingsRoot, "-:.teamcity", "+:folder => folder")

        showDependenciesChanges = true
    }

    triggers {
        vcs {
            triggerRules = "-:user=teamcity:**"
            branchFilter = ""
        }
        schedule {
            enabled = false
            schedulingPolicy = cron {
                minutes = "26"
            }
            branchFilter = ""
            triggerBuild = always()
            withPendingChangesOnly = false
            param("minute", "20")
            param("hour", "16")
        }
    }

    dependencies {
        snapshot(Testtrigger) {
            reuseBuilds = ReuseBuilds.NO
        }
    }
})
