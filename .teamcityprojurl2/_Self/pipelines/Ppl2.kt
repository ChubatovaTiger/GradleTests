package _Self.pipelines

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.pipelines.*
import jetbrains.buildServer.configs.kotlin.triggers.vcs

object Ppl2 : Pipeline({
    name = "ppl2"

    repositories {
        repository(DslContext.settingsRoot)
    }

    triggers {
        vcs {
        }
    }

    job(Ppl2_Job1)
})

object Ppl2_Job1 : Job({
    id("Job1")
    name = "Job 1"

    steps {
        script {
            scriptContent = "echo a"
        }
    }

    requirements {
        doesNotEqual("teamcity.agent.jbHosted", "true")
        doesNotEqual("teamcity.agent.jbHosted", "true")
        doesNotEqual("teamcity.agent.jbHosted", "true")
        doesNotEqual("teamcity.agent.jbHosted", "true")
        equals("teamcity.agent.jbHosted", "true")
        startsWith("system.agent.name", "Linux-Small")
    }
})
