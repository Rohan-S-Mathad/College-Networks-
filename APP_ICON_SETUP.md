# App Icon Setup Guide

Your `applogo.jpg` file is in the assets folder. Here's how to set it as your Android app launcher
icon:

## ⚡ Quick Method (30 seconds - Recommended)

### Using Android Studio:

1. **Open Android Studio**
2. In Project view, **right-click** on `app` folder
3. Select `New` → `Image Asset`
4. In the dialog that opens:
    - Asset Type: Select **"Launcher Icons (Adaptive and Legacy)"**
    - Name: Leave as `ic_launcher`
    - Path: Click the 📁 folder icon and browse to:
      ```
      app/src/main/assets/applogo.jpg
      ```
    - Trim: Yes (recommended)
    - Resize: 85% (adds nice padding)
    - Shape: None (for adaptive)
    - Background Layer: Keep default or customize
5. Click **"Next"**
6. Review the preview - you'll see all sizes that will be generated
7. Click **"Finish"**
8. **Done!** Icons generated automatically in all mipmap folders

### Refresh the App:

```
Build → Clean Project
Build → Rebuild Project
Run → Run 'app'
```

Or uninstall the old app and reinstall.

---

## 🐍 Alternative Method (Using Python Script)

If you prefer automation or don't want to use Android Studio's UI:

### Step 1: Install Python Requirements

```bash
pip install Pillow
```

### Step 2: Run the Script

```bash
python generate_app_icon.py
```

### Step 3: Rebuild

```
Build → Rebuild Project
```

The script will:

- ✅ Convert `applogo.jpg` to PNG format
- ✅ Generate 5 sizes (mdpi, hdpi, xhdpi, xxhdpi, xxxhdpi)
- ✅ Create both square and round versions
- ✅ Place them in correct mipmap folders

---

## 📱 Expected Result

After following either method, your app icon will appear on the Android homescreen showing your
`applogo.jpg` image.

**Generated files:**

```
app/src/main/res/
├── mipmap-mdpi/
│   ├── ic_launcher.png (48x48)
│   └── ic_launcher_round.png
├── mipmap-hdpi/
│   ├── ic_launcher.png (72x72)
│   └── ic_launcher_round.png
├── mipmap-xhdpi/
│   ├── ic_launcher.png (96x96)
│   └── ic_launcher_round.png
├── mipmap-xxhdpi/
│   ├── ic_launcher.png (144x144)
│   └── ic_launcher_round.png
└── mipmap-xxxhdpi/
    ├── ic_launcher.png (192x192)
    └── ic_launcher_round.png
```

---

## ⚠️ Important Notes

1. **After generating icons, you must:**
    - Rebuild the project
    - Uninstall the old app from your device
    - Install the new version

2. **Icon won't update immediately** if you just reinstall over the old app. Either:
    - Uninstall first, then install
    - Or clear launcher cache (varies by device)

3. **The splash screen** will also show this icon (since we use `ic_launcher` by default)

---

## 🎨 Current Status

✅ `applogo.jpg` found in `app/src/main/assets/`  
⏳ Need to generate icon files in mipmap folders  
📱 After generation, icon will appear on homescreen

---

## 💡 Quick Troubleshooting

**Icon not showing after install?**

- Uninstall the app completely
- Reinstall
- Restart your device (if needed)

**Want to change the icon later?**

- Just replace `applogo.jpg` in assets folder
- Run the script or Android Studio Image Asset tool again

**Icon looks stretched or cropped?**

- In Android Studio Image Asset tool, adjust the Resize slider
- Recommended: 80-85% with trim enabled
- This adds nice padding around your logo

---

## ✨ That's it!

Choose the method you prefer and your app icon will be set! 🚀
