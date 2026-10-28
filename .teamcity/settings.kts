import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.amazonEC2CloudImage
import jetbrains.buildServer.configs.kotlin.amazonEC2CloudProfile
import jetbrains.buildServer.configs.kotlin.projectFeatures.kubernetesConnection
import jetbrains.buildServer.configs.kotlin.projectFeatures.kubernetesExecutor

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

version = "2026.2"

project {

    features {
        amazonEC2CloudImage {
            id = "PROJECT_EXT_21"
            profileId = "amazon-4"
            agentPoolId = "-2"
            name = "parkub"
            vpcSubnetId = "subnet-0c4f70b91d8800740"
            keyPairName = "chubatova-amazon-ireland"
            instanceType = "t3.medium"
            securityGroups = listOf("sg-072d8bfa0626ea2a6")
            source = Source("ami-0effbc367ff0a08aa")
        }
        kubernetesConnection {
            id = "PROJECT_EXT_7"
            name = "Kubernetes Connection"
            apiServerUrl = "localhost:8091"
            authStrategy = unauthorized()
        }
        kubernetesExecutor {
            id = "PROJECT_EXT_8"
            connectionId = "PROJECT_EXT_7"
            profileName = "kub1"
        }
        amazonEC2CloudProfile {
            id = "amazon-4"
            name = "parkub"
            terminateIdleMinutes = 30
            region = AmazonEC2CloudProfile.Regions.EU_WEST_DUBLIN
            awsConnectionId = "AmazonWebServicesAws"
        }
    }
}
