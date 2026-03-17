# Snake Game (Jetpack Compose)

Classic Snake game app for Android built with **Kotlin** and **Jetpack Compose**.

- Repository: `EndrewSilveira/snake_android_app`
- Application ID / Namespace: `com.endrew.snake`

---

## Tech Stack

- **Kotlin**
- **Jetpack Compose** (Material 3)
- **Coroutines + Flow**
- Android SDK:
  - `minSdk 24`
  - `targetSdk 36`
  - `compileSdk 36`

---

## Project Structure

Main files/folders:

- `app/src/main/AndroidManifest.xml`
- `app/src/main/java/com/endrew/snake/`
  - `MainActivity.kt` — app entry point, sets up Compose content
  - `Game.kt` — game engine/state holder (Flow + coroutines)
  - `model/`
    - `position/`
    - `state/`
  - `ui/`
    - `screen/` — Compose screens/components
    - `theme/` — Compose theme (`SnakeTheme`)

---

## Architecture Overview (Technical)

### UI Layer (Compose)
- UI is implemented using **Jetpack Compose**.
- The main screen is `SnakeGame(game)` (in `ui/screen`).
- The UI observes state exposed by the game engine (Flow) and renders accordingly.

### Game Engine / State Management
- Core logic lives in `Game.kt`.
- State is exposed as a `Flow<GameState>` backed by a `MutableStateFlow`.
- Updates are executed using coroutines (created with `lifecycleScope` from the Activity).
- A `Mutex` is used to synchronize updates related to movement direction to avoid race conditions.

### Platform Integration
- `MainActivity` initializes the **Vibrator** using `VibratorManager` and passes it into `Game`.
- The engine triggers vibration feedback through the Android vibration APIs.

---

## How to Run

### Requirements
- Android Studio (latest recommended)
- JDK 11
- Android SDK installed (matching the project’s compile/target SDK)

### Steps
1. Clone:
   ```bash
   git clone https://github.com/EndrewSilveira/snake_android_app.git
   ```
2. Open in Android Studio.
3. Sync Gradle.
4. Run on an emulator or device.

---

## Improvements / Roadmap (Technical)

The items below are suggested improvements focused on code quality, architecture, and platform behavior:

1. **Prevent food spawning on the snake**
   - When generating a new food position, ensure it’s not inside the current snake body.
   - Consider using a retry loop or generating from a set of free cells.

2. **Introduce a proper “Game Over” state**
   - Add explicit states (e.g., `Running`, `GameOver`, `Paused`) inside `GameState` (or a sealed class).
   - Let the UI react to `GameOver` and provide a “Restart” action instead of auto-reset.

3. **Difficulty system (speed scaling)**
   - Make tick delay configurable (e.g., `baseDelayMs`, `minDelayMs`).
   - Scale speed based on score or level.
   - Keep the timing logic centralized in the engine.

4. **Persist high score**
   - Store best score using **Jetpack DataStore** (Preferences or Proto).
   - Load/save high score from the engine or a dedicated repository class.
   - Expose it as part of UI state.

5. **Gesture controls (swipe)**
   - Add swipe detection in Compose (`pointerInput`) to change direction.
   - Keep movement validation in the engine (UI only emits intent/events).

6. **Unit tests for the engine**
   - Refactor `Game` so the update step can be tested deterministically:
     - Extract “next state” pure function (e.g., `reduce(state, input) -> state`)
     - Inject randomness (food placement) and timing (ticker) to make tests stable.
   - Add tests for:
     - state updates
     - collision handling
     - food placement constraints
     - score updates
