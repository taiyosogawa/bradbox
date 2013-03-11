Install Java SE 7
Place "platform-tools" in the root directory (make sure it is x86 version)
platform-tools should include copycontacts.exe
Make sure phone in USB debug mode (applications -> development -> debub mode)
Install phone drivers
put 32 bit rxtxSerial.dll in Windows>System32
Factory reset if needed (dial *2767*3855#)
Install Arduino driver (find in device manager, update drivers, have it search in the drivers folder)



Install sqlite3 (.dll and .exe into Windows/System32)
sqlite3 favorites.db "create table names (name TEXT, rank INTEGER);"
populate names with Favorite 1, Favorite 2, ... , Favorite 6.

root the phone
Make sure the function unlock() in JavaMonkey.java will unlock the phone

We will copy the contacts database every time, because there is a timeout for broadcast intent receivers



every time a contact is edited {
adb shell
su (make sure the phone will accept)
chmod 777 /data/data/com.android.providers.contacts/databases/contacts2.db
adb pull /data/data/com.android.providers.contacts/databases/contacts2.db
}

do every time we query a contact {
sqlite3 contacts2.db
select data1 from contact_entities_view where (display_name like 'taiyo%' or data1 like '425%') and mimetype = 'vnd.android.cursor.item/phone_v2';
select data1 from contact_entities_view where (display_name glob '*[TtUuVv][AaBbCc][GgHhIi]*') or data1 glob '*425*' and mimetype = 'vnd.android.cursor.item/phone_v2');
}


SMS
db stored at data/data/com.android.providers.telephony/databases/mmssms.db

ChimpChat Documentation at
https://code.google.com/p/aster/source/browse/src/com/android/chimpchat/core/IChimpDevice.java?r=967f7f8cd6249c69e00c6de7ff1b55bd0f51d311