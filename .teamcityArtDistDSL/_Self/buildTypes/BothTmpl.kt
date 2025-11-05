package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.*

object BothTmpl : BuildType({
    templates(Tmpl)
    name = "both_tmpl"
})
