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

version = "2025.07"

project {

    buildType(Project1_Build1)
    buildType(Project1_Build10)

    template(Project1_Vg)

    params {
        param("banana", "banana")
        password("pwd1", "credentialsJSON:26cac976-1a96-40cd-bedd-d24bccc4ea47")
        param("parparent", "par1parent")
        param("apple", "apple")
        param("cherrysss", "cherryddd")
    }
}

object Project1_Build1 : BuildType({
    id("Build1")
    name = "build1"

    dependencies {
        snapshot(AbsoluteId("Project2_Build2")) {
            reuseBuilds = ReuseBuilds.NO
        }
    }
})

object Project1_Build10 : BuildType({
    templates(Project1_Vg)
    id("Build10")
    name = "build10"
})

object Project1_Vg : Template({
    id("Vg")
    name = "vg"

    artifactRules = "a.txt"

    steps {
        script {
            id = "simpleRunner"
            scriptContent = "echo a > a.txt"
        }
    }
})
