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

version = "2025.03"

project {

    buildType(Project1_Build1)

    subProject(Project1_Project3)
}

object Project1_Build1 : BuildType({
    id("Build1")
    name = "build1"

    vcs {
        root(DslContext.settingsRoot)
    }
})


object Project1_Project3 : Project({
    id("Project3")
    name = "project3"

    buildType(Project1_Project3_Build1)
})

object Project1_Project3_Build1 : BuildType({
    id("Project3_Build1")
    name = "build1"

    steps {
        script {
            id = "simpleRunner"
            scriptContent = "echo a"
        }
    }
})
