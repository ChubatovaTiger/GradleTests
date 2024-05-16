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

version = "2024.03"

project {

    params {
        password("par1", "credentialsJSON:47ee8cb4-be9a-433a-9833-bda5a9096064")
        password("par2", "credentialsJSON:321c8c7f-4941-4042-8c02-61d1711a0283")
        password("par3", "credentialsJSON:fe8cbe53-1728-4cb7-9f0d-7e20c13ee298")
    }
}
