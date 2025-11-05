package _Self

import _Self.buildTypes.*
import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.Project

object Project : Project({

    buildType(Partest)
    buildType(Both)
    buildType(BothTmpl)
    buildType(Mtrx2)
    buildType(Buidl1)

    template(Tmpl)
})
