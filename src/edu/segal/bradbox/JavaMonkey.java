/*
 * BradBox
 * Copyright 2013 Taiyo Sogawa
 * taiyo <at> u <dot> northwestern <dot> edu
 * Last Revised: March 16, 2013
 * 
 * This file is part of BradBox.

    BradBox is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    BradBox is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with BradBox.  If not, see <http://www.gnu.org/licenses/>.
 */

package edu.segal.bradbox;

import java.util.TreeMap;
import com.android.chimpchat.ChimpChat;
import com.android.chimpchat.core.*;


public class JavaMonkey {
	// THIS WILL NEED TO BE CONFIGURED
    private static final String ADB = "/platform-tools/adb.exe";
    private static final long ADB_TIMEOUT = 5000;
    private ChimpChat mChimpchat;
    private IChimpDevice mDevice;


    /**
     * Constructor
     */
    public JavaMonkey() {
    	super();
	    TreeMap<String, String> options = new TreeMap<String, String>();
	    options.put("backend", "adb");
	    options.put("adbLocation", ADB);
	    mChimpchat = ChimpChat.getInstance(options);
	    mDevice = mChimpchat.waitForConnection(ADB_TIMEOUT, ".*");
	    if ( mDevice == null ) {
            throw new RuntimeException("Couldn't connect.");
	    } 
	    mDevice.wake();
    }


    /**
     * press a button specified by the keycode
     */
    public void press(String keycode) {
	    if ( mDevice == null ) {
            throw new IllegalStateException("JavaMonkey is not initialized in JavaMonkey.");
	    }
	    mDevice.press(keycode, TouchPressType.DOWN_AND_UP);  
    }
    
    public void shell(String cmd) {
    	if ( mDevice == null ) {
            throw new IllegalStateException("JavaMonkey is not initialized in JavaMonkey.");
	    }
    	mDevice.shell(cmd);
    }
    public void reboot() {
    	if ( mDevice == null ) {
            throw new IllegalStateException("JavaMonkey is not initialized in JavaMonkey.");
	    }
    	mDevice.reboot(null);
    }
    
    public void installBradbox() {
    	mDevice.installPackage("/platform-tools/android/Android-BradBox.apk");
    }
    
    public void unlock() {
    	if ( mDevice == null ) {
            throw new IllegalStateException("JavaMonkey is not initialized in JavaMonkey.");
	    }
    	mDevice.wake();
    	try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	mDevice.drag(120, 300, 120, 200, 1, 120);
    }
    
    public void lock() {
    	if ( mDevice == null ) {
            throw new IllegalStateException("JavaMonkey is not initialized in JavaMonkey.");
	    }
    	mDevice.press("POWER", TouchPressType.DOWN_AND_UP);
    }

    /**
     * Terminates this JavaMonkey.
     */
    public void shutdown() {
        mChimpchat.shutdown();
        mDevice = null;
    }
}
