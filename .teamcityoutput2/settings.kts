import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.projectFeatures.hashiCorpVaultConnection
import jetbrains.buildServer.configs.kotlin.remoteParameters.hashiCorpVaultParameter

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

version = "2024.12"

project {

    buildType(Build3)
    buildType(Build2)
    buildType(Build1)

    template(Tmpl1)

    params {
        param("fromproj", "k")
    }

    features {
        hashiCorpVaultConnection {
            id = "hashicorpVaultConnection1"
            name = "blue Vault"
            url = "https://vault.intellij.net"
            authMethod = appRole {
                roleId = "secrets-teamcity-qa"
                secretId = "credentialsJSON:db8690ed-2229-40d8-b7e7-b7099046954d"
            }
        }
    }

    subProject(Subproj2)
}

object Build1 : BuildType({
    templates(Tmpl1)
    name = "build1"

    outputParams {
        param("usual1", "usuak1")
        param("usualtmpl", "%usualtmpl%")
    }
})

object Build2 : BuildType({
    name = "build2"

    outputParams {
        exposeAllParameters = false
        param("fromproj", "%gittoken%")
    }

    vcs {
        root(DslContext.settingsRoot)

        showDependenciesChanges = true
    }

    steps {
        script {
            id = "simpleRunner"
            scriptContent = "echo ${Build1.depParamRefs["usual"]}"
        }
    }

    dependencies {
        snapshot(Build1) {
            reuseBuilds = ReuseBuilds.NO
        }
    }
})

object Build3 : BuildType({
    name = "build3"

    vcs {
        root(DslContext.settingsRoot)

        showDependenciesChanges = true
    }

    steps {
        script {
            id = "simpleRunner"
            scriptContent = "echo ${Build2.depParamRefs["fromproj"]}"
        }
    }

    dependencies {
        snapshot(Build2) {
            reuseBuilds = ReuseBuilds.NO
        }
    }
})

object Tmpl1 : Template({
    name = "tmpl1"

    artifactRules = "a.txt"

    params {
        password("pwd1", "credentialsJSON:f3eb63ea-5c2d-461a-abe9-bc1a9ac68968")
        param("usualtmpl", "usualtmplvalue")
        param("usual", "usualVal")
        hashiCorpVaultParameter {
            name = "k1"
            query = "secrets/data/teamcity-qa/tc-qa-test-infrastructure!/tcSpaceServiceAccSshPubKey"
            vaultId = "hashicorpVaultConnection1"
        }
    }

    outputParams {
        exposeAllParameters = false
        param("k1", "%k1%")
        param("output1", "outputval1")
        param("usual", "%usual%")
    }

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        script {
            id = "simpleRunner"
            scriptContent = "echo %k1% > a.txt"
        }
    }
})


object Subproj2 : Project({
    name = "subproj2"
})
