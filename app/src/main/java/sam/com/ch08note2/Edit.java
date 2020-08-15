package sam.com.ch08note2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

public class Edit extends AppCompatActivity {
    TextView tv_number;
    EditText et_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        et_edit = (EditText) findViewById(R.id.et_edit);
        tv_number = (TextView) findViewById(R.id.tv_number);

        Intent it = getIntent();                    //取得傳入的Intent物件
        String string = it.getStringExtra("備忘");  //從Intent中取出資料


        tv_number.setText(string.substring(0, 2));      //將編號顯示在TextView

        if (string.length() > 3) {
            et_edit.setText(string.substring(3));       //去除前3個字再填入EditText
        }
    }

    public void cancel(View v) {

        //呼叫setResult() 傳回執行的結果與修改後的資料給主Activity
        setResult(RESULT_CANCELED, null);

        finish();
    }

    public void save(View v) {

        Intent it2 = new Intent();
        it2.putExtra("備忘1", tv_number.getText() + " " + et_edit.getText());
        it2.putExtra("日期", new Date().toString());

        //呼叫setResult() 傳回執行的結果與修改後的資料給主Activity
        setResult(RESULT_OK, it2);

        finish();
    }
}
