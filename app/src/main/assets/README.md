# Assets Folder

This folder contains all editable data files and images for the app.

## Image Files

### App Icon (Launcher Icon) - For Android Homescreen

To replace the app icon visible on Android homescreen with your app logo:

**Method 1: Using Android Studio (Recommended)**

1. Right-click on `app` folder in Android Studio
2. Select `New` → `Image Asset`
3. Choose `Launcher Icons (Adaptive and Legacy)`
4. Set `Path:` to your logo image file
5. Adjust padding/scaling as needed
6. Click `Next` → `Finish`
7. This will automatically generate all required sizes in mipmap folders

**Method 2: Manual Replacement**

1. Prepare your logo in different sizes:
    - `mdpi`: 48x48 px
    - `hdpi`: 72x72 px
    - `xhdpi`: 96x96 px
    - `xxhdpi`: 144x144 px
    - `xxxhdpi`: 192x192 px
2. Replace files in these folders:
    - `app/src/main/res/mipmap-mdpi/ic_launcher.png`
    - `app/src/main/res/mipmap-hdpi/ic_launcher.png`
    - `app/src/main/res/mipmap-xhdpi/ic_launcher.png`
    - `app/src/main/res/mipmap-xxhdpi/ic_launcher.png`
    - `app/src/main/res/mipmap-xxxhdpi/ic_launcher.png`
3. Also replace `ic_launcher_round.png` in the same folders for circular icon

**Specifications:**

- Format: PNG (with transparency recommended)
- Shape: Square with transparent background
- Safe area: Keep important content in center 80%
- Adaptive icon: Use solid background or transparency

**Note:** After replacing, you may need to:

- Uninstall the app from your device
- Reinstall to see the new icon
- Or clear app cache

### App Logo (applogo.png / applogo.jpg)

The main application logo displayed on the splash screen.

**Supported filenames:**

- `applogo.png` or `applogo.jpg` (preferred)
- `app_logo.png` or `app_logo.jpg`
- `logo.png` or `logo.jpg`

**Specifications:**

- Format: PNG or JPG
- Recommended size: 512x512 or larger
- Aspect ratio: Square (1:1)
- Background: Transparent (for PNG) or solid

### Campus Network Logo (campusnetwork.png / campusnetwork.jpg)

The Campus Network branding logo displayed on the login selection screen with floating animation.

**Supported filenames:**

- `campusnetwork.png` or `campusnetwork.jpg` (preferred)
- `campus_network.png` or `campus_network.jpg`
- `campuslogo.png` or `campuslogo.jpg`

**Specifications:**

- Format: PNG or JPG
- Recommended size: 512x512 or larger
- Aspect ratio: Square or horizontal
- Background: Transparent (for PNG) recommended

**Animation:** Floats up and down with subtle rotation

### College Campus Image (collegeimage.png / collegeimage.jpg)

Background image of the college campus displayed on the guest home screen with blur effect.

**Supported filenames:**

- `collegeimage.png` or `collegeimage.jpg` (preferred)
- `campus_background.png` or `campus_background.jpg`

**Specifications:**

- Format: PNG or JPG
- Recommended size: 1920x1080 or higher
- Aspect ratio: 16:9 or similar
- Will be automatically blurred with 70% black overlay

## Data Files

### users.json
Contains test user data for authentication.

**Fields:**
- `phoneNumber`: 10-digit phone number (used for login)
- `name`: Full name of the user
- `email`: Email address
- `address`: Full address
- `branch`: Academic branch/department (e.g., "Computer Science Engineering")
- `college`: College name
- `classNumber`: Class section (e.g., "CSE-2024-A")
- `classTeacherName`: Class teacher's full name with title
- `teacherContact`: Teacher's 10-digit phone number
- `fatherName`: Father's full name
- `fatherContact`: Father's 10-digit phone number

**To add more users:** Simply add more objects to the JSON array following the same format.

### email_users.json

Contains student profiles for email authentication with individual OTPs.

**Structure:**

```json
[
  {
    "name": "Rohan Mathad",
    "email": "rohan.mathad@rvce.edu.in",
    "rollNumber": "1RV23AI001",
    "department": "AI & ML",
    "otp": "123456",
    "branch": "Artificial Intelligence & Machine Learning",
    "college": "RV College of Engineering",
    "classNumber": "AI-2023-A",
    "classTeacherName": "Dr. Priya Sharma",
    "teacherContact": "+91-98765-43210",
    "fatherName": "Suresh Mathad",
    "fatherContact": "+91-98765-11111",
    "address": "Bangalore, Karnataka"
  }
]
```

**Current Test Users:**

| Name         | Email                    | Roll Number | OTP    |
|--------------|--------------------------|-------------|--------|
| Rohan Mathad | rohan.mathad@rvce.edu.in | 1RV23AI001  | 123456 |
| Aditi Rao    | aditi.rao@rvce.edu.in    | 1RV23AI015  | 234567 |
| Arjun Kumar  | arjun.kumar@rvce.edu.in  | 1RV23AI032  | 345678 |

**How to add more students:**

1. Copy an existing user object
2. Change the email (must be unique and valid format)
3. Update name, roll number, and other fields
4. **Set a unique 6-digit OTP** for each user
5. Save the file

**Testing:**

- Each user has their own unique OTP
- Email verification checks if email exists in database
- OTP field only shows for registered emails
- Wrong OTP shows error message

### announcements.json
Contains announcements with categories and timestamps.

**Fields:**
- `title`: Announcement text
- `category`: One of "HACKATHON", "CLUB_EVENT", or "ACADEMIC"
- `hoursAgo`: Number of hours since the announcement was posted

**Categories:**
- `HACKATHON`: Purple bullet - Tech events, hackathons
- `CLUB_EVENT`: Green bullet - Club activities, competitions
- `ACADEMIC`: Red bullet - Important academic notices

### calendar_events.json
Contains calendar events with dates and categories.

**Fields:**
- `daysOffset`: Days from today (positive = future, negative = past)
- `title`: Event name
- `category`: One of "FEST", "HOLIDAY", "EXAM", or "ACADEMIC"

**Categories:**
- `FEST`: Green - College festivals, club events
- `HOLIDAY`: Red - Holidays
- `EXAM`: Blue - Exam periods
- `ACADEMIC`: Red - Academic deadlines

## Timetable Image

To add your timetable:
1. Name your timetable image as `tt.png` or `tt.jpg`
2. Place it in this `assets` folder
3. The app will automatically display it in the Timetable viewer

**Recommended specifications:**
- Format: PNG or JPG
- Filename: `tt.png` or `tt.jpg` (preferred) or `timetable.png` / `timetable.jpg` (also supported)
- Resolution: 1080x1920 or higher for best quality
- Orientation: Portrait or Landscape (both supported)

**Zoom Features:**
- Pinch to zoom in/out (0.5x to 5x)
- Drag to pan around the zoomed image
- Reset button to return to original view

## Summary of Asset Files

```
assets/
├── applogo.png/jpg            # Splash screen logo
├── campusnetwork.png/jpg      # Login screen branding logo (floating animation)
├── collegeimage.png/jpg       # Guest home background (blurred)
├── tt.png/jpg                 # Timetable image (zoom & pan)
├── users.json                 # User profiles
├── email_users.json           # Student profiles
├── announcements.json         # Announcements with timestamps
└── calendar_events.json       # Calendar events
```

## Notes

- All JSON files must be valid JSON format
- Phone numbers should be 10 digits without spaces or special characters
- The app will fall back to default data/placeholders if files are invalid or missing
- Changes to JSON files require app restart to take effect
- Images are loaded with multiple fallback filenames for flexibility
- Timetable image supports full zoom and pan functionality
- All images should be high quality for best visual experience
