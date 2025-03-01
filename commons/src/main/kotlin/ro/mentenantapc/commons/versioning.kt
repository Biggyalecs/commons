package ro.mentenantapc.commons

import java.io.File

object versioning {

    fun getCustomVersionCode(): Int {
        val versionCodeFile = File("versionCode.properties")
        if (!versionCodeFile.exists()) {
            versionCodeFile.createNewFile()
            versionCodeFile.writeText("1")
        }
        val versionCode = versionCodeFile.readText().trim().toInt()
        versionCodeFile.writeText((versionCode + 1).toString())
        return versionCode
    }

    fun getCustomVersionName(): String {
        val versionNameFile = File("versionName.properties")
        if (!versionNameFile.exists()) {
            versionNameFile.createNewFile()
            versionNameFile.writeText("1.0.0.1")
        }

        val versionName = versionNameFile.readText().trim()
        val versionParts = versionName.split(".").toMutableList()

        var major = versionParts[0].toInt()
        var minor = versionParts[1].toInt()
        var patch = versionParts[2].toInt()
        var build = versionParts[3].toInt()

        build += 1
        if (build > 99) {
            build = 1
            patch += 1
            if (patch > 9) {
                patch = 1
                minor += 1
                if (minor > 9) {
                    minor = 1
                    major += 1
                }
            }
        }

        val newVersionName = "$major.$minor.$patch.$build"
        versionNameFile.writeBytes(newVersionName.toByteArray())
        return newVersionName
    }
}
