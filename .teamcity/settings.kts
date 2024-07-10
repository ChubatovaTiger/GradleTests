import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.amazonEC2CloudImage
import jetbrains.buildServer.configs.kotlin.amazonEC2CloudProfile
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.matrix

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

    buildType(Vulgar)
    buildType(Build1)
    buildType(Build2)

    features {
        amazonEC2CloudImage {
            id = "PROJECT_EXT_5"
            profileId = "amazon-1"
            agentPoolId = "1"
            name = "im1"
            vpcSubnetId = "subnet-043178c302cabfe37"
            keyPairName = "chubatova-amazon-ireland"
            instanceType = "t3.medium"
            securityGroups = listOf("sg-072d8bfa0626ea2a6")
            instanceTags = mapOf(
                "Owner" to "nastasia.chubatova@jetbrains.com"
            )
            source = Source("ami-0b55e6eb21b887f36")
        }
        amazonEC2CloudProfile {
            id = "amazon-1"
            name = "profile1"
            terminateIdleMinutes = 30
            region = AmazonEC2CloudProfile.Regions.EU_WEST_DUBLIN
            authType = accessKey {
                keyId = "credentialsJSON:fdacaedc-b79a-44f5-bc41-0a61e837ffd8"
                secretKey = "credentialsJSON:e7d5edd0-f704-44df-9456-81cca173f56e"
            }
        }
    }
}

object Build1 : BuildType({
    name = "build1"

    params {
        param("abc", "b")
    }

    steps {
        script {
            id = "simpleRunner"
            scriptContent = "echo a"
        }
    }

    features {
        matrix {
            param("env.m1", listOf(
                value("val1"),
                value("val3")
            ))
        }
    }
})

object Build2 : BuildType({
    name = "build2"

    buildNumberPattern = "%build.counter%-2"

    params {
        param("nn", "1")
        param("r1", "2")
    }

    vcs {
        root(DslContext.settingsRoot)
    }

    features {
        matrix {
            param("par1", listOf(
                value("val1"),
                value("val2")
            ))
        }
    }
})

object Vulgar : BuildType({
    name = "vulgar"

    params {
        param("a", "2")
    }
})
