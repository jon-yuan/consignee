package com.babuwyt.consignee.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.Polyline;
import com.babuwyt.consignee.R;
import com.babuwyt.consignee.base.BaseActivity;
import com.babuwyt.consignee.util.amap.MapUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/11/8.
 */
@ContentView(R.layout.activity_locus)
public class LocusActivity extends BaseActivity {
    @ViewInject(R.id.toolbar)
    Toolbar toolbar;
    @ViewInject(R.id.mapview)
    MapView mapView;
    private AMap aMap;
    private Polyline polyline;
    private ArrayList<LatLng> latLngs;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapView.onCreate(savedInstanceState);
        setStatusBar(true);
        init();
        initMap();
    }

    private void initMap() {
        aMap = mapView.getMap();
        aMap.setMapType(AMap.MAP_TYPE_NORMAL);
        latLngs = new ArrayList<LatLng>();
        getLocus();
    }


    private void init() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    /**
     * 108.911971,34.328907
     * 108.983261,34.304101
     * 108.848155,34.249691
     * 108.796413,34.259717
     */
    private void getLocus(){
        latLngs.add(new LatLng(34.328907,108.911971));
        latLngs.add(new LatLng(34.304101,108.983261));
        latLngs.add(new LatLng(34.249691,108.848155));
        latLngs.add(new LatLng(34.259717,108.796413));
        MapUtil mapUtil = MapUtil.getInstance(LocusActivity.this, aMap);
        ArrayList<MarkerOptions> markerOptions = new ArrayList<MarkerOptions>();

        markerOptions.add(mapUtil.setMarker(latLngs.get(0), R.drawable.marker_start, false));
        markerOptions.add(mapUtil.setMarker(latLngs.get(latLngs.size() - 1), R.drawable.marker_end, false));
        mapUtil.addMarkers(markerOptions);
        mapUtil.setPolyline(latLngs);
        mapUtil.setMapwithBounds(latLngs, 20);
        mapUtil.setMapZoomTo(10);
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
