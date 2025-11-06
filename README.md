# OneStop CI: Auto-Generate Gradle Wrapper

This bundle adds:
- `gradle.properties` with AndroidX + Jetifier enabled
- GitHub Actions workflow that **first generates the Gradle Wrapper**
  (`gradle wrapper --gradle-version 8.7`) and then builds your APK.

### Why this approach?
We can't include the binary `gradle/wrapper/gradle-wrapper.jar` here, so the
workflow provisions Gradle and creates the wrapper on CI before building.
Locally, you can run the same once:

```bash
# using a system Gradle 8.7 (or via SDKMAN/asdf)
gradle wrapper --gradle-version 8.7
./gradlew :app:assembleDebug
```

Place these files at the **repo root** and push.
