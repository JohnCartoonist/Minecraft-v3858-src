# Minecraft-v3858-src
Minecraft v3858 is a mod for Minecraft Alpha v1.0.0 that not only restores Minecraft Infdev 20100415's terrain generator, but it also aims to expand upon it while retaining its core "feel". I also restored the vertical background scroll Minecraft Indev 20100202-2330 had for the title screen, so there's that too.

Besides the title screen thing, what you're getting is a fusion of Minecraft Infdev 20100415's and Minecraft Alpha v1.0.0's world generation.


# Build instructions
1. Download Retro MCP-Java and the JDK 8 from Azul Zulu, but make sure that the version of JDK 8 you're installing comes with Java FX: https://github.com/MCPHackers/RetroMCP-Java.

2. Open Retro MCP-Java, and the version of Minecraft you're going to select is "Infdev 20100360-2", not Infdev 20100360-1.

3. There is going to be a popup saying: "Are you sure you want to run setup for selected version?". You're supposed to click on yes.

4. After the environment has finished setting up, click on the "Decompile" button at the upper left corner.

5. After the source code has finished decompiling, close Retro MCP-Java, head over to the newly created "minecraft" folder located wherever you placed your Retro MCP-Java jar file, double click on the "src" folder, double click on the "net" folder within it, double click on the "minecraft" folder within it, double click on the "src" folder within it, and that is where you will drag-&-drop the .java files found in this repository.

6. Open Retro MCP-Java one last time, click on the "Recompile" button, and after that is done, you will click on the "Build" button.


You've successfully built my mod! You should be able to see a newly created folder called "build" containing a zip archive called "minecraft". That's the mod, you just have to extract it with any program of your choice (I personally use 7-Zip). They contain a handful of class files you can drag-&-drop into a Minecraft jar. Now you may feel free to play it through the BetaCraft launcher (which can be found here: https://betacraft.uk/).


# BetaCraft setup
1. Make sure to install Minecraft Infdev 20100360 before anything. The version you will choose to download is listed as "inf-20100360-1835 (1.139)".

2. Right after that is done, close BetaCraft, and head over to wherever your instance directory is located. If you're using Windows, then the default directory is typically found in "Roaming", which is located in "AppData". You can quickly head over there by clicking on the search icon typically present on your search bar (although, you can alternatively click on your home button and click on the search bar found over there), typing "run" (which will bring up a program simply called "Run"), typing %appdata%, and pressing enter on your keyboard.

3. The instance folder should be called ".betacraft". Head over there, double click on the "versions" folder, and look for a jar file named "inf-20100415".

4. Use either WinRAR or 7-Zip to open the jar file.

5. Delete the "META-INF" folder found within the jar file.

6. Drag-&-drop the class files you just created, and close the windows for WinRAR or 7-Zip and File Explorer.

7. Open BetaCraft and boot up "inf-20100360-1835 (1.139)" (the version of Minecraft you just installed). I hope you enjoy my mod :)


# Gameplay screenshots
<img width="1920" height="1080" alt="Screenshot 2025-08-29 13-25-39" src="https://github.com/user-attachments/assets/d34800de-ac80-41d3-a96b-7fe86c161ff1" />
<img width="1920" height="1080" alt="Screenshot 2025-08-29 13-27-24" src="https://github.com/user-attachments/assets/5011bd94-61b4-442a-89f0-9cdf6bb17a37" />
<img width="1920" height="1080" alt="Screenshot 2025-08-29 13-27-48" src="https://github.com/user-attachments/assets/f6a32a88-75d2-49af-a373-911e9d62cd31" />
<img width="1920" height="1080" alt="Screenshot 2025-08-29 13-28-13" src="https://github.com/user-attachments/assets/60625137-f741-41b3-a394-67cf73c3da96" />
<img width="1920" height="1080" alt="Screenshot 2025-08-29 13-30-26" src="https://github.com/user-attachments/assets/0dca5f1c-8997-491e-a7f6-d3ddcd7f6a16" />
<img width="1920" height="1080" alt="Screenshot 2025-08-29 13-30-55" src="https://github.com/user-attachments/assets/9d11272e-5bb8-4004-8fbd-6c44c17bf7d5" />
<img width="1920" height="1080" alt="Screenshot 2025-08-29 13-32-23" src="https://github.com/user-attachments/assets/ff888adc-3a50-4f92-82c5-f89ff8bb99a1" />
<img width="1920" height="1080" alt="Screenshot 2025-08-29 13-33-38" src="https://github.com/user-attachments/assets/f924a222-eb77-4242-bd9e-29b04b9830e3" />
