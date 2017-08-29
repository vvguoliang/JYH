package com.jyh.com.jyh.View;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jyh.com.jyh.R;
import com.jyh.com.jyh.Utils.DisplayUtils;

/**
 * Created by vvguoliang on 2017/6/28.
 * 公共弹出框 列表
 */
@SuppressLint("InflateParams")
public class PublicPromptDialog extends Dialog {

    public PublicPromptDialog(@NonNull Context context, int themeResId) {
        super( context, themeResId );
    }

    public static class Builder {
        private Context context;
        private String title;
        private String comtext;
        private String buttonstring;
        private OnClickListener itemseButtonClickListener;

        public Builder(Context context) {
            this.context = context;
        }

        /**
         * Set the Dialog title from String
         *
         * @param title
         * @return
         */
        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setContext(String comtext) {
            this.comtext = comtext;
            return this;
        }

        public void setItems(String buttonstring, OnClickListener listener) {
            this.itemseButtonClickListener = listener;
            this.buttonstring = buttonstring;
        }

        public PublicPromptDialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            // instantiate the dialog with the custom Theme
            final PublicPromptDialog dialog = new PublicPromptDialog( context, R.style.Dialog );
            View layout = inflater.inflate( R.layout.dialog_public_prompt, null );
            dialog.addContentView( layout, new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT ) );

             /*
         * 获取圣诞框的窗口对象及参数对象以修改对话框的布局设置,
         * 可以直接调用getWindow(),表示获得这个Activity的Window
         * 对象,这样这可以以同样的方式改变这个Activity的属性.
         */
            Window dialogWindow = dialog.getWindow();
            WindowManager.LayoutParams lp;
            if (dialogWindow != null) {
                lp = dialogWindow.getAttributes();
                dialogWindow.setGravity( Gravity.CENTER );
                lp.width = DisplayUtils.dip2px( context, 250 ); // 宽度
                dialogWindow.setAttributes( lp );
            }
            dialog.setCanceledOnTouchOutside( false );
            dialog.setCancelable( false );

            TextView dialog_title = layout.findViewById( R.id.dialog_title );
            dialog_title.setText( title );
            TextView dialog_context = layout.findViewById( R.id.dialog_context );
            dialog_context.setText( comtext );
            Button dialog_button = layout.findViewById( R.id.dialog_button );
            dialog_button.setText( buttonstring );

            if (null != itemseButtonClickListener) {
                dialog_button.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        itemseButtonClickListener.onClick( dialog, DialogInterface.BUTTON_POSITIVE );
                    }
                } );
            }
            dialog.setContentView( layout );
            return dialog;
        }
    }
}
