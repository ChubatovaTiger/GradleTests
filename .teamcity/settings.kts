import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.amazonEC2CloudImage
import jetbrains.buildServer.configs.kotlin.amazonEC2CloudProfile

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

    features {
        amazonEC2CloudImage {
            id = "PROJECT_EXT_4"
            profileId = "amazon-1"
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
        amazonEC2CloudImage {
            id = "PROJECT_EXT_5"
            profileId = "amazon-1"
            agentPoolId = "-2"
            name = "im1 (1)"
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
                keyId = "credentialsJSON:ed3fa036-bfe4-47ed-8c26-cff55797ef8b"
                secretKey = "credentialsJSON:94f17856-50e9-4584-9b15-ef8a28fcda04"
            }
        }
    }
}
