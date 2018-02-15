package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_family);

        mAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("father","әpә",R.drawable.family_father, R.raw.family_father));
        words.add(new Word("mother","әṭa",R.drawable.family_mother, R.raw.family_mother));
        words.add(new Word("son","angsi",R.drawable.family_son, R.raw.family_son));
        words.add(new Word("daughter","tune",R.drawable.family_daughter, R.raw.family_daughter));
        words.add(new Word("older brother","taachi",R.drawable.family_older_brother, R.raw.family_older_brother));
        words.add(new Word("younger brother","chalitti",R.drawable.family_younger_brother, R.raw.family_younger_brother));
        words.add(new Word("older sister","teṭe",R.drawable.family_older_sister, R.raw.family_older_sister));
        words.add(new Word("younger sister","kolliti",R.drawable.family_younger_sister, R.raw.family_younger_sister));
        words.add(new Word("grandmother","ama",R.drawable.family_grandmother, R.raw.family_grandmother));
        words.add(new Word("grandfather","paapa",R.drawable.family_grandfather, R.raw.family_grandfather));



        WordAdapter itemsAdapter = new WordAdapter(this, words, R.color.category_family);

        ListView listView = (ListView) findViewById(R.id.activity_family);

        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {



                Word word = words.get(i);
                releaseMediaPlayer();
                int result = mAudioManager.requestAudioFocus(onAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    mMediaPlayer = MediaPlayer.create(FamilyActivity.this,word.getAudioFile());
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