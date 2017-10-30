package com.babuwyt.consignee.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.babuwyt.consignee.R;
import com.babuwyt.consignee.base.BaseActivity;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {
//    @ViewInject(R.id.toolbar)
//    Toolbar toolbar;
//    @ViewInject(R.id.springview)
//    SpringView springview;
    @ViewInject(R.id.fab)
    FloatingActionButton fab;
    @ViewInject(R.id.drawer_layout)
    DrawerLayout drawer;
//    @ViewInject(R.id.listview)
//    ListView listview;
    private ArrayList<String> mList;
    private TestAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();
        init();
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
    }
    @SuppressLint("ResourceAsColor")
    private void init(){
//        springview.setHeader(new DefaultHeader(this));
//        springview.setListener(new SpringView.OnFreshListener() {
//            @Override
//            public void onRefresh() {
//                mList.add("1");
//                mList.add("1");
//                mList.add("1");
//                adapter.notifyDataSetChanged();
//                springview.onFinishFreshAndLoad();
//            }
//
//            @Override
//            public void onLoadmore() {
//                springview.onFinishFreshAndLoad();
//            }
//        });

//        TextView emptyView = new TextView(this);
//        emptyView.setTextColor(R.color.black);
//        emptyView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//        emptyView.setText("还没有可以跟踪的订单！");
//        emptyView.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL);
//        emptyView.setVisibility(View.VISIBLE);
//        ((ViewGroup)listview.getParent()).addView(emptyView);
        mList=new ArrayList<String>();
        adapter=new TestAdapter(this);
//        listview.setAdapter(adapter);
    }
    @Event(value = {R.id.fab})
    private void getE(View v){
        switch (v.getId()){
            case R.id.fab:
                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                break;
        }
    }

    class TestAdapter extends BaseAdapter{
        Context context;
        public TestAdapter(Context context){
            this.context=context;
        }
        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            TextView textView=new TextView(MainActivity.this);
            textView.setText(i+"测试一下");
            return textView;
        }
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
