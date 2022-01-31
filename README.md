[![](https://img.shields.io/badge/Discord-7289DA?style=for-the-badge&logo=discord&logoColor=white)](https://discord.gg/zSWjKVvfNy)
[![](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/CraftTogether) <BR>
[![](https://jitpack.io/v/CraftTogether/Kelp.svg)](https://jitpack.io/#CraftTogether/Kelp)
![](https://img.shields.io/badge/Maintained%3F-yes-green.svg) <BR>
[![](https://img.shields.io/github/downloads/CraftTogether/kelp/total.svg)](https://github.com/CraftTogether/kelp/releases)
![](https://img.shields.io/github/issues/CraftTogether/kelp.svg)
![](https://img.shields.io/github/issues-pr/CraftTogether/kelp.svg)
# Kelp (Discord Dependency)
Kelp is a discord dependency used by craft together to allow all the plugins which we develop to only require the use of a single discord JDA instance, this not only saves bandwidth but also efficiency, only one websocket needs to be maintained instead of having each plugin having its own discord bot for its discord features.

# Download
You can download the Kelp plugin from the [releases](https://github.com/CraftTogether/Kelp/releases)

# Developers
### Gradle:
```gradle
repositories {
    mavenCentral()
    maven {
        'https://jitpack.io'
    }
}
```

```gradle
dependencies {
    compileOnly 'com.github.CraftTogether:kelp:1.2-SNAPSHOT-3'
    compileOnly ('net.dv8tion:JDA:5.0.0-alpha.4') {
        exclude module: 'opus-java'
    }
}
```

### Maven:
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

```xml
<dependencies>
    <dependency>
        <groupId>com.github.CraftTogether</groupId>
        <artifactId>weg</artifactId>
        <version>1.2-SNAPSHOT-3</version>
        <scope>provided</scope>
    </dependency>
</dependencies>
```