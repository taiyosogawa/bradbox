Install Java SE 7
Place "platform-tools" in the root directory (make sure it is x86 version)
platform-tools should include copycontacts.exe
Make sure phone in USB debug mode (applications -> development -> debub mode)
Install phone drivers
put 32 bit rxtxSerial.dll in Windows>System32
Factory reset if needed (dial *2767*3855#)
Install Arduino driver (find in device manager, update drivers, have it search in the drivers folder)



(Maybe) 
Install sqlite3 (.dll and .exe into Windows/System32)


PHONE SPECIFIC

root the phone

We will copy the contacts database every time, because there is a timeout for broadcast intent receivers


OPTION1 (pull database each time)
do one time {
adb shell
su (make sure the phone will accept)
chmod 777 /data/data/com.android.providers.contacts/databases/contacts2.db
}

do every time a contact is added/edited/deleted {
adb pull /data/data/com.android.providers.contacts/databases/contacts2.db
}

do every time we query a contact {
sqlite3 contacts2.db
select data1 from contact_entities_view where (display_name like 'taiyo%' or data1 like '425%') and mimetype = 'vnd.android.cursor.item/phone_v2';
}

OPTION2 (query within adb)
Instal sqlight on phone via this app https://play.google.com/store/apps/details?id=ptSoft.util.sqlite3forroot&hl=en

