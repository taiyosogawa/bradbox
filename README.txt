==========
= README =
==========
Author: Taiyo Sogawa
Last Revised: 3/17/2013

=======================
= System Requirements =
=======================
Device: Windows XP 32-bit Operating System

1) Install Java SE 7 JRE
	See:	<http://www.oracle.com/technetwork/java/javase/downloads/index.html>

============================
= Application Installation =
============================

1) Double click on Install_Brad_Box.exe
	This should automatically:
		Put the platform-tools directory in the root directory (Do not delete C:\platform-tools after installation!)
		Put rxtxSerial.dll, sqlite3.def, sqlite3.dll, and sqlite3.exe in C:\WINDOWS\System32
		Put an "Brad Box.exe" on the desktop
	If anything fails, make sure these files are located in the appropriate directories. Manually copying the files might be necessary.

========================
= Arduino Installation =
========================
Device: Arduino Micro

1) Install Driver for Arduino Micro 
	Drivers are located in the setup/arduino-drivers directory 
2) Change device port to COM34
	Control Panel > System Properties > Device Manager
	Find Arduino Micro under Ports
	Right Click > Properties > Port Settings > Advanced
	Change port number to COM34


========================
= Android Installation =
========================
Device: Unlocked Samsung Galaxy Pocket S5300

1) Install phone drivers on computer (Directions vary by device.)
	Drivers for Galaxy Pocket are in the setup/android-drivers directory
2) Factory reset the phone if desired (Directions vary by device.)
	On the Galaxy Pocket, Dial *2767*3855#
3) Root the android phone (Directions vary by device.)
4) Enable USB Debug Mode (Directions vary by device.)
	On the Galaxy Pocket, Settings > Applications > Development > USB debugging 
5) Install Android application
	Connect phone to computer by USB
	You can check the connection by opening CMD, navigating to C:\platform-tools and typing command "adb devices"
	Double click install_new_android_application.exe in C:\platform-tools when phone connected
	You will see the Brad Box logo on the phone screen on a successful install

============================
= Additional Documentation =
============================

https://code.google.com/p/aster/source/browse/src/com/android/chimpchat/core/IChimpDevice.java?r=967f7f8cd6249c69e00c6de7ff1b55bd0f51d311