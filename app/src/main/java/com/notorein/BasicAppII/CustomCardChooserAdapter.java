package com.notorein.BasicAppII;

import static com.notorein.BasicAppII.UISettingsItems.createDrawable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.notorein.BasicApp.R;
import com.notorein.BasicAppII.Parameter.Parameter;

public class CustomCardChooserAdapter
        extends BaseAdapter {

    public static LayoutInflater inflater;
    private final String[][] words;
    private final Context context;
    private TextView coverOld;
    private EditText textviewHintOld;
    private EditText textviewAnswerOld;
    private EditText textviewQuestionOld;


    public CustomCardChooserAdapter(String[][] loadedCardsAsArray, Context context) {
        this.words = Arrays.loadedCardsAsArray;
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public int getCount() {
        return words.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.list_item_cards, null);
        EditText textviewQuestion = convertView.findViewById(R.id.textviewQuestion);
        EditText textviewAnswer = convertView.findViewById(R.id.textviewAnswer);
        EditText textviewHint = convertView.findViewById(R.id.textviewHint);

        TextView cover = convertView.findViewById(R.id.cover);
        cover.setBackground(createDrawable(Colors.btn_background_stroke_day_selected, Colors.hint_text_color, 0, 0));
        if (Parameter.mode == 0) {
            textviewQuestion.setTextColor(Colors.btn_text_color);
            textviewAnswer.setTextColor(Colors.btn_text_color);
            textviewHint.setTextColor(Colors.btn_text_color);
        } else {
            textviewQuestion.setTextColor(Colors.background);
            textviewAnswer.setTextColor(Colors.background);
            textviewHint.setTextColor(Colors.background);
        }

        coverOld = cover;
        cover.setOnClickListener(c -> {
            try {
                coverOld.setBackground(createDrawable(Colors.btn_background_stroke_day_selected, Colors.hint_text_color, 0, 0));
//                textviewQuestionOld.setTextColor(Colors.btn_text_color);
//                textviewAnswerOld.setTextColor(Colors.btn_text_color);
//                textviewHintOld.setTextColor(Colors.btn_text_color);

                if (Parameter.mode == 0) {
                    textviewQuestionOld.setTextColor(Colors.btn_text_color);
                    textviewAnswerOld.setTextColor(Colors.btn_text_color);
                    textviewHintOld.setTextColor(Colors.btn_text_color);
                } else {
                    textviewQuestionOld.setTextColor(Colors.background);
                    textviewAnswerOld.setTextColor(Colors.background);
                    textviewHintOld.setTextColor(Colors.background);
                }

            } catch (Exception e) {

            }
            cover.setBackground(createDrawable(Colors.btn_background_stroke_day_selected, Colors.hint_text_color, 0, 18));

            if (Parameter.mode == 0) {
                textviewQuestion.setTextColor(Colors.btn_text_color);
                textviewAnswer.setTextColor(Colors.btn_text_color);
                textviewHint.setTextColor(Colors.btn_text_color);
            } else {
                textviewQuestion.setTextColor(Colors.background);
                textviewAnswer.setTextColor(Colors.background);
                textviewHint.setTextColor(Colors.background);
            }

            if (cover != coverOld) {
                try {

                    if (Parameter.mode == 0) {
                        textviewQuestionOld.setTextColor(Colors.btn_text_color);
                        textviewAnswerOld.setTextColor(Colors.btn_text_color);
                        textviewHintOld.setTextColor(Colors.btn_text_color);
                    } else {
                        textviewQuestionOld.setTextColor(Colors.background);
                        textviewAnswerOld.setTextColor(Colors.background);
                        textviewHintOld.setTextColor(Colors.background);
                    }
                } catch (Exception e) {

                }
            }


            textviewQuestionOld = textviewQuestion;
            textviewAnswerOld = textviewAnswer;
            textviewHintOld = textviewHint;
            Arrays.editedCardIndex = position;
//            Toast.makeText(context,""+   Arrays.editedCardIndex, Toast.LENGTH_SHORT).show();
            coverOld = cover;
//            Log.i(TAG, position + "  setListView: " + editedCardIndex + " "+ lessonNames.length);
        });
//        cover.setBackgroundColor(Colors.background);
        textviewQuestion.setText(words[position][Arrays.questionCardIndex]);
        textviewAnswer.setText(words[position][Arrays.answerCardIndex]);
        textviewHint.setText(words[position][Arrays.questionCardHintIndexMarker]);
        return convertView;
    }
}

