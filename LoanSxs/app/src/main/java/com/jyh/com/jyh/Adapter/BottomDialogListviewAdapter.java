package com.jyh.com.jyh.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by vvguoliang on 2017/8/28.
 */

public class BottomDialogListviewAdapter extends BaseAdapter {


    private List<String> list;

    public BottomDialogListviewAdapter(Context context, List<String> list) {
        this.list = list;

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
        return view;
    }
}
