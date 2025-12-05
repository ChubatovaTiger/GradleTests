import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.commitStatusPublisher
import jetbrains.buildServer.configs.kotlin.buildFeatures.pullRequests
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.triggers.schedule
import jetbrains.buildServer.configs.kotlin.triggers.vcs
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

version = "2025.11"

project {

    vcsRoot(HttpsBbdatacenterQaTeamcityComScmChubChubatovarepoGit)
    vcsRoot(HttpLocalhost7990scmChubChubatovarepoGit)
    vcsRoot(Secondarepo)
    vcsRoot(HttpLocalhost7991scmChubChubatovarepoGit)

    buildType(CommitStatusBitBUcket723)
    buildType(CommitStatusBitBUcket)
    buildType(CommitStatusBB85)
    buildType(Github)

    subProject(Bbsubpr1)
    subProject(Gitlab)
    subProject(Githubtiger)
}

object CommitStatusBB85 : BuildType({
    name = "CommitStatusBB8.5"

    vcs {
        root(HttpsBbdatacenterQaTeamcityComScmChubChubatovarepoGit, "+. => bu")
    }

    triggers {
        schedule {
            schedulingPolicy = daily {
                hour = 15
            }
            branchFilter = ""
            triggerBuild = always()
        }
    }

    failureConditions {
        javaCrash = false
    }

    features {
        commitStatusPublisher {
            publisher = bitbucketServer {
                url = "https://bbdatacenter.qa.teamcity.com"
            }
            param("secure:stashPassword", "credentialsJSON:5ad8bedd-f53d-4c13-96f8-4ce2db050705")
            param("stashUsername", "admin")
        }
    }
})

object CommitStatusBitBUcket : BuildType({
    name = "CommitStatusBitBUcket"

    vcs {
        root(Secondarepo, "+:ckr => .")
    }

    steps {
        script {
            scriptContent = "exit 1"
        }
    }

    features {
        commitStatusPublisher {
            publisher = bitbucketServer {
                url = "http://localhost:7990"
            }
            param("secure:stashPassword", "credentialsJSON:2f7cf5b5-9f65-4906-8df8-8460768267d2")
            param("stashUsername", "admin")
        }
        pullRequests {
            vcsRootExtId = "${Secondarepo.id}"
            provider = bitbucketServer {
                authType = vcsRoot()
                filterTargetBranch = "+:refs/heads/master"
                usePullRequestBranches = true
            }
        }
    }

    requirements {
        contains("teamcity.agent.name", "Default Agent")
    }
})

object CommitStatusBitBUcket723 : BuildType({
    name = "CommitStatusBitBUcket7.2.3"

    vcs {
        root(HttpLocalhost7991scmChubChubatovarepoGit)
    }

    steps {
        script {
            scriptContent = "ls"
        }
    }

    features {
        commitStatusPublisher {
            vcsRootExtId = "${HttpLocalhost7991scmChubChubatovarepoGit.id}"
            publisher = bitbucketServer {
                url = "http://localhost:7991"
            }
            param("secure:stashPassword", "credentialsJSON:4d85de21-4c34-4888-85d2-fedb2fc55fce")
            param("stashUsername", "AChubatova")
        }
        pullRequests {
            vcsRootExtId = "${HttpLocalhost7991scmChubChubatovarepoGit.id}"
            provider = bitbucketServer {
                authType = vcsRoot()
                usePullRequestBranches = true
            }
        }
    }

    requirements {
        contains("teamcity.agent.name", "Default Agent")
    }
})

object Github : BuildType({
    name = "github"

    vcs {
        root(DslContext.settingsRoot)
    }

    features {
        commitStatusPublisher {
            vcsRootExtId = "${DslContext.settingsRoot.id}"
            publisher = github {
                githubUrl = "https://api.github.com"
                authType = personalToken {
                    token = "credentialsJSON:d55ac7e1-70e9-43d5-a101-541aef084a29"
                }
            }
        }
    }
})

object HttpLocalhost7990scmChubChubatovarepoGit : GitVcsRoot({
    name = "http://localhost:7990/scm/chub/chubatovarepo.git"
    url = "http://localhost:7990/scm/chub/chubatovarepo.git"
    branch = "refs/heads/master"
    branchSpec = """
        refs/heads/(*)
        +:refs/(pull-requests/*)
    """.trimIndent()
    authMethod = password {
        userName = "AChubatova"
        password = "credentialsJSON:4d85de21-4c34-4888-85d2-fedb2fc55fce"
    }
})

object HttpLocalhost7991scmChubChubatovarepoGit : GitVcsRoot({
    name = "http://localhost:7991/scm/chub/chubatovarepo.git"
    url = "http://localhost:7991/scm/chub/chubatovarepo.git"
    branch = "refs/heads/master"
    branchSpec = "refs/heads/(*)"
    authMethod = password {
        userName = "AChubatova"
        password = "credentialsJSON:4d85de21-4c34-4888-85d2-fedb2fc55fce"
    }
})

object HttpsBbdatacenterQaTeamcityComScmChubChubatovarepoGit : GitVcsRoot({
    name = "https://bbdatacenter.qa.teamcity.com/scm/chub/chubatovarepo.git"
    url = "https://bbdatacenter.qa.teamcity.com/scm/chub/chubatovarepo.git"
    branch = "refs/heads/boss"
    authMethod = password {
        userName = "admin"
        password = "credentialsJSON:5ad8bedd-f53d-4c13-96f8-4ce2db050705"
    }
})

object Secondarepo : GitVcsRoot({
    name = "secondarepo"
    url = "http://localhost:7990/scm/chub/chubatovasecondrepo.git"
    branch = "refs/heads/abc.los/r"
    branchSpec = "refs/heads/(*)"
    authMethod = password {
        userName = "admin"
        password = "credentialsJSON:2f7cf5b5-9f65-4906-8df8-8460768267d2"
    }
})


object Bbsubpr1 : Project({
    name = "bbs ubpr1"

    subProject(Bbsubpr1_Bbsuprbbsuprbbsuprbbsuprbbsupr2)
})


object Bbsubpr1_Bbsuprbbsuprbbsuprbbsuprbbsupr2 : Project({
    name = "bbsuprbbsuprbbsuprbbsuprbbsupr2"

    subProject(Bbsubpr1_Bbsuprbbsuprbbsuprbbsuprbbsupr2_Bbsuprbbsuprbbsuprbbsuprbbsupr3)
})


object Bbsubpr1_Bbsuprbbsuprbbsuprbbsuprbbsupr2_Bbsuprbbsuprbbsuprbbsuprbbsupr3 : Project({
    name = "bbsuprbbsuprbbsuprbbsuprbbsupr3"

    subProject(Bbsubpr1_Bbsuprbbsuprbbsuprbbsuprbbsupr2_Bbsuprbbsuprbbsuprbbsuprbbsupr3_Bbsuprbbsuprbbsuprbbsuprbbsupr4)
})


object Bbsubpr1_Bbsuprbbsuprbbsuprbbsuprbbsupr2_Bbsuprbbsuprbbsuprbbsuprbbsupr3_Bbsuprbbsuprbbsuprbbsuprbbsupr4 : Project({
    name = "bbsuprbbsuprbbsuprbbsuprbbsupr4"

    buildType(Bbsubpr1_Bbsuprbbsuprbbsuprbbsuprbbsupr2_Bbsuprbbsuprbbsuprbbsuprbbsupr3_Bbsuprbbsuprbbsuprbbsuprbbsupr4_CommitStatusBb85)
})

object Bbsubpr1_Bbsuprbbsuprbbsuprbbsuprbbsupr2_Bbsuprbbsuprbbsuprbbsuprbbsupr3_Bbsuprbbsuprbbsuprbbsuprbbsupr4_CommitStatusBb85 : BuildType({
    name = "CommitStatus BB8.5"

    vcs {
        root(HttpsBbdatacenterQaTeamcityComScmChubChubatovarepoGit)

        cleanCheckout = true
        showDependenciesChanges = true
    }

    steps {
        script {
            name = "step1"
            id = "step1"
            scriptContent = "echo a"
        }
        script {
            name = "step2"
            id = "step2"
            scriptContent = "echo b"
        }
    }

    triggers {
        schedule {
            branchFilter = ""
            triggerBuild = always()
            withPendingChangesOnly = false
        }
        vcs {
            branchFilter = ""
            perCheckinTriggering = true
            groupCheckinsByCommitter = true
            enableQueueOptimization = false
        }
    }

    failureConditions {
        javaCrash = false
    }

    features {
        commitStatusPublisher {
            publisher = bitbucketServer {
                url = "https://bbdatacenter.qa.teamcity.com"
            }
            param("secure:stashPassword", "credentialsJSON:5ad8bedd-f53d-4c13-96f8-4ce2db050705")
            param("stashUsername", "admin")
        }
    }

    dependencies {
        snapshot(AbsoluteId("id73083_73083")) {
        }
    }

    requirements {
        exists("env.DBUS_SESSION_BUS_ADDRESS")
    }
})


object Githubtiger : Project({
    name = "githubtiger"

    buildType(Githubtiger_Githublion)
})

object Githubtiger_Githublion : BuildType({
    name = "githublion"

    vcs {
        root(DslContext.settingsRoot)
    }

    features {
        commitStatusPublisher {
            vcsRootExtId = "${DslContext.settingsRoot.id}"
            publisher = github {
                githubUrl = "https://api.github.com"
                authType = personalToken {
                    token = "credentialsJSON:d55ac7e1-70e9-43d5-a101-541aef084a29"
                }
            }
        }
    }
})


object Gitlab : Project({
    name = "gitlab"

    vcsRoot(Gitlab_HttpsGithubComChubatovaTigerManyBranches)
    vcsRoot(Gitlab_HttpsGitlabComAChubatovaPythonIni)

    buildType(Gitlab_Gitlab)
    buildType(Gitlab_Lkl)
})

object Gitlab_Gitlab : BuildType({
    name = "gitlab"

    vcs {
        root(Gitlab_HttpsGitlabComAChubatovaPythonIni)
    }

    features {
        commitStatusPublisher {
            publisher = gitlab {
                gitlabApiUrl = "https://gitlab.com/api/v4"
            }
            param("secure:gitlabAccessToken", "credentialsJSON:50dab840-e869-45ee-8c55-9cc73c11de1c")
        }
    }
})

object Gitlab_Lkl : BuildType({
    name = "lkl"
    paused = true

    vcs {
        root(AbsoluteId("AwsCodeCommitPem"))
        root(Gitlab_HttpsGithubComChubatovaTigerManyBranches)
    }

    steps {
        script {
            id = "simpleRunner"
            scriptContent = """echo "OutOfMemoryError""""
        }
    }

    triggers {
        schedule {
            triggerBuild = always()
            withPendingChangesOnly = false
        }
    }
})

object Gitlab_HttpsGithubComChubatovaTigerManyBranches : GitVcsRoot({
    name = "https://github.com/ChubatovaTiger/manyBranches"
    url = "https://github.com/ChubatovaTiger/manyBranches"
    branch = "refs/heads/12e"
    branchSpec = "refs/heads/(*)"
})

object Gitlab_HttpsGitlabComAChubatovaPythonIni : GitVcsRoot({
    name = "https://gitlab.com/AChubatova/pythonIni"
    url = "https://gitlab.com/AChubatova/pythonIni.git"
    branch = "refs/heads/master"
    authMethod = password {
        userName = "AChubatova"
        password = "credentialsJSON:50dab840-e869-45ee-8c55-9cc73c11de1c"
    }
})
