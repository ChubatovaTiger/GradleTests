package Project1.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.ant

object Project1_Build1 : BuildType({
    uuid = "9ef5aa98-9ff7-4dcf-8759-8c8b3fce64e7"
    name = "build1"

    steps {
        ant {
            id = "Ant"
            mode = antFile {
            }
        }
    }

    requirements {
        noLessThan("teamcity.agent.hardware.cpuCount", "8")
    }
})
