# Fix: Adaptive icon XML prolog/BOM issue

Error:
```
The processing instruction target matching "[xX][mM][lL]" is not allowed.
```
This happens when `res/mipmap-anydpi-v26/ic_launcher.xml` (or `ic_launcher_round.xml`) contains a BOM or an XML prolog not at byte 0.

## What this patch provides
- Clean adaptive icon XMLs (no `<?xml ...?>`, no BOM)
- A simple vector foreground (`@drawable/ic_launcher_foreground`) shaped like a radio button
- A black background color at `@color/ic_launcher_background`

## Where to place
- `app/src/main/res/mipmap-anydpi-v26/ic_launcher.xml`
- `app/src/main/res/mipmap-anydpi-v26/ic_launcher_round.xml`
- `app/src/main/res/drawable/ic_launcher_foreground.xml`
- `app/src/main/res/values/ic_launcher_background.xml`

## Rebuild
```bash
./gradlew --no-daemon :app:assembleDebug
```
