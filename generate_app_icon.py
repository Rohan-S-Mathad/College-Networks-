#!/usr/bin/env python3
"""
App Icon Generator
Converts applogo.jpg from assets folder to Android launcher icons in all required sizes.

Requirements: pip install Pillow

Usage: python generate_app_icon.py
"""

from PIL import Image
import os

# Define the required icon sizes for different densities
ICON_SIZES = {
    'mdpi': 48,
    'hdpi': 72,
    'xhdpi': 96,
    'xxhdpi': 144,
    'xxxhdpi': 192
}

# Source image path
SOURCE_IMAGE = 'app/src/main/assets/applogo.jpg'

# Base output directory
OUTPUT_BASE = 'app/src/main/res'

def generate_icons():
    """Generate launcher icons in all required sizes."""
    
    # Check if source image exists
    if not os.path.exists(SOURCE_IMAGE):
        print(f"❌ Error: Source image not found at {SOURCE_IMAGE}")
        return False
    
    print(f"📱 Generating app icons from {SOURCE_IMAGE}")
    print("=" * 60)
    
    try:
        # Open and convert source image
        img = Image.open(SOURCE_IMAGE)
        
        # Convert to RGBA (PNG with transparency)
        if img.mode != 'RGBA':
            # Create a white background
            background = Image.new('RGBA', img.size, (255, 255, 255, 255))
            if img.mode == 'RGB':
                background.paste(img, (0, 0))
            else:
                img = img.convert('RGBA')
                background.paste(img, (0, 0), img)
            img = background
        
        # Generate icons for each density
        for density, size in ICON_SIZES.items():
            # Create output directory if it doesn't exist
            output_dir = os.path.join(OUTPUT_BASE, f'mipmap-{density}')
            os.makedirs(output_dir, exist_ok=True)
            
            # Resize image
            resized = img.resize((size, size), Image.Resampling.LANCZOS)
            
            # Save as PNG for ic_launcher
            output_path = os.path.join(output_dir, 'ic_launcher.png')
            resized.save(output_path, 'PNG', quality=100)
            print(f"✅ Generated {density:7s} ({size:3d}x{size:3d}): {output_path}")
            
            # Create rounded version for ic_launcher_round
            # Create a circular mask
            mask = Image.new('L', (size, size), 0)
            from PIL import ImageDraw
            draw = ImageDraw.Draw(mask)
            draw.ellipse((0, 0, size, size), fill=255)
            
            # Apply mask to create round icon
            round_img = resized.copy()
            round_img.putalpha(mask)
            
            # Save round icon
            round_path = os.path.join(output_dir, 'ic_launcher_round.png')
            round_img.save(round_path, 'PNG', quality=100)
            print(f"🔵 Generated {density:7s} (round):  {round_path}")
        
        print("=" * 60)
        print("✨ Success! All app icons generated successfully!")
        print("\n📋 Next steps:")
        print("1. Rebuild the project in Android Studio")
        print("2. Uninstall the old app from your device")
        print("3. Install the new version to see the new icon")
        print("\nOr simply: Run → Clean Project → Rebuild Project → Run")
        
        return True
        
    except Exception as e:
        print(f"❌ Error generating icons: {str(e)}")
        return False

if __name__ == '__main__':
    print("\n🎨 Android App Icon Generator\n")
    
    # Check if Pillow is installed
    try:
        from PIL import Image, ImageDraw
    except ImportError:
        print("❌ Error: Pillow library not found!")
        print("\n📦 Please install it using:")
        print("   pip install Pillow")
        print("\nThen run this script again.")
        exit(1)
    
    generate_icons()
