import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.triggers.schedule

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

    buildType(Build1)
    buildType(Build3)
    buildType(Build2)

    template(Ffftmpl)

    params {
        password("pwdpar1", "credentialsJSON:1c3675db-3f28-4ec7-ba35-813e25bc901b")
    }
}

object Build1 : BuildType({
    templates(Ffftmpl)
    name = "build1"

    outputParams {
        param("chkboxtmpl", "%chkboxtmpl%")
    }
    
    disableSettings("ArtifactoryDocker")
})

object Build2 : BuildType({
    name = "build2"

    type = BuildTypeSettings.Type.COMPOSITE

    outputParams {
        exposeAllParameters = false
        param("bu", "${Build1.depParamRefs["par1"]}")
    }

    vcs {
        showDependenciesChanges = true
    }

    dependencies {
        snapshot(Build1) {
            reuseBuilds = ReuseBuilds.NO
        }
    }
})

object Build3 : BuildType({
    name = "build3"

    type = BuildTypeSettings.Type.COMPOSITE
    buildNumberPattern = "%build.counter%-${Build1.depParamRefs["chkboxtmpl"]}"

    params {
        param("gg", "${Build1.depParamRefs["par1"]}")
    }

    vcs {
        showDependenciesChanges = true
    }

    steps {
        script {
            id = "simpleRunner"
            scriptContent = "echo ${Build2.depParamRefs["bu"]}"
        }
    }

    triggers {
        schedule {
            schedulingPolicy = daily {
                hour = 17
                minute = 45
            }
            branchFilter = ""
            triggerBuild = always()
            withPendingChangesOnly = false

            buildParams {
                param("ass", "${Build1.depParamRefs["par2"]}")
            }
        }
    }

    dependencies {
        snapshot(Build2) {
            reuseBuilds = ReuseBuilds.NO
        }
    }
})

object Ffftmpl : Template({
    name = "ffftmpl"

    params {
        param("pp", "%pwdpar1%")
        param("usualtmpl", "ddd")
        param("par1", "1")
        password("pedpartmpl", "credentialsJSON:132d34fb-2bbb-4c67-8edc-ea70dc93595b")
        param("par2", "2")
        checkbox("chkboxtmpl", "true",
                  checked = "true", unchecked = "false")
    }

    outputParams {
        exposeAllParameters = false
        param("par1", "%par1%")
        param("pp", "%pp%")
    }

    steps {
        step {
            id = "ArtifactoryDocker"
            type = "ArtifactoryDocker"
            param("org.jfrog.artifactory.selectedDeployableServer.dockerCommand", "PULL")
            param("org.jfrog.artifactory.selectedDeployableServer.urlId", "0")
            param("org.jfrog.artifactory.selectedDeployableServer.dockerImageName", "g")
        }
    }
})
