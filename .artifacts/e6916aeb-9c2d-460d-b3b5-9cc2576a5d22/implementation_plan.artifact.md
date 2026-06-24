# Implementation Plan: Migrate XRadioStream to Android 17 (API 37)

## Goal
Update the application to fully support and comply with Android 17 (API Level 37) requirements, including new security permissions and behavior changes.

## User Review Required
> [!IMPORTANT]
> **New Permissions**: I am adding two new permissions to the `AndroidManifest.xml`:
> 1. `USE_LOOPBACK_INTERFACE`: Required for connecting to servers on `localhost` or `127.0.0.1`.
> 2. `ACCESS_LOCAL_NETWORK`: Required for connecting to servers on your local network (LAN).
>
> **Runtime Permission**: `ACCESS_LOCAL_NETWORK` is a runtime permission. I will update `MainActivity` to request this permission to ensure connectivity to local stream servers works as expected on Android 17+.

## Proposed Changes

### 1. Manifest Updates
#### [MODIFY] [AndroidManifest.xml](file:///D:/GitHub/XRadioStream/app/src/main/AndroidManifest.xml)
- Add `<uses-permission android:name="android.permission.USE_LOOPBACK_INTERFACE" />`
- Add `<uses-permission android:name="android.permission.ACCESS_LOCAL_NETWORK" />`

### 2. Permission Handling
#### [MODIFY] [MainActivity.kt](file:///D:/GitHub/XRadioStream/app/src/main/java/com/xradiostream/app/MainActivity.kt)
- Update the permission request logic to include `ACCESS_LOCAL_NETWORK` when running on Android 17 or higher.

### 3. Code Maintenance
#### [MODIFY] [PlaybackService.kt](file:///D:/GitHub/XRadioStream/app/src/main/java/com/xradiostream/app/PlaybackService.kt)
- Ensure the `MediaSession` and Foreground Service are initialized correctly to comply with stricter background audio hardening in Android 17.

## Verification Plan

### Automated Tests
- Run `gradle build` to ensure project integrity.

### Manual Verification
1. **Permission Request**: Deploy to an Android 17+ device and verify the "Local Network" permission prompt appears.
2. **Local Connectivity**: Test connecting to a local IP (e.g., `192.168.1.100`) and verify the stream plays successfully.
3. **Loopback Connectivity**: Test connecting to `127.0.0.1` and verify the `USE_LOOPBACK_INTERFACE` permission allows the connection.
4. **Background Playback**: Verify that audio playback is not interrupted when the app moves to the background or the screen is locked.
