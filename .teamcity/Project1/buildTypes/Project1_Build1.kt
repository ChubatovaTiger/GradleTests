package Project1.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.ant
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script

object Project1_Build1 : BuildType({
    uuid = "9ef5aa98-9ff7-4dcf-8759-8c8b3fce64e7"
    name = "build1"

    params {
        password("a.b.c.c", "credentialsJSON:a23964f3-28ab-41a9-901d-6269f684db6f")
    }

    steps {
        ant {
            id = "Ant"
            mode = antFile {
            }
        }
        script {
            id = "simpleRunner"
            scriptContent = "echo a"
        }
    }
})
