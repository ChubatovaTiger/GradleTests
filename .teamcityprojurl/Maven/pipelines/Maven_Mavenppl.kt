package Maven.pipelines

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.pipelines.*
import jetbrains.buildServer.configs.kotlin.triggers.vcs

object Maven_Mavenppl : Pipeline({
    name = "mavenppl"

    repositories {
        repository(Maven.vcsRoots.Maven_GitGithubComChubatovaTigerMavenJunitGitRefsHeadsMain)
    }

    triggers {
        vcs {
        }
    }

    job(Maven_Mavenppl_Job1)
})

object Maven_Mavenppl_Job1 : Job({
    id("Job1")
    name = "Job 1"
})
