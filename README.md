# MyApplication2 - Guest Authentication App

A modern Android application built with Jetpack Compose featuring a complete authentication flow
with splash screen, phone verification, OTP validation, and a comprehensive guest home screen with *
*editable data files in assets folder** and **session persistence**.

## Features

### Design & Theme
- **Color Scheme:**
    - Black background (#000000)
    - Purple text and icons (#A020F0)
    - Green action buttons (#00C853)
  - Light grey for secondary text (#C0C0C0)
  - Red for important notices (#FF4444)
  - Blue for academic events (#2196F3)

- **Typography:**
    - Heading font: Brooklyn (with Serif fallback)
    - Content font: Receptive (with SansSerif fallback)

- **Animations:**
    - Button hover and click animations with spring physics
  - Smooth fade in/out transitions (300-500ms)
    - Scale animations for interactive elements
    - Success animation with green checkmark
  - No white flashes during navigation
      - Slide-in animations for modals and announcements
      - Purple glow/shadow effects on active icons

### App Flow

1. **Splash Screen (2 seconds)**
    - **Displays app icon (`ic_launcher`) as logo**
    - Can be overridden with custom logo in `assets/applogo.png`
    - Smooth fade in/out animation
    - Falls back to placeholder if not found
    - Automatically transitions to login selection
   - **If user is already logged in, skips directly to Guest Main Page**

2. **Login Selection Screen**
     - **Modern gradient background** (black to dark purple)
    - **Animated floating particles** creating a dynamic atmosphere
    - **Campus Network logo** displayed at top with floating animation
        - Loads from `assets/campusnetwork.png` or `campusnetwork.jpg`
        - Smooth up/down float animation
        - Subtle rotation effect
        - Falls back to text logo if image not found
    - **Animated welcome text** with slide-in effect
        - "Welcome to Campus Navigation"
        - "Your digital campus companion"
    - **Two interactive buttons:**
        - **Login button:** Semi-transparent purple with hover effects
        - **Guest button:** Solid purple with pulse animation and glow effect
    - **Button animations:**
        - Pulse effect on Guest button (continuously)
        - Press animation (scales to 95%)
        - Hover animation (scales to 105%)
        - Smooth spring physics
    - **"Choose your access method" text** above buttons
    - Professional modern UI with smooth transitions

3. **Guest Authentication Screen**
    - Phone number input (10 digits)
    - Validation for correct phone format
   - **Phone number verification checks database before showing OTP**
   - OTP input (6 digits) - only appears if phone number is registered
   - "Verify Phone" button validates phone number exists
   - "Verify OTP" and "Resend OTP" buttons appear after phone verification
    - Test OTP: `123456`
    - Green success animation on verification
    - Retrieves user profile from database

4. **Guest Home Screen**
    - **Background:**
        - **Blurred college campus image from `assets/collegeimage.png` or `collegeimage.jpg`**
        - Falls back to placeholder if not found
        - 70% black overlay for text legibility
        - Frosted-glass UI panels for modals

    - **Header (Top Left):**
        - Circular avatar with user's initial
        - Greeting: "Hi, [FirstName]!"
        - Tappable to open full profile details

    - **Welcome Section (Center):**
        - Large heading: "Welcome to [College Name]"
        - Subtitle: "Explore your campus effortlessly."
        - Guest banner: "You're browsing as Guest. Log in to access full features."

    - **Bottom Navigation Bar (4 Buttons):**
        1. **College Navigation (CN)** - **Compass icon** (greyed out/inactive)
            - Redesigned to look like a real compass:
            - Outer circle (1.5dp stroke)
            - Inner circle (1dp stroke)
            - North needle (purple pointing upward)
            - South needle (gray pointing downward)
            - Cardinal direction markers at N, E, S, W
            - Center point
        2. **Announcements** - Megaphone icon
          - Opens modal with color-coded announcements
          - **Shows "time ago" for each announcement** (e.g., "2 hours ago", "3 days ago")
          - Loaded from `assets/announcements.json`
          - Categories:
                - Purple bullet → Hackathon Alert
                - Green bullet → Club Event
                - Red bullet → Important Academic Notice
            - Cards slide up with smooth animations
      3. **Academic Calendar** - Calendar icon
          - Interactive calendar with event categories
          - **Shows day of week with each date** (e.g., "Mon, 15 Jan")
          - **Month navigation arrows** to browse previous/next months
          - Loaded from `assets/calendar_events.json`
          - Categories:
                - Green → College fest/club events
                - Red → Holidays
                - Blue → Exam weeks
            - Tap events to see details in popup
      4. **Timetable** - Clock+Grid icon
           - Opens image viewer with timetable
         - **Loads from `assets/tt.png` or `assets/tt.jpg`** (also checks `timetable.png`/
           `timetable.jpg`)
         - **Full-screen display** - Uses entire device screen
         - **Pinch-to-Zoom:** Zoom from 0.5x to 5x magnification
         - **Drag-to-Pan:** Move around the zoomed image with **accelerated panning** (2.5x speed
           multiplier)
         - **Compact floating header** with transparent background
         - **Reset Button:** Instantly return to original view
         - **Close button:** Easy exit with X button
         - **Black background** for optimal viewing
         - Automatic fallback to placeholder if no image found
         - Supports PNG and JPG formats
         - Image scaling for optimal viewing
         - Easy close with X button
         - Purple border frosted glass design
         - **Instructions shown on screen:** "Pinch to zoom • Drag to pan"
           - Falls back to placeholder if no image found

    - **Profile Page:**
        - Accessible by tapping header name/avatar
      - **Scrollable list** for easy viewing of all information
        - Displays complete user information:
            - Name, Phone, Email
            - Branch, College, Class Number
            - Class Teacher Name & Contact
            - Father's Name & Contact
            - Address
        - **Sign Out button at bottom** (red button with exit icon)
        - Slide-in animation from right
        - Purple-bordered frosted glass design
        - Fixed header with close button
        - Smooth scrolling experience
        - **Sign Out button** at bottom (red, full width)
            - Clears session and profile
            - Exits app immediately
            - Requires re-login on next launch

### Navigation & Back Button Handling

#### Back Button Behavior

1. **Splash Screen:** Not applicable (auto-navigates)
2. **Login Selection Screen:** Exits app
3. **Guest Auth Screen:** Returns to login selection
4. **Guest Main Page:** **Exits app** (session preserved)

### Session Persistence

- User remains logged in between app launches
- **Back button on Guest Main Page exits app without signing out**
- Reopening app returns user to Guest Main Page
- User must explicitly sign out to clear session

## Session Management

### Automatic Session Persistence

- **First Launch:** Shows splash → login selection → authentication → guest main page
- **Subsequent Launches:**
    - **If logged in:** Splash → **directly to guest main page** (skips login)
    - **If signed out:** Splash → login selection (normal flow)

### Sign Out Functionality

- **Sign Out button** in profile dialog (red button at bottom)
- Clears session state and profile data
- **Exits app immediately**
- Next launch returns to login screen

### Session State Storage

- Session stored in `session_state` file (internal storage)
- Profile stored in `current_profile` file (internal storage)
- Persists across app restarts
- Only cleared when user signs out

## Test Data

The app includes two test user profiles with complete information:

### User 1

| Field            | Value                          |
|------------------|--------------------------------|
| Phone Number     | 1234567890                     |
| Name             | John Doe                       |
| Email            | john.doe@example.com           |
| Branch           | Computer Science Engineering   |
| College          | University of Los Angeles      |
| Class Number     | CSE-2024-A                     |
| Class Teacher    | Dr. Sarah Johnson              |
| Teacher Contact  | 9988776655                     |
| Father's Name    | Robert Doe                     |
| Father's Contact | 9876543210                     |
| Address          | 123 Main Street, City, Country |

### User 2

| Field            | Value                         |
|------------------|-------------------------------|
| Phone Number     | 9876543210                    |
| Name             | Jane Smith                    |
| Email            | jane.smith@example.com        |
| Branch           | Electronics and Communication |
| College          | University of Los Angeles     |
| Class Number     | ECE-2024-B                    |
| Class Teacher    | Prof. Michael Chen            |
| Teacher Contact  | 9988112233                    |
| Father's Name    | William Smith                 |
| Father's Contact | 9123456789                    |
| Address          | 456 Oak Avenue, Town, Country |

**Test OTP:** `123456`

**Note:** You must use one of these registered phone numbers. The app will show an error if you
enter an unregistered number.

## Assets Folder Structure

All test data and images are now stored in **editable files** in the `app/src/main/assets/` folder:

```
app/src/main/assets/
├── README.md                  # Guide for editing assets
├── applogo.png/jpg            # App logo (splash screen)
├── campusnetwork.png/jpg      # Campus Network logo (login screen)
├── collegeimage.png/jpg       # College campus background
├── users.json                 # User profile data
├── announcements.json         # Announcements with timestamps
├── calendar_events.json       # Calendar events
├── tt.png/jpg                 # Your timetable image (optional)
└── timetable.png/jpg          # Timetable image (optional)
```

### Editing Test Data

#### `users.json` - Add/Edit Users

```json
[
  {
    "phoneNumber": "1234567890",
    "name": "John Doe",
    "email": "john.doe@example.com",
    "address": "123 Main Street, City, Country",
    "branch": "Computer Science Engineering",
    "college": "University of Los Angeles",
    "classNumber": "CSE-2024-A",
    "classTeacherName": "Dr. Sarah Johnson",
    "teacherContact": "9988776655",
    "fatherName": "Robert Doe",
    "fatherContact": "9876543210"
  }
]
```

#### `announcements.json` - Add/Edit Announcements

```json
[
  {
    "title": "Hackathon XYZ registrations open!",
    "category": "HACKATHON",
    "hoursAgo": 2
  }
]
```

**Categories:** `HACKATHON` (purple), `CLUB_EVENT` (green), `ACADEMIC` (red)

#### `calendar_events.json` - Add/Edit Events

```json
[
  {
    "daysOffset": 5,
    "title": "Tech Fest 2024",
    "category": "FEST"
  }
]
```

**Categories:** `FEST` (green), `HOLIDAY` (red), `EXAM` (blue), `ACADEMIC` (red)  
**daysOffset:** Positive = future days, Negative = past days

#### Adding Your Timetable

1. Place your timetable image in `app/src/main/assets/`
2. Name it `tt.png` or `tt.jpg` (preferred) or `timetable.png`/`timetable.jpg`
3. Recommended: 1080x1920 or higher resolution
4. App will automatically load and display it
5. **Supports pinch-to-zoom and drag-to-pan**

## Customization Guide

### Add Custom Images

#### Replace App Icon (Launcher Icon)

To use your app logo as the homescreen icon:

**Using Android Studio (Easiest):**

1. Right-click on `app` folder
2. Select `New` → `Image Asset`
3. Choose `Launcher Icons (Adaptive and Legacy)`
4. Browse to your logo file
5. Adjust padding if needed
6. Click `Next` → `Finish`
7. Uninstall old app and reinstall to see new icon

**Required Sizes:**

- mdpi: 48x48px
- hdpi: 72x72px
- xhdpi: 96x96px
- xxhdpi: 144x144px
- xxxhdpi: 192x192px

**Location:**

- Replace files in `app/src/main/res/mipmap-*/ic_launcher.png`
- Also update `ic_launcher_round.png` for circular icon support

#### Replace App Logo

1. Add your logo to `app/src/main/assets/`
2. Name it `applogo.png` or `applogo.jpg`
3. **That's it!** App will automatically load it

#### Replace Campus Network Logo

1. Add your branding logo to `app/src/main/assets/`
2. Name it `campusnetwork.png` or `campusnetwork.jpg`
3. **That's it!** App will display it with floating animation

#### Replace College Campus Image

1. Add your campus photo to `app/src/main/assets/`
2. Name it `collegeimage.png` or `collegeimage.jpg`
3. **That's it!** App will blur it and use as background

## Technical Stack

- **Language:** Kotlin
- **UI Framework:** Jetpack Compose
- **Navigation:** Navigation Compose
- **Data Storage:** Local file storage with Gson
- **Date/Time:** Java 8+ Time API (with desugaring for API 24+)
- **Min SDK:** 24 (Android 7.0)
- **Target SDK:** 36

## Project Structure

```
app/src/main/java/com/example/myapplication2/
├── data/
│   ├── UserProfile.kt          # User data model (expanded with academic fields)
│   └── UserRepository.kt       # User data management (loads from assets)
├── navigation/
│   ├── NavRoutes.kt           # Navigation routes
│   └── NavGraph.kt            # Navigation graph setup
├── screens/
│   ├── SplashScreen.kt        # Splash screen with animation
│   ├── LoginSelectionScreen.kt # Login/Guest selection
│   ├── GuestAuthScreen.kt     # Phone & OTP authentication
│   └── GuestMainPage.kt       # Guest home screen with full features
├── ui/theme/
│   ├── Color.kt               # App color definitions (expanded)
│   ├── Theme.kt               # Material theme configuration
│   └── Type.kt                # Typography definitions
└── MainActivity.kt            # Main activity entry point

app/src/main/assets/              # Editable Data
├── README.md                     # Assets folder guide
├── applogo.png/jpg               # App logo
├── campusnetwork.png/jpg         # Campus Network logo
├── collegeimage.png/jpg          # College campus background
├── users.json                    # User profiles
├── announcements.json            # Announcements
├── calendar_events.json          # Events
└── tt.png/jpg                    # Timetable image (optional)

app/src/main/res/drawable/
├── campus_background.xml      # Placeholder campus image
├── ic_navigation.xml          # Navigation/compass icon
├── ic_announcements.xml       # Announcements/megaphone icon
├── ic_calendar.xml            # Calendar icon
├── ic_timetable.xml           # Timetable icon
└── timetable_placeholder.xml  # Placeholder timetable image
```

## Setup & Running

### Prerequisites
- Android Studio Hedgehog (2023.1.1) or later
- JDK 11 or higher
- Android SDK 36

### Steps

1. **Open the project in Android Studio**
   ```
   File > Open > Select MyApplication2 folder
   ```

2. **Sync Gradle dependencies**
    - Android Studio will automatically prompt to sync
    - Or click: File > Sync Project with Gradle Files
   - Desugaring library will be automatically downloaded

3. **Add Custom Assets (Optional)**
   To replace placeholder campus background:
    1. Place campus photo in `app/src/main/res/drawable/`
    2. Name it `campus_background.jpg` or `.png`
    3. The image will be automatically blurred in the app

4. **Run the app**
    - Connect an Android device or start an emulator
    - Click Run button or press Shift+F10
    - Select your target device

### Building APK
```bash
./gradlew assembleDebug
```
The APK will be located at: `app/build/outputs/apk/debug/app-debug.apk`

## Key Dependencies

```kotlin
implementation("androidx.navigation:navigation-compose:2.7.5")
implementation("androidx.compose.material:material-icons-extended:1.5.4")
implementation("com.google.code.gson:gson:2.10.1")
coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.4")
```

## User Profile Storage

User profiles are saved to local storage as JSON:
- File name: `current_profile`
- Location: App's internal files directory
- Format: JSON serialization using Gson
- **New fields:** branch, college, classNumber, classTeacherName, teacherContact, fatherName,
  fatherContact

## Animation Details

### Button Animations
- **Press:** Scales to 95% with spring physics
- **Hover:** Scales to 105% with spring physics
- **Spring Settings:** Medium bouncy damping, low stiffness
- **Nav Buttons:** Grow slightly on tap with purple glow effect

### Screen Transitions

- **Fade In/Out:** 300-500ms duration
- **Success Animation:** Spring-based scale from 0 to 1
- **Announcements:** Slide up from bottom with staggered timing
- **Profile Dialog:** Slide in from right

### Splash Screen
- **Fade In:** 1000ms
- **Display:** 2000ms total
- **Fade Out:** 1000ms before transition
- **Logo:** Loaded from assets with fade animation

### Login Selection Screen

- **Gradient Background:** Black to dark purple fade
- **Particle Animation:** 30 floating purple particles
    - Random speeds and sizes
    - Sine wave horizontal movement
    - Fade in/out at edges
    - 10-second loop
- **Campus Network Logo:**
    - Floating up/down (20dp range, 2-second cycle)
    - Subtle rotation (-2° to +2°, 3-second cycle)
    - Size: 180dp
- **Welcome Text:**
    - Fade in + slide up animation
    - 400ms delay, 1000ms duration
- **Login Button:**
    - Semi-transparent purple background
    - Hover and press animations
    - Purple text
- **Guest Button:**
    - Solid purple gradient background
    - Continuous pulse animation (1.5-second cycle)
    - Glow effect with pulsing opacity
    - Black text (high contrast)
    - Enhanced spring physics

### Guest Home Screen Features

### Accessibility

- Minimum 48dp touch targets for all buttons
- High contrast purple on black/dark background
- Semantic color coding (green=positive, red=important, blue=academic)
- Clear visual hierarchy with frosted glass panels

### Announcements System

- **6 Sample Announcements** with categories (editable in `assets/announcements.json`)
    - Hackathon alerts (Purple)
    - Club events (Green)
    - Academic notices (Red)
- **"Time ago" display** for each announcement:
    - "Just now" for < 1 hour
    - "X hours ago" for < 24 hours
    - "X days ago" for < 1 week
    - "X weeks ago" for older
- Smooth slide-up animations
- Color-coded category bullets
- Easy-to-read card layout
- **Fully customizable via JSON file**

### Academic Calendar

- **Event Categories:**
    - Festivals/Club Events (Green)
    - Holidays (Red)
    - Exam Weeks (Blue)
    - Academic Notices (Red)
- **Month Navigation:** Arrow buttons to browse previous/next months
- **Day of Week Display:** Shows full day name (e.g., "Monday, 15 January")
- Interactive event cards
- Tap to view detailed event information
- Color legend for quick reference
- **Shows "No events in this month"** if month is empty
- **Sample events populated from `assets/calendar_events.json`**
- **Fully customizable via JSON file**

### Timetable Viewer ⭐ Enhanced

- **Full-screen display** - Uses entire device screen
- **Loads from `assets/tt.png` or `assets/tt.jpg`** (also supports `timetable.png`/`timetable.jpg`)
- **Pinch-to-Zoom:** Zoom from 0.5x to 5x magnification
- **Drag-to-Pan:** Move around the zoomed image with **accelerated panning** (2.5x speed multiplier)
- **Compact floating header** with transparent background
- **Reset Button:** Instantly return to original view
- **Close button:** Easy exit with X button
- **Black background** for optimal viewing
- Automatic fallback to placeholder if no image found
- Supports PNG and JPG formats
- Image scaling for optimal viewing
- Easy close with X button
- Purple border frosted glass design
- **Instructions shown on screen:** "Pinch to zoom • Drag to pan"
- **Simply add your timetable image to assets folder!**

### Profile System

- **Comprehensive Fields:**
    - Personal: Name, Phone, Email, Address
    - Academic: Branch, College, Class Number
    - Contacts: Class Teacher, Father's details
- **Scrollable layout** for easy navigation
- Beautiful card layout with dividers
- Purple accent colors throughout
- Instant access from header
- Fixed header with close button
- Smooth scrolling experience
- **Sign Out button** at bottom (red, full width)
    - Clears session and profile
    - Exits app immediately
    - Requires re-login on next launch

### Interactive Elements

- All nav buttons have press animations
- College Navigation (CN) button intentionally disabled/greyed
- Active buttons have purple glow effect
- Smooth state transitions
- Professional frosted-glass modals

## Future Enhancements

- Actual Login functionality
- Real SMS OTP integration
- Backend API integration
- College Navigation (CN) feature implementation
- Real-time announcements sync
- Editable profile fields
- Biometric authentication
- Push notifications for announcements
- Download timetable as PDF
- Add events to device calendar
- Multi-language support

## Notes

- The Login button is currently a placeholder
- **App icon (`ic_launcher`) used as splash screen logo by default**
- **Custom logo in `assets/applogo.png` will override app icon**
- **Campus Network logo loaded from `assets/campusnetwork.png` or `campusnetwork.jpg`** with
  fallback
- **Campus background loaded from `assets/collegeimage.png` or `collegeimage.jpg`** with fallback
- **User data loaded from `assets/users.json`** with fallback to hardcoded defaults
- **Announcements loaded from `assets/announcements.json`** with fallback
- **Calendar events loaded from `assets/calendar_events.json`** with fallback
- **Timetable loaded from `assets/tt.png` or `assets/tt.jpg`** (also checks `timetable.png`/
  `timetable.jpg`) with fallback
- **Timetable supports full pinch-to-zoom (0.5x-5x) and drag-to-pan with accelerated panning**
- **Profile dialog is fully scrollable**
- **Session persists across app launches**
- **Back button on Guest Main Page exits app (session preserved)**
- **Sign out clears session and exits app**
- **Login selection screen features dynamic particle animation**
- **Campus Network logo floats with smooth animation**
- **Compass icon redesigned to look like actual compass**
- Phone numbers must match test data to proceed past OTP
- Custom fonts (Brooklyn, Receptive) require manual installation
- College Navigation button is intentionally inactive
- Desugaring enables java.time API on API 24+
- **All data files are JSON format and easily editable**
- **All images load from assets folder with multiple fallback filenames**
- **Changes to JSON files require app restart**
- **Timetable panning is 2.5x faster for better navigation when zoomed**

## License

This project is for educational/demonstration purposes.

## Contact

For questions or issues, please open an issue in the repository.
