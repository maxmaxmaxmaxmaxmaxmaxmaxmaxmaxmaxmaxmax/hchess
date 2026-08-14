plugins {
    java
}

group = "com.earthmelon"
version = "1.0-SNAPSHOT"

val lwjglVersion = "3.4.2"
val lwjglNatives = "natives-windows"
val jomlVersion = "1.10.9"
val joglVersion = "2.6.0"

repositories {
    mavenCentral()
}

sourceSets {
    main {
        java {
            setSrcDirs(listOf("src/main/java"))
        }
    }
}

dependencies {
    // JUnit
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    // LWJGL
    implementation(platform("org.lwjgl:lwjgl-bom:$lwjglVersion"))

    implementation("org.lwjgl:lwjgl")
    implementation("org.lwjgl:lwjgl-assimp")
    implementation("org.lwjgl:lwjgl-openal")
    implementation("org.lwjgl:lwjgl-opengl")
    implementation("org.lwjgl:lwjgl-stb")

    implementation("org.lwjgl:lwjgl::$lwjglNatives")
    implementation("org.lwjgl:lwjgl-assimp::$lwjglNatives")
    implementation("org.lwjgl:lwjgl-openal::$lwjglNatives")
    implementation("org.lwjgl:lwjgl-opengl::$lwjglNatives")
    implementation("org.lwjgl:lwjgl-stb::$lwjglNatives")

    // Math library
    implementation("org.joml:joml:$jomlVersion")

    // GLFW
    implementation("org.lwjgl:lwjgl-glfw")
    implementation("org.lwjgl:lwjgl-glfw::$lwjglNatives")
}

tasks.test {
    useJUnitPlatform()
}

