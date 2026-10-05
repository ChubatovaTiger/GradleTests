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

version = "2026.1"

project {

    buildType(Build1)

    params {
        password("aa", "credentialsJSON:6f7805a5-3e46-4755-b190-60ef15e388b0")
        password("a", "credentialsJSON:dd710de3-b93b-4279-a5e8-e5b37c31b5c2")
    }
}

object Build1 : BuildType({
    name = "build1"

    params {
        param("a", "credentialsJSON:33da9097-88ce-443d-b61b-962c617f2f19")
    }
})
