package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.matrix

object Buidl1 : BuildType({
    name = "buidl1"

    artifactRules = """
        *.txt => a/b/c/1.zip
        1.txt => 2.zip
    """.trimIndent()

    steps {
        script {
            id = "simpleRunner"
            scriptContent = """
                echo %build.number% > 1.txt
                echo %build.number% > 2.txt
            """.trimIndent()
        }
    }

    features {
        matrix {
            param("par1", listOf(
                value("val11"),
                value("val12")
            ))
            param("PAr2", listOf(
                value("val 2 1"),
                value("val 2.2")
            ))
            param("d", listOf(
                value("dg")
            ))
            param("f", listOf(
                value("1"),
                value("2")
            ))
        }
    }
})
