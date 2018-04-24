package com.midux.custominputdialog;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DialogInterface.OnKeyListener keylistener = new DialogInterface.OnKeyListener() {
        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
            Log.i("TAG", "键盘code---" + keyCode);
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                dialog.dismiss();
                return false;
            } else if(keyCode == KeyEvent.KEYCODE_DEL){//删除键
//                if(dialog != null){
//                    mCustomDialog.del();
//                }
                return false;
            }else{
                return true;

            }
        }
    };
    private RecyclerView mRecyclerView;
    private CustomDialog mCustomDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);

        List<String> pic_list = new ArrayList<>();
        pic_list.add("图片");

        List<CommentBean> commentBeans = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            commentBeans.add(new CommentBean("小名：", "评论1评论1评论1评论1评论1评论1"));
            commentBeans.add(new CommentBean("小王：", "wangawanngwahoiahsifhohafoahsgoahsofhuhuoafwangawanngwahoiahsifhohafoahsgoahsofhuhuoaf"));
            commentBeans.add(new CommentBean("笑脸：", "笑笑笑笑"));
        }

        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        mRecyclerView.setLayoutManager(layoutmanager);

        CustommRecyclerViewAdapter adapter = new CustommRecyclerViewAdapter(this, pic_list, commentBeans);
        mRecyclerView.setAdapter(adapter);

        adapter.setmOnItemClickListener(new CustommRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                mCustomDialog = new CustomDialog(MainActivity.this, R.style.customdialogstyle);
                mCustomDialog.setOnKeyListener(keylistener);
                mCustomDialog.show();
            }
        });
    }
}
