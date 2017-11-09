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
import com.babuwyt.consignee.bean.location.LocusBean;
import com.babuwyt.consignee.bean.location.LocusEntity;
import com.babuwyt.consignee.finals.BaseURL;
import com.babuwyt.consignee.util.amap.MapUtil;
import com.babuwyt.consignee.util.request.CommonCallback.ResponseCallBack;
import com.babuwyt.consignee.util.request.XUtil;

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

    private String fsendcarid;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapView.onCreate(savedInstanceState);
        setStatusBar(true);
        init();
        initMap();
        getLocation();
    }

    private void initMap() {
        aMap = mapView.getMap();
        aMap.setMapType(AMap.MAP_TYPE_NORMAL);
        latLngs = new ArrayList<LatLng>();
    }


    private void init() {
        fsendcarid=getIntent().getStringExtra("fsendcarid");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    private void getLocation(){
        ArrayList<String> list=new ArrayList<String>();
        list.add(fsendcarid);
        mDialog.showDialog();
        XUtil.GetPing(BaseURL.PLGET_LOCATION_HISTORY,list,new ResponseCallBack<LocusBean>(){
            @Override
            public void onSuccess(LocusBean result) {
                super.onSuccess(result);
                mDialog.dissDialog();
                if (result.isSuccess() && result.getObj().size()>0){
                    ArrayList<LocusEntity>list=result.getObj();
                    drowLine(list);
                }
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                mDialog.dissDialog();
            }
        });
    }

    private void drowLine(ArrayList<LocusEntity>list){

        for (LocusEntity entity : list){
            latLngs.add(new LatLng(entity.getWgLat(),entity.getWgLon()));
        }
        MapUtil mapUtil = MapUtil.getInstance(LocusActivity.this, aMap);
        ArrayList<MarkerOptions> markerOptions = new ArrayList<MarkerOptions>();

        markerOptions.add(mapUtil.setMarker(latLngs.get(0), R.drawable.marker_start, false));
        markerOptions.add(mapUtil.setMarker(latLngs.get(latLngs.size() - 1), R.drawable.marker_end, false));
        mapUtil.addMarkers(markerOptions);
        mapUtil.setPolyline(latLngs);
        mapUtil.setMapwithBounds(latLngs, 5);

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
