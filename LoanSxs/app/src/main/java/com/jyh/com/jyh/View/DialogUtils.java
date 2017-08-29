package com.jyh.com.jyh.View;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jyh.com.jyh.Activity.PersonalDataActivity;
import com.jyh.com.jyh.Adapter.BottomDialogListviewAdapter;
import com.jyh.com.jyh.R;
import com.jyh.com.jyh.Utils.AppUtil;
import com.jyh.com.jyh.Utils.DisplayUtils;
import com.jyh.com.jyh.Utils.SharedPreferencesUtils;
import com.jyh.com.jyh.View.dialogUtils.BottomDialog;

import java.util.List;
import java.util.Map;

/**
 * Created by vvguoliang on 2017/8/25.
 * 公共 弹出框
 */

@SuppressWarnings("ConstantConditions")
public class DialogUtils {
    /**
     * 单例对象实例
     */
    private static class DialogUtilsHolder {
        static final DialogUtils INSTANCE = new DialogUtils();
    }

    public static DialogUtils getInstance() {
        return DialogUtils.DialogUtilsHolder.INSTANCE;
    }

    /**
     * private的构造函数用于避免外界直接使用new来实例化对象
     */
    private DialogUtils() {
    }

    public void getDialogPrompt(final Context context, String title, String comtext, String buttonstring) {
        PublicPromptDialog.Builder builder = new PublicPromptDialog.Builder( context );
        builder.setTitle( title );
        builder.setContext( comtext );
        builder.setItems( buttonstring, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent( context, PersonalDataActivity.class );
                context.startActivity( intent );
                dialogInterface.dismiss();
            }
        } );
        builder.create().show();
    }

    // 提示对话框方法
    public void showDialog(final Activity context, final Handler mHanler, final int magint, final String name, final String nameStinrg,
                           List<Map<String, Object>> list) {
        final BottomDialog bottomDialog = new BottomDialog( context, R.layout.dialog_buttom_listview );
        bottomDialog.getWindow().setWindowAnimations( R.style.AnimBottom );
        bottomDialog.setWidthHeight( AppUtil.getInstance().Dispay( context )[0],
                DisplayUtils.dip2px( context, AppUtil.getInstance().Dispay( context )[1] / 6 ) );
        bottomDialog.getWindow().setGravity( Gravity.BOTTOM );

        final List<Map<String, Object>> mapList;
        String stringa = SharedPreferencesUtils.get( context, name, "" ).toString();
        if (!TextUtils.isEmpty( stringa )) {
            mapList = SharedPreferencesUtils.getInfo( context, stringa );
            if (null != mapList && mapList.size() > 0) {
                list.clear();
            }
            for (int i = 0; mapList.size() > i; i++) {
                if (!TextUtils.isEmpty( nameStinrg ) && mapList.get( i ).get( "name" ).equals( nameStinrg )) {
                    mapList.get( i ).put( "boolean", "1" );
                } else {
                    mapList.get( i ).put( "boolean", "2" );
                }
            }
        } else {
            mapList = list;
        }
        ListView bottom_dialog_listView = (ListView) bottomDialog.findViewById( R.id.bottom_dialog_listView );
        BottomDialogListviewAdapter bottomDialogListviewAdapter = new BottomDialogListviewAdapter( context, mapList );
        bottom_dialog_listView.setAdapter( bottomDialogListviewAdapter );
        bottomDialogListviewAdapter.notifyDataSetChanged();
        bottom_dialog_listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (mapList != null && mapList.size() > 0) {
                    mapList.get( i ).put( "boolean", "1" );
                    String strings = SharedPreferencesUtils.saveInfo( context, mapList );
                    SharedPreferencesUtils.put( context, name, strings );
                    Message message = new Message();
                    message.what = magint;
                    message.obj = mapList.get( i ).get( "name" );
                    mHanler.sendMessage( message );
                    bottomDialog.onItemClick( adapterView, view, i, l );
                }
            }
        } );

        if (!context.isFinishing()) {
            bottomDialog.show();
        }
    }
}
