package com.jyh.com.jyh.EntityClass.DataDisplay;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.jyh.com.jyh.Activity.PersonalDataInformation;
import com.jyh.com.jyh.EntityClass.PersonalDataEntity;
import com.jyh.com.jyh.R;
import com.jyh.com.jyh.RecyclerView.BaseViewHolder;

/**
 * Created by vvguoliang on 2017/8/25.
 * <p>
 * 个人资料数据展示
 */

public class PersonalDataHolder extends BaseViewHolder<PersonalDataEntity> {

    private ImageView personal_data_image;
    private TextView personal_data_information;
    private TextView personal_data_effective;
    private TextView personal_data_authentication;

    private ViewGroup parent;


    public PersonalDataHolder(ViewGroup parent) {
        super( parent, R.layout.adapter_personal_data_item );
        this.parent = parent;
    }

    //数据写入
    @Override
    public void setData(PersonalDataEntity data) {
        super.setData( data );
        Glide.with( parent.getContext() )
                .load( data.getUrl_path() )
                .listener( new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object o, Target<Drawable> target, boolean b) {
                        personal_data_image.setImageResource( R.mipmap.ic_loan_banner_default );
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable drawable, Object o, Target<Drawable> target, DataSource dataSource, boolean b) {
                        return false;
                    }
                } )
                .into( personal_data_image );
        personal_data_information.setText( data.getTitil() );
        personal_data_effective.setText( data.getBottom() );
        if (!TextUtils.isEmpty( data.getStater() )) {
            if (data.getStater().equals( "1" )) {
                personal_data_authentication.setText( parent.getContext().getString( R.string.loan_name_loan_personal_authentication ) );
                personal_data_authentication.setTextColor( Color.parseColor( "#75C328" ) );
            } else {
                personal_data_authentication.setText( parent.getContext().getString( R.string.loan_name_loan_personal_no_authentication ) );
                personal_data_authentication.setTextColor( Color.parseColor( "#AFB0AF" ) );
            }
        } else {
            personal_data_authentication.setText( parent.getContext().getString( R.string.loan_name_loan_personal_no_authentication ) );
            personal_data_authentication.setTextColor( Color.parseColor( "#AFB0AF" ) );
        }

    }

    //初始化
    @Override
    public void onInitializeView() {
        super.onInitializeView();
        personal_data_image = (ImageView) findViewById( R.id.personal_data_image );
        personal_data_information = (TextView) findViewById( R.id.personal_data_information );
        personal_data_effective = (TextView) findViewById( R.id.personal_data_effective );
        personal_data_authentication = (TextView) findViewById( R.id.personal_data_authentication );
    }

    @Override
    public void onItemViewClick(PersonalDataEntity data) {
        super.onItemViewClick( data );
        Intent intent = new Intent( parent.getContext(), PersonalDataInformation.class );
        parent.getContext().startActivity( intent );
    }
}
