package patches.projects

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.AmazonEC2CloudImage
import jetbrains.buildServer.configs.kotlin.AmazonEC2CloudProfile
import jetbrains.buildServer.configs.kotlin.Project
import jetbrains.buildServer.configs.kotlin.amazonEC2CloudImage
import jetbrains.buildServer.configs.kotlin.amazonEC2CloudProfile
import jetbrains.buildServer.configs.kotlin.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, change the root project
accordingly, and delete the patch script.
*/
changeProject(DslContext.projectId) {
    features {
        val feature1 = find<AmazonEC2CloudImage> {
            amazonEC2CloudImage {
                id = "PROJECT_EXT_4"
                profileId = "amazon-1"
                agentPoolId = "-2"
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
        }
        feature1.apply {
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
            param("ebs-optimized", "")
        }
        val feature2 = find<AmazonEC2CloudProfile> {
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
        feature2.apply {
            name = "profile1"
            terminateIdleMinutes = 30
            region = AmazonEC2CloudProfile.Regions.EU_WEST_DUBLIN
        }
    }
}
