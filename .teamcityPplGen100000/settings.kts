import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.pipelines.*
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

version = "2025.11"

project {

    buildType(RunAll)
    buildType(C)
    buildType(A)
    buildType(B)

    pipeline(Ppl1)
}

object A : BuildType({
    name = "A"

    params {
        param("person", "Mary")
    }

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        script {
            id = "simpleRunner"
            scriptContent = """
                echo %person%
                echo a
            """.trimIndent()
        }
    }
})

object B : BuildType({
    name = "B"

    params {
        param("override.dep.ProjectA_A.person", "Mike")
    }

    vcs {
        root(DslContext.settingsRoot)
    }

    dependencies {
        snapshot(A) {
        }
    }
})

object C : BuildType({
    name = "C"

    params {
        param("override.dep.ProjectA_A.person", "John")
    }

    vcs {
        root(DslContext.settingsRoot)
    }

    dependencies {
        snapshot(A) {
        }
    }
})

object RunAll : BuildType({
    name = "RunAll"

    type = BuildTypeSettings.Type.COMPOSITE

    vcs {
        root(DslContext.settingsRoot)
    }

    dependencies {
        snapshot(C) {
        }
    }
})


object Ppl1 : Pipeline({
    name = "ppl1"

    repositories {
        repository(DslContext.settingsRoot)
    }

    triggers {
        vcs {
        }
    }

    dependencies {
        snapshot(AbsoluteId("Project2_GradleTests")) {
            reuseBuilds = ReuseBuilds.NO
            onDependencyFailure = FailureAction.FAIL_TO_START
            onDependencyCancel = FailureAction.CANCEL
            synchronizeRevisions = false
        }
        snapshot(C) {
reuseBuilds = ReuseBuilds.NO
        }
    }

    job(Ppl1_Job1)
})

object Ppl1_Job1 : Job({
    id("Job1")
    name = "Job 1"
})
