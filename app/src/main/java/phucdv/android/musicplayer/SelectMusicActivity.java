package phucdv.android.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SelectMusicActivity extends AppCompatActivity implements View.OnClickListener {

    public static String EXTRA_INDEX_MUSIC = "phucdv.extra.index_music";
    public static String EXTRA_RES_ID_MUSIC = "phucdv.extra.res_id_music";

    private TextView mTxtIndex;
    private int mIndexSelected;
    private int mResIdSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_music);

        getSupportActionBar().setTitle("Chọn bài hát");

        mTxtIndex = findViewById(R.id.tv_index);

        findViewById(R.id.btn_select).setOnClickListener(this);
        findViewById(R.id.btn_index_1).setOnClickListener(this);
        findViewById(R.id.btn_index_2).setOnClickListener(this);
        findViewById(R.id.btn_index_3).setOnClickListener(this);
        findViewById(R.id.btn_index_4).setOnClickListener(this);
        findViewById(R.id.btn_index_5).setOnClickListener(this);
        findViewById(R.id.btn_index_6).setOnClickListener(this);
        findViewById(R.id.btn_index_7).setOnClickListener(this);
        findViewById(R.id.btn_index_8).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_select:
                Intent intent = new Intent();
                intent.putExtra(EXTRA_INDEX_MUSIC, mIndexSelected);
                intent.putExtra(EXTRA_RES_ID_MUSIC, mResIdSelected);
                if(mIndexSelected == 0){
                    setResult(RESULT_CANCELED);
                } else {
                    setResult(RESULT_OK, intent);
                }
                finish();
                break;
            case R.id.btn_index_1:
                mResIdSelected = R.raw.anh_khong_tha_thu;
                mIndexSelected = Integer.parseInt(((Button)v).getText().toString());
                mTxtIndex.setText(mIndexSelected + "");
                break;
            case R.id.btn_index_2:
                mResIdSelected = R.raw.binz_021;
                mIndexSelected = Integer.parseInt(((Button)v).getText().toString());
                mTxtIndex.setText(mIndexSelected + "");
                break;
            case R.id.btn_index_3:
                mResIdSelected = R.raw.co_gai_vang;
                mIndexSelected = Integer.parseInt(((Button)v).getText().toString());
                mTxtIndex.setText(mIndexSelected + "");
                break;
            case R.id.btn_index_4:
                mResIdSelected = R.raw.em_da_thuong_nguoi_ta_hon_anh;
                mIndexSelected = Integer.parseInt(((Button)v).getText().toString());
                mTxtIndex.setText(mIndexSelected + "");
                break;
            case R.id.btn_index_5:
                mResIdSelected = R.raw.hat_cat_bui_vang;
                mIndexSelected = Integer.parseInt(((Button)v).getText().toString());
                mTxtIndex.setText(mIndexSelected + "");
                break;
            case R.id.btn_index_6:
                mResIdSelected = R.raw.ong_ba_gia_tao_lo_het;
                mIndexSelected = Integer.parseInt(((Button)v).getText().toString());
                mTxtIndex.setText(mIndexSelected + "");
                break;
            case R.id.btn_index_7:
                mResIdSelected = R.raw.son_tinh_thuy_tinh;
                mIndexSelected = Integer.parseInt(((Button)v).getText().toString());
                mTxtIndex.setText(mIndexSelected + "");
                break;
            case R.id.btn_index_8:
                mResIdSelected = R.raw.thien_dang;
                mIndexSelected = Integer.parseInt(((Button)v).getText().toString());
                mTxtIndex.setText(mIndexSelected + "");
                break;
            default:
                // @TODO: default
                break;
        }
    }
}