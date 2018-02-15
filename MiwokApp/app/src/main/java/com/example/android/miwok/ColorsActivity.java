    package com.example.android.miwok;

    import android.content.Context;
    import android.content.Intent;
    import android.media.AudioManager;
    import android.media.MediaPlayer;
    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.AdapterView;
    import android.widget.ListView;
    import android.widget.TextView;

    import java.util.ArrayList;

    public class ColorsActivity extends AppCompatActivity {


        MediaPlayer mMediaPlayer;
        AudioManager mAudioManager;
        AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
            @Override
            public void onAudioFocusChange(int i) {
                if (i == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || i == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                    mMediaPlayer.pause();
                    mMediaPlayer.seekTo(0);

                }
                else if (i == AudioManager.AUDIOFOCUS_GAIN){
                    mMediaPlayer.start();

                }else if (i == AudioManager.AUDIOFOCUS_LOSS){
                    releaseMediaPlayer();

                }
            }
        };

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_colors);

            mAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

            final ArrayList<Word> words = new ArrayList<Word>();
            words.add(new Word("red","weṭeṭṭi",R.drawable.color_red, R.raw.color_red));
            words.add(new Word("green","chokokki",R.drawable.color_green, R.raw.color_green));
            words.add(new Word("brown","ṭakaakki",R.drawable.color_brown, R.raw.color_brown));
            words.add(new Word("gray","ṭopoppi",R.drawable.color_gray, R.raw.color_gray));
            words.add(new Word("black","kululli",R.drawable.color_black, R.raw.color_black));
            words.add(new Word("white","kelelli",R.drawable.color_white, R.raw.color_white));
            words.add(new Word("dusty yellow","ṭopiisә",R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
            words.add(new Word("mustard yello","chiwiiṭә",R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));



            WordAdapter itemsAdapter = new WordAdapter(this, words , R.color.category_colors);

            ListView listView = (ListView) findViewById(R.id.activity_colors);

            listView.setAdapter(itemsAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {



                    Word word = words.get(i);
                    releaseMediaPlayer();
                    int result = mAudioManager.requestAudioFocus(onAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
                    if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    mMediaPlayer = MediaPlayer.create(ColorsActivity.this,word.getAudioFile());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            releaseMediaPlayer();
                        }
                    });

                    }
                }
            });


        }

        private void releaseMediaPlayer() {
            // If the media player is not null, then it may be currently playing a sound.
            if (mMediaPlayer != null) {
                // Regardless of the current state of the media player, release its resources
                // because we no longer need it.
                mMediaPlayer.release();

                // Set the media player back to null. For our code, we've decided that
                // setting the media player to null is an easy way to tell that the media player
                // is not configured to play an audio file at the moment.
                mMediaPlayer = null;
                mAudioManager.abandonAudioFocus(onAudioFocusChangeListener);
            }
        }

        @Override
        protected void onStop() {
            super.onStop();
            releaseMediaPlayer();
        }
    }
