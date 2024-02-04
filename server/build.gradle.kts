plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    application
    alias(libs.plugins.kotlinx.serialization)
}

group = "fr.togethim.mtg_life"
version = "0.0.1"
application {
    mainClass.set("fr.togethim.mtg_life.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

tasks.withType<Jar> {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

dependencies {
    testImplementation(libs.ktor.server.tests) // Tests de serveur Ktor
    testImplementation(libs.kotlin.test.junit) // Tests unitaires Kotlin avec JUnit
    testImplementation(libs.koin.test) // Koin pour les tests unitaires
    testImplementation(libs.mockk) // MockK pour les tests unitaires
    testImplementation(libs.junit.jupiter.api) // Tests unitaires avec JUnit
    testImplementation(libs.junit.jupiter.engine) // Tests unitaires avec JUnit
    implementation(projects.shared)
    implementation(libs.logback) // Journalisation avec Logback
    implementation(libs.ktor.server.core) // Serveur Ktor avec Netty ou Jetty
    implementation(libs.ktor.server.netty)
    implementation(libs.tomcat) // Serveur Ktor avec Tomcat
    implementation(libs.ktor.client.serialization)
    implementation(libs.kotlinx.serialization.json) // Sérialisation/désérialisation JSON avec Ktor
    implementation(libs.ktor.server.content.negotiation) // Content negotiation de Ktor
    implementation(libs.ktorm.core) // ORM pour manipuler des données et faire des mappings objet-relationnel
    implementation(libs.mysql.connector.j) // Mysql
    implementation(libs.mariadb.java.client) // MariaDB
    implementation(libs.jbcrypt) // Hache les mdp
    implementation(libs.ktor.server.auth) // Authentification JWT
    implementation(libs.ktor.server.auth.jwt) // Authentification JWT
    implementation(libs.rxkotlin) // Rx Kotlin
    implementation(libs.kotlin.logging.jvm) // Logging
    implementation(libs.retrofit) // Retrofit pour les appels HTTP
    implementation(libs.koin.core) // Koin pour les injections de dépendances
    implementation(libs.koin.ktor) // Koin pour Ktor
    implementation(libs.slf4j.api) // API de journalisation pour SLF4J pour afficher les logs pendant les tests
    implementation(libs.logback) // ajoute une implémentation de journalisation compatible avec SLF4J
    implementation(libs.google.api.client)
    implementation(libs.google.oauth.client.jetty)
    implementation(libs.google.api.services.gmail)
    implementation(libs.jakarta.mail.api)
    implementation(libs.jakarta.mail)
    implementation(libs.ktor.server.websockets) // WebSocket avec Ktor

}