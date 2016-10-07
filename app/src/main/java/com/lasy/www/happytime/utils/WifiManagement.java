package com.lasy.www.happytime.utils;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

/**
 * Created by Administrator on 2016/10/7.
 */
public class WifiManagement implements IWifiManagement{
    private static IWifiManagement wifiManagement;
    private WifiManagement(){}

    /**
     * 双重锁实现但实例模式
     * @return
     */
    public static IWifiManagement getInstance(){
        if (wifiManagement ==null){
            synchronized (WifiManagement.class){
                if (wifiManagement ==null){
                    wifiManagement = new WifiManagement();
                }
            }
        }
        return wifiManagement;
    }

    /**
     * 获取 wifi ip
     * @param context
     * @return
     */
    @Override
    public String getWifiIp(Context context) {
        WifiManager wifiManager = (WifiManager) context
                .getSystemService(Context.WIFI_SERVICE);

        if (wifiManager.isWifiEnabled() && wifiManager.getWifiState() == wifiManager.WIFI_STATE_ENABLED) {
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            if (wifiInfo != null) {
                int ipAddress = wifiInfo.getIpAddress();
                if (ipAddress == 0) return "";
                return ((ipAddress & 0xff) + "." + (ipAddress >> 8 & 0xff)
                        + "." + (ipAddress >> 16 & 0xff) + "." + (ipAddress >> 24 & 0xff));
            }
        }
        return "";
    }

}
