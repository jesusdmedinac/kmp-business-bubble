import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.tasks.bundling.Jar
import org.gradle.kotlin.dsl.`maven-publish`

plugins {
    `maven-publish`
    signing
}

publishing {
    // Configure all publications
    publications.withType<MavenPublication> {
        // Stub javadoc.jar artifact
        artifact(tasks.register("${name}JavadocJar", Jar::class) {
            groupId = "com.jesusdmedinac.kmp.business.bubble"
            version = "1.0.0"
            archiveClassifier.set("javadoc")
            archiveAppendix.set(this@withType.name)
        })

        // Provide artifacts information required by Maven Central
        pom {
            groupId = "com.jesusdmedinac.kmp.business.bubble"
            version = "1.0.0"
            name.set("Kotlin Multiplatform library for bubble business and data logic")
            description.set("Kotlin Multiplatform library for bubble business and data logic")
            url.set("https://github.com/jesusdmedinac/kmp-business-bubble")

            licenses {
                license {
                    name.set("MIT")
                    url.set("https://opensource.org/licenses/MIT")
                }
            }
            developers {
                developer {
                    id.set("JesusDMedinaC")
                    name.set("Jesús Daniel Medina Cruz")
                    organization.set("JesusDMedinaC")
                    organizationUrl.set("https://jesusdmedinac.com")
                }
            }
            scm {
                groupId = "com.jesusdmedinac.kmp.business.bubble"
                version = "1.0.0"
                url.set("https://github.com/jesusdmedinac/kmp-business-bubble")
            }
        }
    }
}

signing {
    if (project.hasProperty("signing.gnupg.keyName")) {
        useGpgCmd()
        sign(publishing.publications)
    }
}
