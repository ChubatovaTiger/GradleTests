import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.buildCache
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.matrix

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

version = "2024.07"

project {

    buildType(B1)
    buildType(B20)

    template(Sdsdf)

    cleanup {
        baseRule {
            option("disableCleanupPolicies", false)
        }
    }
}

object B1 : BuildType({
    templates(Sdsdf)
    name = "b1"

    steps {
        script {
            id = "simpleRunner"
            enabled = false
            scriptContent = """
                echo a >> a%build.number%.txt
                echo %teamcity.build.branch% >> b2.txt
            """.trimIndent()
        }
        stepsOrder = arrayListOf("simpleRunner", "simpleRunner_1")
    }
    
    disableSettings("matrix")
})

object B20 : BuildType({
    name = "b20"

    artifactRules = "a*.txt"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        script {
            id = "simpleRunner"
            scriptContent = """
                echo a >> a%build.number%.txt
                echo %teamcity.build.branch% >> b1.txt
            """.trimIndent()
        }
        script {
            id = "simpleRunner_1"
            scriptContent = "cat b1.txt"
        }
    }

    features {
        buildCache {
            name = "cache123123"
            publishOnlyChanged = false
            rules = "b1.txt"
        }
    }
})

object Sdsdf : Template({
    name = "sdsdf"

    artifactRules = "a*.txt"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        script {
            id = "simpleRunner_1"
            scriptContent = "cat b1.txt"
        }
    }

    features {
        buildCache {
            id = "BUILD_EXT_5"
            name = "bubububu2"
            publishOnlyChanged = false
            rules = "b2.txt"
        }
        matrix {
            id = "matrix"
            param("a", listOf(
                value("1"),
                value("2")
            ))
        }
    }

    dependencies {
        snapshot(B20) {
            reuseBuilds = ReuseBuilds.NO
        }
    }
})
