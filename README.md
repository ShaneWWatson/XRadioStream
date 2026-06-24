# XRadioStream

XRadioStream is a lightweight Android application designed for high-quality audio streaming from custom radio servers. Built with modern Android technologies, it provides a seamless background listening experience.

## Features

- **High-Quality Streaming**: Uses Jetpack Media3 and ExoPlayer for robust audio playback.
- **Background Playback**: Continuous streaming even when the app is in the background or the screen is off, managed via a Foreground Service.
- **Media Controls**: Integrated with the Android System Media Controls and Lock Screen for easy access to Play/Pause functionality.
- **Custom Configuration**: Easily configure your radio server details (Host, Port, Mount Point, and
  Protocol).
- **Metadata Support**: Displays real-time Artist and Song information from the stream.
- **Security-First**: Uses EncryptedSharedPreferences for configuration storage and enforces host
  validation.
- **Modern UI**: Built entirely with Jetpack Compose and Material 3 for a sleek, responsive user interface.
- **Network Awareness**: Designed to handle network security and connectivity configurations effectively.

## Architecture & Technologies

- **Language**: 100% Kotlin
- **UI Framework**: Jetpack Compose (Material 3)
- **Media Engine**: [Jetpack Media3](https://developer.android.com/guide/topics/media/media3) (ExoPlayer & MediaSession)
- **Navigation**: Navigation3 for Compose
- **Service**: `MediaSessionService` for managing background audio sessions.
- **Theme**: Dynamic coloring and Material Design 3 guidelines.

## Getting Started

### Prerequisites

- Android Studio Ladybug (or newer)
- Android SDK 24+ (Min SDK)
- Target SDK 35+ (Optimized for Android 15)

### Setup

1. **Clone the repository**:
   ```bash
   git clone https://github.com/ShaneWWatson/XRadioStream.git
   ```
2. **Open in Android Studio**:
   Import the project and wait for Gradle sync to complete.
3. **Configure the Stream**:
   Upon first launch, the app will prompt you for your radio server's configuration:
    - **Host**: The IP or domain of your stream server (validated for security).
   - **Port**: The port number (e.g., 8000).
    - **Mount Point**: The specific path for your stream (e.g., /stream).
    - **Protocol**: Choose between HTTP or HTTPS (with security warnings for insecure connections).
4. **Play**: Once configured, tap the Play button on the main screen to start the live stream.

## Permissions

The app requires the following permissions to function correctly:
- `INTERNET`: To stream audio from remote servers.
- `FOREGROUND_SERVICE` & `FOREGROUND_SERVICE_MEDIA_PLAYBACK`: To keep the audio playing in the background.
- `POST_NOTIFICATIONS`: To show playback controls in the notification drawer (Android 13+).

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
