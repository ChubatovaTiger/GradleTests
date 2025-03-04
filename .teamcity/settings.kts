import jetbrains.buildServer.configs.kotlin.*

import jetbrains.buildServer.configs.kotlin.buildFeatures.PullRequests
import jetbrains.buildServer.configs.kotlin.buildFeatures.pullRequests
import jetbrains.buildServer.configs.kotlin.vcs.GitVcsRoot
import jetbrains.buildServer.configs.kotlin.triggers.vcs

version = "2021.2"

project {




    val numProjects=2
    val numConfigurationsPerProject=40


    for (i in 0..numProjects) {
        subProject {
            id("subProj_$i")
            name = "subProj $i"

            for (j in 0..numConfigurationsPerProject) {
                buildType {
                    id("subProj_bt_$i" + "_$j")
                    name = "bt $i $j"
                    


                }
            }
        }
    }
}







