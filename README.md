# Fix: file_paths.xml parsing error

**Problem**
```
[Fatal Error] file_paths.xml:2:6: The processing instruction target matching "[xX][mM][lL]" is not allowed.
```
This happens when an XML prolog like `<?xml version="1.0"?>` appears with any character (BOM/whitespace)
before it, or when multiple `<?xml ...?>` lines exist.

**Solution**
Use this `res/xml/file_paths.xml` *without* any XML prolog and ensure UTF-8 (no BOM).

**Where to place**
`app/src/main/res/xml/file_paths.xml`

**Manifest reminder**
Make sure your `AndroidManifest.xml` has the `FileProvider` configured to use this file:
```xml
<provider
    android:name="androidx.core.content.FileProvider"
    android:authorities="${applicationId}.provider"
    android:exported="false"
    android:grantUriPermissions="true">
    <meta-data
        android:name="android.support.FILE_PROVIDER_PATHS"
        android:resource="@xml/file_paths" />
</provider>
```

**Rebuild**
```bash
./gradlew --no-daemon :app:assembleDebug
```