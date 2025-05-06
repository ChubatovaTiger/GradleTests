import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.amazonEC2CloudImage
import jetbrains.buildServer.configs.kotlin.amazonEC2CloudProfile
import jetbrains.buildServer.configs.kotlin.projectFeatures.awsConnection
import jetbrains.buildServer.configs.kotlin.projectFeatures.jira
import jetbrains.buildServer.configs.kotlin.projectFeatures.youtrack
import jetbrains.buildServer.configs.kotlin.vcs.GitVcsRoot

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

version = "2025.03"

project {

    vcsRoot(ImgSrcXOnerrorAlert1)

    buildType(K)

    features {
        jira {
            id = "PROJECT_EXT_6"
            displayName = """<img/src="x"/onerror=alert(1)>"""
            host = "http://localhost:8111"
            userName = ""
            password = ""
            projectKeys = """<img/src="x"/onerror=alert(1)>"""
            cloudClientID = ""
            cloudSecret = ""
        }
        youtrack {
            id = "PROJECT_EXT_7"
            displayName = """<img/src="x"/onerror=alert(1)>"""
            host = "http://localhost:8111"
            userName = ""
            password = ""
            projectExtIds = """<img/src="x"/onerror=alert(1)>"""
            accessToken = "credentialsJSON:18dcf192-7094-4185-90d4-f79f5c172de1"
        }
        amazonEC2CloudImage {
            id = "PROJECT_EXT_8"
            profileId = "amazon-3"
            //agentPoolId = "-2"
            name = "p2im1"
            vpcSubnetId = "subnet-0ace2a91ee63119ea"
            instanceType = "t3.medium"
            securityGroups = listOf("sg-072d8bfa0626ea2a6")
            source = Source("ami-0b55e6eb21b887f36")
        }
        amazonEC2CloudImage {
            id = "PROJECT_EXT_9"
            profileId = "amazon-4"
            agentPoolId = "12"
            name = "p2im2"
            vpcSubnetId = "subnet-0ace2a91ee63119ea"
            instanceType = "t3.medium"
            securityGroups = listOf("sg-072d8bfa0626ea2a6")
            source = Source("ami-0b55e6eb21b887f36")
        }
        amazonEC2CloudProfile {
            id = "amazon-3"
            name = "p2p1"
            terminateIdleMinutes = 0
            region = AmazonEC2CloudProfile.Regions.EU_WEST_DUBLIN
            awsConnectionId = "awsConnection_1"
        }
        amazonEC2CloudProfile {
            id = "amazon-4"
            name = "p2p2"
            terminateIdleMinutes = 0
            region = AmazonEC2CloudProfile.Regions.EU_WEST_DUBLIN
            awsConnectionId = "awsConnection_1"
        }
        awsConnection {
            id = "awsConnection_1"
            name = "Amazon Web Services (AWS)"
            credentialsType = static {
                accessKeyId = "AKIA5JH2VERVDN3XW5GF"
                secretAccessKey = "credentialsJSON:14db7bcd-3042-46bf-a603-107d72bd3432"
            }
            allowInSubProjects = true
            allowInBuilds = true
        }
    }
}

object K : BuildType({
    name = "k"

    vcs {
        root(ImgSrcXOnerrorAlert1)
    }
})

object ImgSrcXOnerrorAlert1 : GitVcsRoot({
    name = "<img src=x onerror=alert(1)>"
    url = "s"
    branch = "refs/heads/master"
})
