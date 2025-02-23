# Video Player Application

This project is designed to provide a simple way to browse and watch top film video materials.

## Features

- **Video List Screen**:
    - Displays an auto-loading feed of films with thumbnails, titles, and details.
    - Fetches data from a remote API.
    - Supports "pull to refresh" functionality for updating the video list.
    - Implements caching of the video list using Room.
    - Includes error handling for network issues and data loading errors.

- **Film Details Screen**:
    - Allows users to play videos using ExoPlayer.
    - Displays film details, such as name, collapsed description, countries, etc.
    - Includes playback controls (play, pause, seek forward/backward).
    - Supports fullscreen mode with screen rotation.
    - Includes error handling for network issues and missing videos.

- **Architecture**:
    - Follows the MVVM architectural pattern.
    - Utilizes Android Clean Architecture layers.

## Libraries Used
- **Retrofit:** For network operations and API calls.
- **Dagger Hilt:** For dependency injection.
- **Room:** For local data caching.
- **ExoPlayer:** For video playback.
- **Jetpack Compose:** For UI implementation.