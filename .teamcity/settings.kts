import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.gradle
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

version = "2024.03"

project {

    buildType(Build1)
    buildType(Build2)
    buildType(Build5)
    buildType(Build3)
    buildType(Build4)
}

object Build1 : BuildType({
    name = "build1"

    type = BuildTypeSettings.Type.COMPOSITE

    vcs {
        root(DslContext.settingsRoot)

        showDependenciesChanges = true
    }

    dependencies {
        snapshot(Build2) {
            reuseBuilds = ReuseBuilds.NO
        }
    }
})

object Build2 : BuildType({
    name = "build2"

    steps {
        script {
            id = "simpleRunner"
            scriptContent = """
                echo "##teamcity[testStarted name='mytest7.test42s']"
                #echo "##teamcity[testFailed type='comparisonFailure' name='MyTest.test2' message='failure message' details='message and stack trace' expected='expected value' actual='actual value']"
                #echo "##teamcity[testIgnored name='MyTest.test2' message='ignore comment']"
                echo "##teamcity[testFinished name='mytest7.test42s']"
            """.trimIndent()
        }
    }
})

object Build3 : BuildType({
    name = "build3"

    steps {
        script {
            id = "simpleRunner"
            scriptContent = """
                echo "##teamcity[testStarted name='MyTest.test2']"
                #echo "##teamcity[testFailed type='comparisonFailure' name='MyTest.test2' message='failure message' details='message and stack trace' expected='expected value' actual='actual value']"
                #echo "##teamcity[testIgnored name='MyTest.test2' message='ignore comment']"
                echo "##teamcity[testFinished name='MyTest.test2']"
            """.trimIndent()
        }
    }
})

object Build4 : BuildType({
    name = "build4"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        gradle {
            id = "gradle_runner"
            tasks = "clean build"
            jdkHome = "%env.JDK_11%"
        }
    }
})

object Build5 : BuildType({
    name = "build5"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        gradle {
            id = "gradle_runner"
            tasks = "clean build"
            jdkHome = "%env.JDK_11%"
        }
    }
})
