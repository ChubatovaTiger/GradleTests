import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.PullRequests
import jetbrains.buildServer.configs.kotlin.buildFeatures.pullRequests
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.matrix
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

    template(TmplPR)
    template(Ffftmpl)

    params {
        password("pwdpar1", "credentialsJSON:1c3675db-3f28-4ec7-ba35-813e25bc901b")
        password("pwdout", "credentialsJSON:474ea32a-7792-422e-9afa-9d863f42a746")
    }
}

object Build1 : BuildType({
    templates(Ffftmpl, TmplPR)
    name = "build1"

    params {
        param("par20", "2")
        password("pedpartmpl0", "credentialsJSON:132d34fb-2bbb-4c67-8edc-ea70dc93595b")
        param("usualtmpl4", "ddd")
        param("usualtmpl3", "ddd")
        param("usualtmpl6", "ddd")
        param("usualtmpl5", "ddd")
        param("usualtmpl0", "ddd")
        param("usualtmpl2", "ddd")
        param("usualtmpl1", "ddd")
        param("par10", "1")
        param("par11", "1")
        param("par12", "1")
        param("par13", "1")
        param("par14", "1")
        param("par15", "1")
        param("par16", "1")
        param("kk", """
            k
            kl
        """.trimIndent())
        param("pp1", "%pwdpar1%")
        param("pp0", "%pwdpar1%")
        param("pp3", "%pwdpar1%")
        param("pp2", "%pwdpar1%")
        param("pp5", "%pwdpar1%")
        password("sdf", "credentialsJSON:474ea32a-7792-422e-9afa-9d863f42a746")
        param("pp4", "%pwdpar1%")
        param("pp6", "%pwdpar1%")
    }

    outputParams {
        param("chkboxtmpl", "%chkboxtmpl%")
        param("env.AWS_ACCESS_KEY_ID", "%env.AWS_ACCESS_KEY_ID%")
        param("multiline", "%multiline%")
        param("multilineOwn", "%multilinePwd%")
        param("par1", "string")
        param("par16", "jaj")
        param("par1new", "string")
        param("par20", """
            %par20%
            kjkj
        """.trimIndent())
        param("par2000000", "nbvnbvnbv")
        param("pp3", "%pp3%")
        param("pp5", "%pp5%")
        param("pwdout", "******")
        param("teamcity.pullRequest.source.branch", "%teamcity.pullRequest.source.branch%")
    }

    steps {
        script {
            id = "simpleRunner"
            scriptContent = "sleep 30"
            param("org.jfrog.artifactory.selectedDeployableServer.downloadSpecSource", "Job configuration")
            param("org.jfrog.artifactory.selectedDeployableServer.useSpecs", "false")
            param("org.jfrog.artifactory.selectedDeployableServer.uploadSpecSource", "Job configuration")
        }
    }
    
    disableSettings("ArtifactoryDocker")
})

object Build2 : BuildType({
    templates(TmplPR)
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
    templates(TmplPR)
    name = "build3"

    buildNumberPattern = "%build.counter%-${Build1.depParamRefs["teamcity.build.id"]}"

    params {
        param("gg", "${Build1.depParamRefs["par1"]}")
    }

    vcs {
        showDependenciesChanges = true
    }

    steps {
        script {
            id = "simpleRunner"
            scriptContent = "echo ${Build1.depParamRefs["pwdout"]}"
            param("org.jfrog.artifactory.selectedDeployableServer.downloadSpecSource", "Job configuration")
            param("org.jfrog.artifactory.selectedDeployableServer.useSpecs", "false")
            param("org.jfrog.artifactory.selectedDeployableServer.uploadSpecSource", "Job configuration")
        }
    }

    triggers {
        schedule {
            id = "TRIGGER_1"
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

    features {
        matrix {
            id = "BUILD_EXT_1"
            enabled = false
            param("sdf", listOf(
                value("%dep.Project4_Build1.build.number%")
            ))
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
        checkbox("chkboxtmpl", "true",
                  checked = "true", unchecked = "false")
        param("par2", "2")
        param("multiline", """
            multiline
            value
        """.trimIndent())
        password("multilinePwd", "credentialsJSON:b00d5dfc-ad4b-481d-b231-6feabd0e8693")
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

object TmplPR : Template({
    name = "tmplPR"

    params {
        param("tpl1", "1")
    }

    outputParams {
        param("tmpl2", "1")
    }

    features {
        pullRequests {
            id = "BUILD_EXT_2"
            vcsRootExtId = "${DslContext.settingsRoot.id}"
            provider = github {
                authType = vcsRoot()
                filterAuthorRole = PullRequests.GitHubRoleFilter.MEMBER
            }
        }
    }
})
