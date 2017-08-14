/**
 * 
 */
package com.manage.util;

/**
 * @description 通过经纬度计算距离
 * @author Unaware
 *
 */
import java.util.HashMap;
import java.util.Map;

public class MapDistance { 
        
    private static double EARTH_RADIUS = 6378137; 
    
    private static double rad(double d) { 
        return d * Math.PI / 180.0; 
    }
      
    /**
     * 根据两个位置的经纬度，来计算两地的距离（单位为M）
     * 参数为String类型
     * @param lat1 用户经度
     * @param lng1 用户纬度
     * @param lat2 停车站经度
     * @param lng2 停车站纬度
     * @return 两地的距离（单位为M）
     */
    public static double getDistance(double lat1, double lng1, double lat2, double lng2) {       
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double difference = radLat1 - radLat2;
        double mdifference = rad(lng1) - rad(lng2);
        double distance = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(difference / 2.0), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(mdifference / 2.0), 2)));
        distance = distance * EARTH_RADIUS;
        return distance;
    }
      
    /**
     * 获取当前用户一定距离以内的经纬度值
     * 单位米 return minLat
     * 最小经度 minLng
     * 最小纬度 maxLat
     * 最大经度 maxLng
     * 最大纬度 minLat
     */
    public static Map<String, Double> getAround(double latitude, double longitude, double raidusMile) {
        Map<String, Double> map = new HashMap<String, Double>();
  
        double degree = (24901 * 1609) / 360.0; // 获取每度
        double mpdLng = Double.parseDouble((degree * Math.cos(latitude * (Math.PI / 180))+"").replace("-", ""));
        double dpmLng = 1 / mpdLng;
        double radiusLng = dpmLng * raidusMile;
        //获取最小经度
        double minLat = longitude - radiusLng;
        // 获取最大经度
        double maxLat = longitude + radiusLng;
          
        double dpmLat = 1 / degree;
        double radiusLat = dpmLat * raidusMile;
        // 获取最小纬度
        double minLng = latitude - radiusLat;
        // 获取最大纬度
        double maxLng = latitude + radiusLat;
          
        map.put("minLat", minLat);
        map.put("maxLat", maxLat);
        map.put("minLng", minLng);
        map.put("maxLng", maxLng);
        return map;
    }
}
