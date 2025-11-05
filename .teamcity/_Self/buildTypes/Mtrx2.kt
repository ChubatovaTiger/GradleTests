package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.matrix

object Mtrx2 : BuildType({
    name = "mtrx2"

    features {
        matrix {
            param("par1", listOf(
                value("1"),
                value("2")
            ))
        }
    }
})
