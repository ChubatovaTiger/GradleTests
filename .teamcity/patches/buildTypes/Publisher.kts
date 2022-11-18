package patches.buildTypes

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.triggers.ScheduleTrigger
import jetbrains.buildServer.configs.kotlin.triggers.schedule
import jetbrains.buildServer.configs.kotlin.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, change the buildType with id = 'Publisher'
accordingly, and delete the patch script.
*/
changeBuildType(RelativeId("Publisher")) {
    triggers {
        val trigger1 = find<ScheduleTrigger> {
            schedule {
                schedulingPolicy = daily {
                    hour = 17
                }
                branchFilter = "+:<default>"
                triggerBuild = always()
                withPendingChangesOnly = false
                param("cronExpression_min", "/2")

                enforceCleanCheckout = true
                enforceCleanCheckoutForDependencies = true
                buildParams {
                    param("rebuildDependencies", "force")
                }
            }
        }
        trigger1.apply {
            schedulingPolicy = cron {
                seconds = "0"
                minutes = "59"
                hours = "13"
                dayOfMonth = "*"
                dayOfWeek = "?"
                month = "*"
                year = "*"
                timezone = "SERVER"
            }
            clearBuildParams()
            buildParams {
                param("rebuildDependencies", "force")
            }
        }
    }
}
