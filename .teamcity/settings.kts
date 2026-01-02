import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.nuGetFeedCredentials
import jetbrains.buildServer.configs.kotlin.buildSteps.script

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2024.03"

project {

    buildType(Build2_2)
    buildType(Build2)
    buildType(Build)
    buildType(Build_2)
}

object Build : BuildType({
    name = "build"

    params {
        param("par1", "1")
    }

    vcs {
        root(DslContext.settingsRoot)
    }

    features {
        nuGetFeedCredentials {
            feedUrl = "http://localhost:8111/httpAuth/app/nuget/feed/_Root/wer/v3/index.json"
            username = "admin"
            password = "credentialsJSON:f27ff0cb-f72f-49a2-b06e-b5ef549b52c6"
        }
    }
})

object Build2 : BuildType({
    name = "build2"

    vcs {
        root(DslContext.settingsRoot)

        showDependenciesChanges = true
    }

    steps {
        script {
            id = "simpleRunner"
            scriptContent = "echo ${Build.depParamRefs.buildNumber}"
        }
    }

    features {
        nuGetFeedCredentials {
            feedUrl = "http://localhost:8111/httpAuth/app/nuget/feed/_Root/wer/v3/index.json"
            username = "admin"
            password = "credentialsJSON:f27ff0cb-f72f-49a2-b06e-b5ef549b52c6"
        }
    }

    dependencies {
        snapshot(Build) {
            reuseBuilds = ReuseBuilds.NO
        }
    }
})

object Build2_2 : BuildType({
    name = "build2 (1)"

    vcs {
        root(DslContext.settingsRoot)

        showDependenciesChanges = true
    }

    steps {
        script {
            id = "simpleRunner"
            scriptContent = "echo ${Build.depParamRefs.buildNumber}"
        }
    }

    features {
        nuGetFeedCredentials {
            feedUrl = "http://localhost:8111/httpAuth/app/nuget/feed/_Root/wer/v3/index.json"
            username = "admin"
            password = "credentialsJSON:f27ff0cb-f72f-49a2-b06e-b5ef549b52c6"
        }
    }

    dependencies {
        snapshot(Build) {
            reuseBuilds = ReuseBuilds.NO
        }
    }
})

object Build_2 : BuildType({
    name = "build (1)"

    params {
        param("par1", "1")
    }

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        script {
            id = "simpleRunner"
            scriptContent = "echo ${Build.depParamRefs.buildNumber}"
        }
    }

    features {
        nuGetFeedCredentials {
            feedUrl = "http://localhost:8111/httpAuth/app/nuget/feed/_Root/wer/v3/index.json"
            username = "admin"
            password = "credentialsJSON:f27ff0cb-f72f-49a2-b06e-b5ef549b52c6"
        }
    }

    dependencies {
        snapshot(Build2) {
            reuseBuilds = ReuseBuilds.NO
        }
    }
})
