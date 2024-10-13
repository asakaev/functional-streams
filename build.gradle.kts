plugins {
    id("java")
}

group = "ru.yandex"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.projectreactor:reactor-core:3.6.10")
}
