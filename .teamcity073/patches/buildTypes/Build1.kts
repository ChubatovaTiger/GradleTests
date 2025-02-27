package patches.buildTypes

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, change the buildType with id = 'Build1'
accordingly, and delete the patch script.
*/
changeBuildType(RelativeId("Build1")) {
    check(description == "") {
        "Unexpected description: '$description'"
    }
    description = "hello"

    check(type == BuildTypeSettings.Type.REGULAR) {
        "Unexpected option value: type = $type"
    }
    type = BuildTypeSettings.Type.COMPOSITE

    vcs {

        check(cleanCheckout == false) {
            "Unexpected option value: cleanCheckout = $cleanCheckout"
        }
        cleanCheckout = true

        check(showDependenciesChanges == false) {
            "Unexpected option value: showDependenciesChanges = $showDependenciesChanges"
        }
        showDependenciesChanges = true
    }

    dependencies {
        add(AbsoluteId("Tests_Tests")) {
            snapshot {
            }
        }

    }
}
