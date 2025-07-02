package Project1.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.freeDiskSpace
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.ant
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script

object Project1_Build1 : BuildType({
    uuid = "9ef5aa98-9ff7-4dcf-8759-8c8b3fce64e7"
    name = "build1"

    params {
        password("remote.cache.upload.token", "credentialsJSON:3e8c2bb6-e485-48a8-8cf0-98923243e5fe")
    }

    vcs {
        root(Project1.vcsRoots.Project1_HttpsGithubComChubatovaTigerRepo)
    }

    steps {
        script {
            id = "simpleRunner"
            scriptContent = "echo a"
        }
        ant {
            id = "Ant"
            mode = antFile {
            }
        }
    }

    features {
        freeDiskSpace {
            failBuild = false
        }
    }

    requirements {
        noLessThan("teamcity.agent.hardware.cpuCount", "8")
    }
})
