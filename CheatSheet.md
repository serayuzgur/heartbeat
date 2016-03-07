# Cheat Sheet for time saving.

## Download Oracle JDK from console.
```bash
wget --no-check-certificate --no-cookies --header "Cookie: oraclelicense=accept-securebackup-cookie" http://download.oracle.com/otn-pub/java/jdk/8u73-b02/jdk-8u73-linux-arm32-vfp-hflt.tar.gz
```

## Extract tar.gz
```bash
tar -zxvf jdk-8u73-linux-arm32-vfp-hflt.gz 
```

## Connect to wifi
```bash
sudo nmcli device wifi connect <WIFI_NAME> password <PASSWORD> ifname wlan0
```

## Run with nohup
```bash
nohup java -jar <RUNNABLE_JAR_NAME>  &
```
## SSH error due to IP change
Host key verification failed.

```bash
ssh-keygen -R hostname
```