package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.parallelTests
import jetbrains.buildServer.configs.kotlin.matrix

object BothTmpl : BuildType({
    templates(Tmpl)
    name = "both_tmpl"

    features {
        parallelTests {
            id = "BUILD_EXT_1"
            numberOfBatches = 10
            param("groupArtifactsByBuild", "false")
        }
        matrix {
            id = "BUILD_EXT_2"
            param("par1", listOf(
                value("val 1"),
                value("val2")
            ))
            param("par2", listOf(
                value("val 1"),
                value("val 2")
            ))
        }
    }
})
