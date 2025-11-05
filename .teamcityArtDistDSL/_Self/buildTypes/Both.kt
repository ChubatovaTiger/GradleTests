package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.*

object Both : BuildType({
    templates(Tmpl)
    name = "both"
})
