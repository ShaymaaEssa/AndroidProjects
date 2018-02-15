    package com.example.android.miwok;

    import android.content.Context;
    import android.media.MediaPlayer;
    import android.support.annotation.NonNull;
    import android.support.v4.content.ContextCompat;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.ArrayAdapter;
    import android.widget.ImageView;
    import android.widget.TextView;

    import java.util.ArrayList;

    /**
     * Created by goeic admin on 08-Feb-18.
     */

    public class WordAdapter extends ArrayAdapter<Word> {
        int backgroudColor;
        Context context;
        MediaPlayer mMediaPlayer;

        public WordAdapter (Context context , ArrayList<Word> words, int backgroudColor){
            super(context,0,words);
            this.context = context;
            this.backgroudColor = backgroudColor;
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View listItemView = convertView;
            if (listItemView == null){ //check if the view is new and not reusable so inflate it.
                listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
            }

            TextView txtDefaultTranslation = (TextView)listItemView.findViewById(R.id.txt_defaulttranslation);
            TextView txtMiwokTranslation = (TextView)listItemView.findViewById(R.id.txt_miwoktranslation);
            ImageView imageListItemImage = (ImageView)listItemView.findViewById(R.id.image_listitemimg);
            final Word word = getItem(position);
            txtDefaultTranslation.setText(word.getmDefaultTranslation());
            txtMiwokTranslation.setText(word.getmMiwokTranslation());
            if (word.getImgResourceId() != -1)
            {
                imageListItemImage.setImageResource(word.getImgResourceId());
            } else {
                imageListItemImage.setVisibility(View.GONE);
            }
            int color = ContextCompat.getColor(getContext(), backgroudColor);
            listItemView.findViewById(R.id.linearlayout_listitem).setBackgroundColor(color);

            return listItemView;
        }


    }
