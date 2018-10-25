package com.yihui.vertifycode;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 功能详细描述
 *
 * @author: zhouxing
 * @version: [1.0, 2018/6/25]
 * @see: [相关类/方法]
 * @describe: [产品/模块版本]
 */
public class VerifyCodeView extends RelativeLayout {
    /**
     * 输入框数量
     */
    private final int boxNumber;
    private EditText editText;
    private TextView[] textViews;
//    private static int MAX = 4;
    private String inputContent;
    private int layout = 0;
    public VerifyCodeView(Context context) {
        this(context, null);
    }

    public VerifyCodeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VerifyCodeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.VerifyCodeView);
        boxNumber = typedArray.getColor(R.styleable.VerifyCodeView_boxNumber, 0);
        View.inflate(context, R.layout.view_verify_code, this);

        textViews = new TextView[boxNumber];
        for (int i =0;i<boxNumber;i++){
            switch (i){
                case 0:
                    textViews[i] = (TextView) findViewById(R.id.tv_0);
                    break;
                case 1:
                    textViews[i] = (TextView) findViewById(R.id.tv_1);
                    break;
                case 2:
                    textViews[i] = (TextView) findViewById(R.id.tv_2);
                    break;
                case 3:
                    textViews[i] = (TextView) findViewById(R.id.tv_3);
                    break;
            }

        }

        editText = (EditText) findViewById(R.id.edit_text_view);

        editText.setCursorVisible(false);//隐藏光标
        setEditTextListener();
    }

    private void setEditTextListener() {
        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                inputContent = editText.getText().toString();

                if (inputCompleteListener != null) {
                    if (inputContent.length() >= boxNumber) {
                        inputCompleteListener.inputComplete();
                    } else {
                        inputCompleteListener.invalidContent();
                    }
                }

                for (int i = 0; i < boxNumber; i++) {
                    if (i < inputContent.length()) {
                        textViews[i].setText(String.valueOf(inputContent.charAt(i)));
                    } else {
                        textViews[i].setText("");
                    }
                }
            }
        });
    }


    private InputCompleteListener inputCompleteListener;

    public void setInputCompleteListener(InputCompleteListener inputCompleteListener) {
        this.inputCompleteListener = inputCompleteListener;
    }

    public interface InputCompleteListener {

        void inputComplete();

        void invalidContent();
    }

    public String getEditContent() {
        return inputContent;
    }

    public void setEditText(String strings){
//        for (int i =0;i<4;i++){
//            textViews[i].setText(strings[i]);
//
//        }
        editText.setText(strings);
    }
    public void setLayout(int i){
        layout = i;
    }
}
