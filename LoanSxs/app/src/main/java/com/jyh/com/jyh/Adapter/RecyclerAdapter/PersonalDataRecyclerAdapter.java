package com.jyh.com.jyh.Adapter.RecyclerAdapter;

import android.content.Context;
import android.view.ViewGroup;

import com.jyh.com.jyh.EntityClass.DataDisplay.PersonalDataHolder;
import com.jyh.com.jyh.EntityClass.PersonalDataEntity;
import com.jyh.com.jyh.RecyclerView.BaseViewHolder;
import com.jyh.com.jyh.RecyclerView.RecyclerAdapter;

/**
 * Created by vvguoliang on 2017/8/25.
 * 个人资料 适配器
 */

public class PersonalDataRecyclerAdapter extends RecyclerAdapter<PersonalDataEntity> {

    public PersonalDataRecyclerAdapter(Context context) {
        super( context );
    }

    @Override
    public BaseViewHolder<PersonalDataEntity> onCreateBaseViewHolder(ViewGroup parent, int viewType) {
        return new PersonalDataHolder(parent);
    }
}
