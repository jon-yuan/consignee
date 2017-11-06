package com.babuwyt.consignee.views.dialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.babuwyt.consignee.R;
import com.babuwyt.consignee.views.CustomGridView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;


public class ShaixuanDialog extends Dialog{

    private static Context mContext;
    private Context zContext;
    private View view;
    private GridView gridview;
    private ImageView img_canal;
    private TextView tv_confirm;
    private static ShaixuanDialog dialog;
    private Adapter adapter;
    public ShaixuanDialog(Context context) {
        super(context,R.style.dialog_custom);
        zContext=context;
    }

    /**
     * 要监听的控件id
     */

    public static ShaixuanDialog Instance(Context context){
        if (dialog==null){
            dialog=new ShaixuanDialog(context);
            mContext=context;
        }
        return dialog;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.setGravity(Gravity.BOTTOM); // 此处可以设置dialog显示的位置
        view= LayoutInflater.from(zContext).inflate(R.layout.dialog_shaixuan,null);
        window.setWindowAnimations(R.style.bottom_menu_animation); // 添加动画
        setContentView(view);
        gridview=view.findViewById(R.id.gridview);
        img_canal=view.findViewById(R.id.img_canal);
        tv_confirm=view.findViewById(R.id.tv_confirm);
        adapter=new Adapter();
        gridview.setAdapter(adapter);
        // 宽度全屏
        WindowManager windowManager = ((Activity) zContext).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = display.getWidth(); // 设置宽度
        lp.height = display.getHeight()*2/3; // 设置宽度
        getWindow().setAttributes(lp);
        // 点击Dialog外部消失
        setCanceledOnTouchOutside(true);
        img_canal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.CallBack("数据");
            }
        });
    }

    public interface CallBack{
        void CallBack(String s);
    }
    private CallBack callBack;
    public void setCallBack(CallBack callBack){
        this.callBack=callBack;
    }

    class Adapter extends BaseAdapter{
        @Override
        public int getCount() {
            return 15;
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
            ViewHolder holder=null;
            if (view==null){
                holder=new ViewHolder();
                view=LayoutInflater.from(zContext).inflate(R.layout.adapter_text,null);
                x.view().inject(holder,view);
                view.setTag(holder);
            }else {
                holder= (ViewHolder) view.getTag();
            }
            if (i==0){
                holder.text.setTextColor(zContext.getResources().getColor(R.color.red));
                holder.linear_layout.setBackgroundResource(R.drawable.default_background_red_line);
                holder.img_sanjiao.setVisibility(View.VISIBLE);
            }else {
                holder.text.setTextColor(zContext.getResources().getColor(R.color.black_333));
                holder.linear_layout.setBackgroundResource(R.drawable.default_background_line_black);
                holder.img_sanjiao.setVisibility(View.GONE);
            }
            holder.text.setText("DT20574023412");
            return view;
        }
        class ViewHolder{
            @ViewInject(R.id.text)
            TextView text;
            @ViewInject(R.id.linear_layout)
            ConstraintLayout linear_layout;
            @ViewInject(R.id.img_sanjiao)
            ImageView img_sanjiao;
        }
    }

}
