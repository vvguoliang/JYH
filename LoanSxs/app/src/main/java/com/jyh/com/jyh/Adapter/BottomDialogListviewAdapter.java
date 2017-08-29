package com.jyh.com.jyh.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jyh.com.jyh.R;

import java.util.List;
import java.util.Map;

/**
 * Created by vvguoliang on 2017/8/28.
 * 公共 底部弹出框
 */
@SuppressLint("InflateParams")
public class BottomDialogListviewAdapter extends BaseAdapter {

    private List<Map<String, Object>> list;
    private LayoutInflater mInflater;

    public BottomDialogListviewAdapter(Context context, List<Map<String, Object>> list) {
        this.list = list;
        mInflater = LayoutInflater.from( context );
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get( i );
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = mInflater.inflate( R.layout.dialog_buttom_listview_item, null );
            viewHolder.dialog_button_listView_line = view.findViewById( R.id.dialog_button_listView_line );
            viewHolder.dialog_button_listView_text = view.findViewById( R.id.dialog_button_listView_text );
            view.setTag( viewHolder );
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.dialog_button_listView_text.setText( list.get( i ).get( "name" ).toString() );
        if (list.get( i ).get( "boolean" ).equals( "1" )) {//选中
            viewHolder.dialog_button_listView_line.setBackgroundColor( Color.parseColor( "#F5F5F5" ) );
            viewHolder.dialog_button_listView_text.setTextColor( Color.parseColor( "#43A0EB" ) );
        } else {
            viewHolder.dialog_button_listView_line.setBackgroundColor( Color.parseColor( "#ffffff" ) );
            viewHolder.dialog_button_listView_text.setTextColor( Color.parseColor( "#7D8791" ) );
        }
        return view;
    }

    class ViewHolder {
        LinearLayout dialog_button_listView_line;
        TextView dialog_button_listView_text;
    }
}
