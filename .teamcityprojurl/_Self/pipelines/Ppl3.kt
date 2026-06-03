package _Self.pipelines

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.pipelines.*
import jetbrains.buildServer.configs.kotlin.triggers.vcs

object Ppl3 : Pipeline({
    name = "ppl3"

    repositories {
        repository(DslContext.settingsRoot)
    }

    triggers {
        vcs {
        }
    }

    job(Ppl3_Job1)
    job(Ppl3_Job2)
})

object Ppl3_Job1 : Job({
    id("Job1")
    name = "Job 1"
    allowReuse = false

    steps {
        script {
            scriptContent = "echo a > a.txt"
        }
    }

    outputFiles {
        sharedWithJobs("a.txt")
        pipelineArtifacts("a.txt")
    }
})

object Ppl3_Job2 : Job({
    id("Job2")
    name = "Job 2"

    requirements {
        doesNotEqual("teamcity.agent.jbHosted", "true")
    }
})
