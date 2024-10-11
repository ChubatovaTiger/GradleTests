import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.vcs.PerforceVcsRoot

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
    description = "Contains all other projects"

    vcsRoot(Sd)
    vcsRoot(Prfrc)

    subProject(P1)
    subProject(Sdf)
    subProject(Project11)
}

object Prfrc : PerforceVcsRoot({
    name = "prfrc"
    port = "localhost:1666"
    mode = clientMapping {
        mapping = "//nastiadepo/boss/... //team-city-agent/..."
    }
    userName = "jetbrains"
    password = "credentialsJSON:b17ce248-ac8a-4b16-840a-3af8f08cf27b"
    workspaceOptions = """
        Options:        noallwrite clobber nocompress unlocked nomodtime rmdir
        Host:           %teamcity.agent.hostname%
        SubmitOptions:  revertunchanged
        LineEnd:        local
    """.trimIndent()
})

object Sd : PerforceVcsRoot({
    name = "sd"
    port = "localhost:1666"
    mode = clientMapping {
        mapping = "//nastiadepo/boss/... //team-city-agent/..."
    }
    userName = "jetbrains"
    password = "credentialsJSON:b17ce248-ac8a-4b16-840a-3af8f08cf27b"
    workspaceOptions = """
        Options:        noallwrite clobber nocompress unlocked nomodtime rmdir
        Host:           %teamcity.agent.hostname%
        SubmitOptions:  revertunchanged
        LineEnd:        local
    """.trimIndent()
})


object P1 : Project({
    name = "p1"

    subProject(P1_P3)
    subProject(P1_P2)
})


object P1_P2 : Project({
    name = "p2"
})


object P1_P3 : Project({
    name = "p3"
})


object Project11 : Project({
    name = "8"

    buildType(Project11_Build11)
})

object Project11_Build11 : BuildType({
    name = "build11"
})


object Sdf : Project({
    name = "sdf"
})
