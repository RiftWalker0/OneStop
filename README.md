# One Stop — Minimal Full Repo (Java, Views, Material3)

This repository builds an app with:
- Single big toggle on Home to set `ADB_ENABLED` and `development_settings_enabled` (needs `WRITE_SECURE_SETTINGS`).
- Navigation drawer (Home, Setup, Themes, Updates, About).
- **Themes**: Pure White, Material You (M3 + Dynamic Colors), Pitch Black.
- QS Tile to toggle the same setting.
- Updates page linking to the latest GitHub release.
- About page shows version and build number.
- GitHub Actions workflow that generates the Gradle wrapper and builds the debug APK.

## Build locally
```
# Install Gradle 8.7 or use the wrapper after first generation:
gradle wrapper --gradle-version 8.7
./gradlew :app:assembleDebug
```

## Granting permission
```
adb shell pm grant com.onestop android.permission.WRITE_SECURE_SETTINGS
```
Or from a shell app (aShell) on-device:
```
pm grant com.onestop android.permission.WRITE_SECURE_SETTINGS
```
Then disable "permission monitoring" in Developer options to keep it from being revoked.
