package patches.projects

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.Project
import jetbrains.buildServer.configs.kotlin.projectFeatures.S3Storage
import jetbrains.buildServer.configs.kotlin.projectFeatures.s3Storage
import jetbrains.buildServer.configs.kotlin.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, change the root project
accordingly, and delete the patch script.
*/
changeProject(DslContext.projectId) {
    features {
        val feature1 = find<S3Storage> {
            s3Storage {
                id = "PROJECT_EXT_6"
                bucketName = "n.chubatova-test"
                awsEnvironment = default {
                    awsRegionName = "eu-central-1"
                }
                accessKeyID = "AKIA5JH2VERVHVMPJQJI"
                accessKey = "credentialsJSON:4f0dd7f7-9e59-443b-8487-fc73ea89b097"
            }
        }
        feature1.apply {
            bucketName = "chubatovatransferacceleration"
            forceVirtualHostAddressing = true
            param("aws.use.default.credential.provider.chain", "")
        }
    }
}
