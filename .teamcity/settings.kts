import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.projectFeatures.activeStorage
import jetbrains.buildServer.configs.kotlin.projectFeatures.awsConnection
import jetbrains.buildServer.configs.kotlin.projectFeatures.s3Storage

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

version = "2024.07"

project {

    features {
        awsConnection {
            id = "AmazonWebServicesAws"
            name = "Amazon Web Services (AWS)"
            regionName = "eu-west-1"
            credentialsType = static {
                accessKeyId = "AKIA5JH2VERVDN3XW5GF"
                secretAccessKey = "credentialsJSON:61577a37-2621-4f7a-b757-893b9dab7a6b"
                stsEndpoint = "https://sts.eu-west-1.amazonaws.com"
            }
        }
        s3Storage {
            id = "PROJECT_EXT_5"
            storageName = "nm"
            bucketName = "chubatovatransferacceleration"
            bucketPrefix = "prefprefchubatova1109"
            forceVirtualHostAddressing = true
            awsEnvironment = default {
                awsRegionName = "eu-central-1"
            }
            connectionId = "AmazonWebServicesAws"
        }
        activeStorage {
            id = "PROJECT_EXT_6"
            activeStorageID = "PROJECT_EXT_5"
        }
    }

    subProject(Proj5)
    subProject(Proj4)
}


object Proj4 : Project({
    name = "proj4"

    buildType(Proj4_Build1)
    buildType(Proj4_Build3a)
})

object Proj4_Build1 : BuildType({
    name = "build1"

    artifactRules = "a*.txt"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        script {
            id = "simpleRunner"
            scriptContent = "echo a > a%system.teamcity.buildType.id%.%build.number%.txt"
        }
    }
})

object Proj4_Build3a : BuildType({
    name = "build3"

    artifactRules = "a*.txt"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        script {
            id = "simpleRunner"
            scriptContent = "echo a > a%system.teamcity.buildType.id%.%build.number%.txt"
        }
    }
})


object Proj5 : Project({
    name = "proj5"

    buildType(Proj5_Build1)
    buildType(Proj5_Build3)
    buildType(Proj5_Build2)
})

object Proj5_Build1 : BuildType({
    name = "build1"

    artifactRules = "a*.txt"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        script {
            id = "simpleRunner"
            scriptContent = "echo a > a%system.teamcity.buildType.id%.%build.number%.txt"
        }
    }
})

object Proj5_Build2 : BuildType({
    name = "build2"

    artifactRules = "a*.txt"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        script {
            id = "simpleRunner"
            scriptContent = "echo a > a%system.teamcity.buildType.id%.%build.number%.txt"
        }
    }
})

object Proj5_Build3 : BuildType({
    name = "build3"

    artifactRules = "a*.txt"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        script {
            id = "simpleRunner"
            scriptContent = "echo a > a%system.teamcity.buildType.id%.%build.number%.txt"
        }
    }
})
