#!/usr/bin/env bash
#在LINUX下如何将tar压缩文件解压到指定的目录下
# tar -xvf openstack_test.tar -C /tmp
#查看当前挂载的所有磁盘
#diskutil list

function mountAndroid(){
  hdiutil attach ~/android.sparseimage
}
function unMountAndroid(){
  hdiutil eject /Volumes/android
}
echo  ${1}
if [[ ${1} = "m" ]]; then
  echo '=== hdiutil mount ~/android.sparseimage ==='
  hdiutil mount ~/android.sparseimage

elif [[ ${1} = "u" ]]; then
  echo '=== hdiutil eject /Volumes/android=== '
  hdiutil eject /Volumes/android

elif [[ ${1} = "m2" ]]; then
  echo '=== hdiutil mount /Volumes/Seagate/android2.dmg.sparseimage '
#  hdiutil attach /Volumes/Seagate/android2.dmg.sparseimage -mountpoint /Volumes/android2
  hdiutil mount /Volumes/Seagate/android2.dmg.sparseimage

elif [[ ${1} = "u2" ]]; then
  echo '=== hdiutil eject /Volumes/android2 === '
  hdiutil eject /Volumes/android2

elif [[ ${1} = "us" ]]; then
  echo '=== hdiutil eject /Volumes/android2 === '
  hdiutil eject /Volumes/android2
  echo '===  hdiutil eject /Volumes/Seagate=== '
  hdiutil eject /Volumes/Seagate
#  diskutil unmount /dev/disk3s1

else
  echo '磁盘管理工具 '
  echo '挂载android输入：m'
  echo '卸载android输入：u'
  echo '挂载Seagate android2输入：m2'
  echo '卸载Seagate android2输入：u2'
  echo '卸载Seagate(会先卸载android2) 输入：us'
  exit
fi
