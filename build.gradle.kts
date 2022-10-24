plugins {
    id("java-library")
    id("net.minecrell.plugin-yml.bukkit") version "0.5.2"
    id("xyz.jpenilla.run-paper") version "1.0.6"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "com.eripe14"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()

    maven { url = uri("https://repository.minecodes.pl/snapshots") }
    maven { url = uri("https://repo.panda-lang.org/releases") }
    maven { url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/") }
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    // spigot api
    compileOnly("org.spigotmc:spigot-api:1.19.2-R0.1-SNAPSHOT")

    // Kyori Adventure
    implementation("net.kyori:adventure-platform-bukkit:4.1.2")
    implementation("net.kyori:adventure-text-minimessage:4.11.0")

    implementation("dev.rollczi.litecommands:bukkit-adventure:2.5.0-SNAPSHOT")

    // Cdn configs
    implementation("net.dzikoysk:cdn:1.14.0")

    // LiteCommands
    implementation("org.panda-lang:panda-utilities:0.5.2-alpha")

    // TriumphGui
    implementation("dev.triumphteam:triumph-gui:3.1.3")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
}

bukkit {
    main = "com.eripe14.flamecode.FlameCodePlugin"
    apiVersion = "1.13"
    prefix = "FlameCode"
    name = "FlameCodePlugin"
    version = "${project.version}"
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

tasks {
    runServer {
        minecraftVersion("1.19.2")
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
    archiveFileName.set("FlameCodePlugin v${project.version} (MC 1.8.8-1.19x).jar")

    exclude(
        "org/intellij/lang/annotations/**",
        "org/jetbrains/annotations/**",
        "org/checkerframework/**",
        "META-INF/**",
        "javax/**"
    )

    val prefix = "com.eripe14.flamecode.libs"
    listOf(
        "panda",
        "org.panda_lang",
        "net.dzikoysk",
        "dev.rollczi",
        "net.kyori",
        "dev.triumphteam",
        "org.slf4j"
    ).forEach { pack ->
        relocate(pack, "$prefix.$pack")
    }
}