package patches.buildTypes

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.approval
import jetbrains.buildServer.configs.kotlin.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, change the buildType with id = 'Build1'
accordingly, and delete the patch script.
*/
changeBuildType(RelativeId("Build1")) {
    params {
        add {
            param("a", "a")
        }
    }

    features {
        add {
            approval {
                approvalRules = "user:user1"
                timeout = 1
                manualRunsApproved = false
            }
        }
    }
}
