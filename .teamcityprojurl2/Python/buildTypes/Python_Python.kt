package Python.buildTypes

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.python

object Python_Python : BuildType({
    name = "python"

    steps {
        python {
            id = "python_runner"
            command = script {
                content = """print("hello world")"""
            }
            dockerImage = "python"
        }
    }
})
