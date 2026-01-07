import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.perfmon
import jetbrains.buildServer.configs.kotlin.buildSteps.powerShell
import jetbrains.buildServer.configs.kotlin.buildSteps.python
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

    subProject(Project2)
}


object Project2 : Project({
    name = "project2"

    subProject(Project2_Project3)
})


object Project2_Project3 : Project({
    name = "project3"

    buildType(Project2_Project3_Build1)
    buildType(Project2_Project3_Build2)
    buildType(Project2_Project3_Build3)
})

object Project2_Project3_Build1 : BuildType({
    name = "Build1"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        python {
            id = "python_runner"
            command = file {
                filename = "n.py"
            }
        }
    }

    features {
        perfmon {
        }
    }
})

object Project2_Project3_Build2 : BuildType({
    name = "Build2"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        powerShell {
            id = "jetbrains_powershell"
            scriptMode = file {
                path = "jhj.ps1"
            }
        }
    }

    features {
        perfmon {
        }
    }
})

object Project2_Project3_Build3 : BuildType({
    name = "Build3"

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
