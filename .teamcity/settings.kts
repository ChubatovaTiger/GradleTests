import jetbrains.buildServer.configs.kotlin.*
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

version = "2025.03"

project {
    defaultTemplate = AbsoluteId("Project1_Ljhljkhjkhkj")

    buildType(Sdf)

    template(Ggg)

    params {
        param("a", "a")
    }
}

object Sdf : BuildType({
    templates(AbsoluteId("Project1_Ljhljkhjkhkj"), RelativeId("Ggg"))
    name = "sdf"

    artifactRules = "file.txt"

    steps {
        script {
            id = "simpleRunner"
            scriptContent = "echo %a% > file.txt"
        }
    }

    features {
        matrix {
            id = "matrix"
            param("a", listOf(
                value("1"),
                value("2")
            ))
            param("separateArtifacts", "true")
        }
    }
})

object Ggg : Template({
    name = "ggg"
})
