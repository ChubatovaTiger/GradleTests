package Maven.buildTypes

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.script

object Maven_Maven : BuildType({
    name = "maven"

    artifactRules = "folder/a => folder/a.zip"

    vcs {
        root(Maven.vcsRoots.Maven_GitGithubComChubatovaTigerMavenJunitGitRefsHeadsMain)
    }

    steps {
        script {
            id = "simpleRunner"
            scriptContent = """
                mkdir folder
                echo a > folder/a
            """.trimIndent()
        }
    }
})
