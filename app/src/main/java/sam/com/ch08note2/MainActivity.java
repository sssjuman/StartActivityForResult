package sam.com.ch08note2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    String[] aMemo = {"1. 按一下可以編輯備忘", "2. 長按可以清除備忘", "3. ", "4. ", "5. ", "6. "};
    ListView memo;
    ArrayAdapter<String> memoAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        memo = (ListView) findViewById(R.id.lv_memo);
        memoAd = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, aMemo);
        memo.setAdapter(memoAd);

        memo.setOnItemClickListener(this);
        memo.setOnItemLongClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent it = new Intent(this, Edit.class);
        it.putExtra("備忘", aMemo[i]);

        //在此以項目位置i作為識別碼 (在onActivityResult()更新陣列內容會用到)
        //當新Activity傳回資料時，會自動傳回此識別碼
        startActivityForResult(it, i);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        aMemo[i] = (i + 1) + ".";        //將內容清除只剩編號   ex. "1."
        memoAd.notifyDataSetChanged();   //通知ListView要更新顯示的內容
        return true;
    }


    //參數requestCode為項目位置i
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {

            //加上 if (data != null)，因為getStringExtra()可能產生'NullPointerException'
            if (data != null) {
                aMemo[requestCode] = data.getStringExtra("備忘1");
                memoAd.notifyDataSetChanged();

                Toast.makeText(this, data.getStringExtra("日期") + "修改", Toast.LENGTH_LONG).show();
            }

        }
    }
}

