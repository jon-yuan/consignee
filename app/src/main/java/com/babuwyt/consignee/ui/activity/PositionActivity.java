package com.babuwyt.consignee.ui.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.babuwyt.consignee.R;
import com.babuwyt.consignee.base.BaseActivity;
import com.babuwyt.consignee.bean.location.LatLngBean;
import com.babuwyt.consignee.bean.location.LatLngEntity;
import com.babuwyt.consignee.bean.location.LocusEntity;
import com.babuwyt.consignee.bean.location.Result;
import com.babuwyt.consignee.finals.BaseURL;
import com.babuwyt.consignee.util.UHelper;
import com.babuwyt.consignee.util.amap.InfoWinAdapter;
import com.babuwyt.consignee.util.amap.MapUtil;
import com.babuwyt.consignee.util.request.CommonCallback.ResponseCallBack;
import com.babuwyt.consignee.util.request.XUtil;
import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 2017/11/8.
 */
@ContentView(R.layout.activity_position)
public class PositionActivity extends BaseActivity {
    @ViewInject(R.id.toolbar)
    Toolbar toolbar;
    @ViewInject(R.id.mapview)
    MapView mapView;

    private AMap aMap;
    private MapUtil mapUtil;
    private InfoWinAdapter mAdapter;
    private String fsendcarid;
    private String drivername;
    private String address;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapView.onCreate(savedInstanceState);
        setStatusBar(true);
        init();
        initMap();
        getLocation();
    }

    private void init() {
        fsendcarid = getIntent().getStringExtra("fsendcarid");
        drivername = getIntent().getStringExtra("drivername");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    private void initMap() {
        aMap = mapView.getMap();
        mAdapter = new InfoWinAdapter(this);
        mapUtil = MapUtil.getInstance(this, aMap);
        mapUtil.setMapZoomTo(20);
        aMap.setInfoWindowAdapter(mAdapter);
    }


    private Marker addMarkerToMap(LatLng latLng, String title, String snippet) {
        return aMap.addMarker(new MarkerOptions().anchor(0.5f, 0.5f)
                .position(latLng)
                .title(title)
                .snippet(snippet)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_car_green))
        );
    }

    private void getLocation() {
        ArrayList<String> list = new ArrayList<String>();
        list.add(fsendcarid);
        mDialog.showDialog();
        XUtil.GetPing(BaseURL.PLGET_LOCATION, list, new ResponseCallBack<LatLngBean>() {
            @Override
            public void onSuccess(LatLngBean result) {
                super.onSuccess(result);
                mDialog.dissDialog();
                if (result.isSuccess() && result.getObj() != null && result.getObj().getGps() != null) {
                    Result res=result.getObj();
                    res.setName(drivername);
                    address=res.getAddress();
                    Marker marker = addMarkerToMap(new LatLng(res.getGps().getWgLat(), res.getGps().getWgLon()), "", "");
                    marker.setObject(res);
                    mapUtil.moveMapCenter(new LatLng(res.getGps().getWgLat(), res.getGps().getWgLon()));
                    marker.showInfoWindow();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                mDialog.dissDialog();
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mapView.onSaveInstanceState(outState);
    }


}
