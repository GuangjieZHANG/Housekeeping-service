package com.example.user;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.utils.DistanceUtil;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by 张广洁 on 2017/3/18.
 */
public class ModifyMineActivity extends Activity {

    EditText mEditText=null;
    Button mButton1=null;
    Button mButton2=null;
    String name=null;

    public MapView mapView = null;
    public BaiduMap baiduMap = null;

    private LinkedList<LocationEntity> locationList = new LinkedList<LocationEntity>();

    private double longtitude;
    private double latitude;
    private TextView infoText;

    private boolean toastLoad = false;

    // 定位相关声明
    public LocationClient locationClient = null;
    //  boolean isFirstLoc = true;// 是否首次定位

    public void onCreate(Bundle savedInstanceBundle) {
        super.onCreate(savedInstanceBundle);
        //使用之前一定要初始化！！！血与泪的教训！！！
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.modifymine);
        mapView=(MapView)findViewById(R.id.maphome);
        mEditText=(EditText)findViewById(R.id.newname);
        mButton1=(Button)findViewById(R.id.modify);
        mButton2=(Button)findViewById(R.id.unmodify);
        name=mEditText.getText().toString();


        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itent = new Intent(ModifyMineActivity.this,MineActivity.class);
                ModifyMineActivity.this.startActivity(itent);
               //不保存修改时要返回并结束
                ModifyMineActivity.this.finish();
            }
        });

        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //这里还要提交信息

                Intent itent1 = new Intent(ModifyMineActivity.this,MineActivity.class);
                ModifyMineActivity.this.startActivity(itent1);

            }
        });

        baiduMap=mapView.getMap();
        baiduMap.setMyLocationEnabled(true);

        locationClient = new LocationClient(getApplicationContext());
        locationClient.registerLocationListener(new MyLocationListener());
        initLocation();

        baiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Log.d("map click", latLng.longitude + "，" + latLng.latitude);
                setMapOverlay(latLng);
                getInfoFromLAL(latLng);
            }

            @Override
            public boolean onMapPoiClick(MapPoi mapPoi) {
                return false;
            }
        });

        infoText=(TextView)findViewById(R.id.homeaddress);

    }

    @Override
    protected void onDestroy() {
        //退出时销毁定位
        locationClient.stop();
        baiduMap.setMyLocationEnabled(false);
        // TODO Auto-generated method stub
        super.onDestroy();
        mapView.onDestroy();
        mapView = null;
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        mapView.onPause();
    }

    // 初始化定位参数
    private void initLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span=1000;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认false，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        locationClient.setLocOption(option);
        locationClient.start();
    }


    private void toast(final String str) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(ModifyMineActivity.this, str, Toast.LENGTH_SHORT).show();
            }
        });
    }

    // 定位监听


    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onConnectHotSpotMessage(String s, int i) {

        }

        @Override
        public void onReceiveLocation(BDLocation loc) {
            if (loc != null && (loc.getLocType() == 161 || loc.getLocType() == 66)) {
                Bundle locData = algorithm(loc);
                Message locMsg = locHander.obtainMessage();
                if (locData != null) {
                    locData.putParcelable("loc", loc);
                    locMsg.setData(locData);
                    locHander.sendMessage(locMsg);

                    if (!toastLoad) {
                        Toast.makeText(ModifyMineActivity.this, "正在加载地图...", Toast.LENGTH_SHORT).show();
                    }
                    toastLoad = true;
                    locationClient.stop();
                }
            } else {
                Toast.makeText(ModifyMineActivity.this, "定位失败，请检查手机网络或设置！", Toast.LENGTH_LONG).show();
            }
        }

    }

    /***
     * 接收定位结果消息，并显示在地图上
     */
    private Handler locHander = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            try {
                BDLocation location = msg.getData().getParcelable("loc");

                latitude = location.getLatitude();
                longtitude = location.getLongitude();

                Log.d("time",location.getTime());
                Log.d("error code", location.getLocType()+"");
                Log.d("latitude", location.getLatitude()+"");
                Log.d("longitude", location.getLongitude()+"");
                Log.d("radius", location.getRadius()+"");
                if (location.getLocType() == BDLocation.TypeGpsLocation) {
                    Log.d("GPS", "gps定位成功");
                    Log.d("speed", location.getSpeed()+" 单位：公里每小时");
                    Log.d("satellite", location.getSatelliteNumber()+"");
                    Log.d("height", location.getAltitude()+" 单位：米");
                    Log.d("direction", location.getDirection()+" 单位度");
                    Log.d("addr", location.getAddrStr());
                } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
                    Log.d("网络定位结", "网络定位成功");
                    Log.d("addr", location.getAddrStr());
                    Log.d("operationers", "运营商信息："+location.getOperators());
                } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {
                    Log.d("离线定位", "离线定位成功，离线定位结果也是有效的");
                } else if (location.getLocType() == BDLocation.TypeServerError) {
                    Toast.makeText(ModifyMineActivity.this, "服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因", Toast.LENGTH_LONG).show();
                } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                    Toast.makeText(ModifyMineActivity.this, "网络不同导致定位失败，请检查网络是否通畅", Toast.LENGTH_LONG).show();
                } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                    Toast.makeText(ModifyMineActivity.this, "无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机", Toast.LENGTH_LONG).show();
                }
                Log.d("locationdescribe", location.getLocationDescribe());

                List<Poi> list = location.getPoiList();// POI数据
                StringBuffer sb = new StringBuffer("POI数据：");
                if (list != null && list.size() > 0) {
                    sb.append("\npoilist size = : ");
                    sb.append(list.size());
                    for (Poi p : list) {
                        sb.append("\npoi= : ");
                        sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
                    }
                }

                Log.d("POI数据", sb.toString());

                infoText.setText("经度："+location.getLongitude()+"，纬度"+location.getLatitude()
                        +"\n"+location.getAddrStr()
                        +"\n"+location.getLocationDescribe());

                if (location != null) {
                    LatLng point = new LatLng(location.getLatitude(), location.getLongitude());
                    setMapOverlay(point);
                    baiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(point));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    };

    /***
     * 平滑策略代码实现方法，主要通过对新定位和历史定位结果进行速度评分，
     * 来判断新定位结果的抖动幅度，如果超过经验值，则判定为过大抖动，进行平滑处理,若速度过快，
     * 则推测有可能是由于运动速度本身造成的，则不进行低速平滑处理
     *
     * @param
     * @return Bundle
     */
    private Bundle algorithm(BDLocation location) {
        float[] EARTH_WEIGHT = {0.1f,0.2f,0.4f,0.6f,0.8f}; // 推算计算权重_地球
        Bundle locData = new Bundle();
        double curSpeed = 0;
        if (locationList.isEmpty() || locationList.size() < 2) {
            LocationEntity temp = new LocationEntity();
            temp.location = location;
            temp.time = System.currentTimeMillis();
            locData.putInt("iscalculate", 0);
            locationList.add(temp);
        } else {
            if (locationList.size() > 5)
                locationList.removeFirst();
            double score = 0;
            for (int i = 0; i < locationList.size(); ++i) {
                LatLng lastPoint = new LatLng(locationList.get(i).location.getLatitude(),
                        locationList.get(i).location.getLongitude());
                LatLng curPoint = new LatLng(location.getLatitude(), location.getLongitude());
                double distance = DistanceUtil.getDistance(lastPoint, curPoint);
                curSpeed = distance / (System.currentTimeMillis() - locationList.get(i).time) / 1000;
                score += curSpeed * EARTH_WEIGHT[i];
            }
            if (score > 0.00000999 && score < 0.00005) {
                location.setLongitude(
                        (locationList.get(locationList.size() - 1).location.getLongitude() + location.getLongitude())
                                / 2);
                location.setLatitude(
                        (locationList.get(locationList.size() - 1).location.getLatitude() + location.getLatitude())
                                / 2);
                locData.putInt("iscalculate", 1);
            } else {
                locData.putInt("iscalculate", 0);
            }
            LocationEntity newLocation = new LocationEntity();
            newLocation.location = location;
            newLocation.time = System.currentTimeMillis();
            locationList.add(newLocation);

        }
        return locData;
    }

    class LocationEntity {
        BDLocation location;
        long time;
    }

    // 在地图上添加标注
    private void setMapOverlay(LatLng point) {
        latitude = point.latitude;
        longtitude = point.longitude;

        baiduMap.clear();
        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.icon_openmap_mark);
        OverlayOptions option = new MarkerOptions().position(point).icon(bitmap);
        baiduMap.addOverlay(option);
    }

    // 根据经纬度查询位置
    private void getInfoFromLAL(final LatLng point) {
        infoText.setText("经度："+point.latitudeE6+"，纬度"+point.latitudeE6);
        GeoCoder gc = GeoCoder.newInstance();
        gc.reverseGeoCode(new ReverseGeoCodeOption().location(point));
        gc.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {

            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
                //pb.setVisibility(View.GONE);
                if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                    Log.e("发起反地理编码请求", "未能找到结果");
                } else {
                    infoText.setText("经度："+point.latitudeE6+"，纬度"+point.latitudeE6
                            +"\n"+result.getAddress());
                }
            }

            @Override
            public void onGetGeoCodeResult(GeoCodeResult result) {

            }
        });
    }


}
