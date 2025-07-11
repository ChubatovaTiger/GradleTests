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

version = "2025.03"

project {
    defaultTemplate = RelativeId("Tmpl")

    buildType(Project1_Build1)

    template(Project1_Tmpl)

    subProject(Project1_Subproj1)
}

object Project1_Build1 : BuildType({
    id("Build1")
    name = "build1"

    params {
        param("a", "a")
    }
})

object Project1_Tmpl : Template({
    id("Tmpl")
    name = "tmpl"

    params {
        param("a", "a")
    }
})


object Project1_Subproj1 : Project({
    id("Subproj1")
    name = "subproj1"

    params {
        param("sdf", "sdf")
    }
})
