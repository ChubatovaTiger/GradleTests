package Python

import Python.buildTypes.*
import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.Project

object Project : Project({
    id("Python")
    name = "python"

    buildType(Python_Python)
})
