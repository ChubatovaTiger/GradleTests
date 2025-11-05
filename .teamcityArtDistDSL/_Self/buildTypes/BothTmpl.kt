package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.parallelTests

object BothTmpl : BuildType({
    templates(Tmpl)
    name = "both_tmpl"

    features {
        parallelTests {
            id = "BUILD_EXT_1"
            numberOfBatches = 10
        }
    }
})
