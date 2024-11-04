package patches.buildTypes

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, change the buildType with id = 'Ssss'
accordingly, and delete the patch script.
*/
changeBuildType(RelativeId("Ssss")) {
    expectSteps {
    }
    steps {
        insert(0) {
            script {
                id = "simpleRunner"
                scriptContent = """echo "##teamcity[buildProblem description='blue' identity='red']""""
            }
        }
    }
}
