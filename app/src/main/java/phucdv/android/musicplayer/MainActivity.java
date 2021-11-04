package phucdv.android.musicplayer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static int REQUEST_CODE_SELECT_MUSIC = 123;

    private TextView mTxtMusicIndex;
    private TextView mTxtMusicName;
    private TextView mTxtDuration;
    private int mIndexSelected;

    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Trình Nghe Nhạc");

        mTxtMusicIndex = findViewById(R.id.tv_music_index);
        mTxtMusicName = findViewById(R.id.tv_music_name);
        mTxtDuration = findViewById(R.id.tv_duration);

        findViewById(R.id.btn_select_music).setOnClickListener(this);
        findViewById(R.id.btn_play).setOnClickListener(this);
        findViewById(R.id.btn_pause).setOnClickListener(this);


        mMediaPlayer = new MediaPlayer();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_select_music:
                mMediaPlayer.pause();
                startActivityForResult(new Intent(this, SelectMusicActivity.class), REQUEST_CODE_SELECT_MUSIC);
                break;
            case R.id.btn_play:
                mMediaPlayer.start();
                break;
            case R.id.btn_pause:
                mMediaPlayer.pause();
                break;
            default:
                // @TODO: default
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_SELECT_MUSIC && resultCode == RESULT_OK){
            mIndexSelected = data.getIntExtra(SelectMusicActivity.EXTRA_INDEX_MUSIC, 0);
            mTxtMusicIndex.setText("Bài " + mIndexSelected);

            int resID = data.getIntExtra(SelectMusicActivity.EXTRA_RES_ID_MUSIC, 0);
            mMediaPlayer.reset();
            mMediaPlayer = MediaPlayer.create(this, resID);

            Time time = new Time();
            time.set(mMediaPlayer.getDuration());
            mTxtDuration.setText(getString(R.string.time_format, time.minute, time.second));

            Resources resources = getResources();
            Uri uri = new Uri.Builder()
                    .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
                    .authority(resources.getResourcePackageName(resID))
                    .appendPath(resources.getResourceTypeName(resID))
                    .appendPath(resources.getResourceEntryName(resID))
                    .build();
            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
            retriever.setDataSource(this, uri);
            mTxtMusicName.setText(retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE));
        }
    }
}