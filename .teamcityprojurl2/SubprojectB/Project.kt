package SubprojectB

import SubprojectB.buildTypes.*
import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.Project

object Project : Project({
    id("SubprojectB")
    name = "subprojectB"

    buildType(SubprojectB_Build)

    params {
        param("teamcity.project.server_url", "a")
    }
})
