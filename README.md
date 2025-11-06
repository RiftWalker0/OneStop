# OneStop AndroidX Fix Patch

This patch enables **AndroidX** and **Jetifier** for your build. Drop the included
`gradle.properties` at the root of your project (next to `settings.gradle`).
A GitHub Actions workflow (`.github/workflows/build.yml`) is also included and
uses `gradle/actions/setup-gradle@v3` (no deprecations).

## Quick apply

```bash
# from project root
cp gradle.properties gradle.properties.bak || true
cp OneStop-AndroidX-Fix/gradle.properties ./gradle.properties
mkdir -p .github/workflows
cp OneStop-AndroidX-Fix/.github/workflows/build.yml .github/workflows/build.yml
```

Then run:

```bash
./gradlew --no-daemon :app:assembleDebug
```

If you still see AndroidX errors, ensure there are **no** support libraries in
your `dependencies {}` (everything should be `androidx.*`).