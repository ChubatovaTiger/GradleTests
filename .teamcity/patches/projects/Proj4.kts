package patches.projects

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, remove the project with id = 'Proj4'
from your code, and delete the patch script.
*/
deleteProject(RelativeId("Proj4"))

