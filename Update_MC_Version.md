# Personal file so that I know how to do this
- Update gradle.properties
  - Get from Fabric website: https://fabricmc.net/develop/
  - "minecraft_version", "yarn_mappings", "loader_version", "loom_version", and "fabric_version" from website
  - "mod_version"
- Update src/main/resources/fabric.mod.json
  - "depends", "minecraft"

- gradlew build

- Verify that it works with gradlew runClient

- Assuming it does, final .jar file to publish can be found in build/libs