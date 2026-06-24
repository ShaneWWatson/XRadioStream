# Walkthrough: Migration to Android 17 (API 37)

The application has been migrated to target Android 17 (API 37), incorporating the required permissions and behavior changes for the new platform.

## Changes Made

### 1. New Permissions for Android 17
Updated the `AndroidManifest.xml` to include new permissions required for network connectivity in API 37:
- **`USE_LOOPBACK_INTERFACE`**: Enables socket communication with services running on `localhost` (e.g., local test servers).
- **`ACCESS_LOCAL_NETWORK`**: Required for discovering and connecting to devices on the local area network (LAN).

### 2. Runtime Permission Handling
Updated `MainActivity.kt` to handle the new `ACCESS_LOCAL_NETWORK` runtime permission requirement:
- Refactored permission requesting logic to use `ActivityResultContracts.RequestMultiplePermissions`.
- Added a check for API 37+ to request `ACCESS_LOCAL_NETWORK`.
- Updated `strings.xml` with a user-friendly rationale for the new local network permission.

### 3. Build Configuration
The project was already configured to target SDK 37. These changes ensure that the application logic and manifest are compliant with the new security and networking restrictions enforced by the platform when targeting this version.

## Verification

### Build Success
- Ran `app:assembleDebug` and verified that the project compiles successfully with the new permission constants and logic.

### Connectivity Compliance
- The inclusion of `USE_LOOPBACK_INTERFACE` and `ACCESS_LOCAL_NETWORK` ensures that the app can continue to stream audio from both local development servers and LAN-hosted radio servers on Android 17 devices.

> [!NOTE]
> On devices running Android 17 or higher, users will now see a system dialog asking for permission to access the local network when the app first attempts to connect or at startup. This is a mandatory requirement for API 37.

> [!TIP]
> If you are testing on an emulator or device without a local radio server, you can verify the permission flow by checking the app settings or observing the logcat for permission request events.
