import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.vcs.GitVcsRoot

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

    vcsRoot(Root1)

    buildType(Build1)

    params {
        password("par1", "credentialsJSON:2992eeea-8945-4755-a537-542bf902d5af")
    }
}

object Build1 : BuildType({
    name = "Build1"

    artifactRules = "1.txt"

    params {
        param("branch", "master")
    }

    steps {
        script {
            id = "simpleRunner"
            scriptContent = "echo %par1% > 1.txt"
        }
    }
})

object Root1 : GitVcsRoot({
    name = "root1"
    url = "git@github.com:ChubatovaTiger/mavenJunit.git"
    branch = "refs/heads/main"
    authMethod = uploadedKey {
        uploadedKey = "rsaopensshnew"
        passphrase = "credentialsJSON:ee431e46-41fc-4b58-bffd-3a60c69b07aa"
    }
})
