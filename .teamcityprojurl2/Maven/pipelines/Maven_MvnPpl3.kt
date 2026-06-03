package Maven.pipelines

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.pipelines.*
import jetbrains.buildServer.configs.kotlin.triggers.vcs

object Maven_MvnPpl3 : Pipeline({
    name = "mvnPpl3"

    repositories {
        repository(Maven.vcsRoots.Maven_HttpsGithubComChubatovaTigerMavenJunitRefsHeadsMain)
    }

    triggers {
        vcs {
        }
    }

    job(Maven_MvnPpl3_Job1)
})

object Maven_MvnPpl3_Job1 : Job({
    id("Job1")
    name = "Job 1"
})
