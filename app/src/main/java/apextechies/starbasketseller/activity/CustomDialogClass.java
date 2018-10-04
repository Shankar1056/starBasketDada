package apextechies.starbasketseller.activity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import apextechies.starbasketseller.R;

public class CustomDialogClass extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button yes, no;
    public TextView message;
    public String msg;

    public CustomDialogClass(Activity a, String from) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        this.msg = from;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
        message = (TextView)findViewById(R.id.txt_dia);
        if (msg.equals("login")){
            message.setText("Your registration is under verification process, please wait for approval. You may contact with us at 9232116121");
        }else {
            message.setText("Thank you for register with us. Please wait for approval. You may contact with us at 9232116121");
        }
        yes = (Button) findViewById(R.id.btn_yes);
        yes.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_yes:
                c.finish();
                break;
            default:
                break;
        }
        dismiss();
    }
}