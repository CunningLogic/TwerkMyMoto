Root exploit for Motorola Razr I (x86)

To build:

javac TwerkMyMoto.java

dx --dex --output=classes.dex TwerkMyMoto.class

zip TwerkMyMoto.jar classes.dex

To use:

adb push su /data/local/tmp/

adb push rootme.sh /data/local/tmp/

adb push TwerkMyMoto.jar /data/local/tmp/

adb shell chmod 755 /data/local/tmp/rootme.sh

adb shell /data/local/tmp/rootme.sh

adb reboot

adb shell /data/local/tmp/rootme.sh

wait for device to reboot on it's own, then install SuperSu from the Google Play Store
