package com.notorein.BasicAppII.Strings;

import static com.notorein.BasicAppII.Parameter.Parameter.defaultTextSize;
import static com.notorein.BasicAppII.Parameter.Parameter.newTextSize;
import static com.notorein.BasicAppII.Parameter.Parameter.textPaint;
import static com.notorein.BasicAppII.Parameter.Parameter.txt;
import static com.notorein.BasicAppII.Parameter.Parameter.weightedTextSize;
import static com.notorein.BasicAppII.Parameter.Parameter.widthOfTextView;
import static com.notorein.BasicAppII.Parameter.Parameter.widthTextFromTextView;

import android.graphics.Rect;
import android.widget.TextView;

import com.notorein.BasicAppII.Parameter.Parameter;

import java.util.ArrayList;

public class StringsAdaptAndCut {
    public static Rect bounds;

    public final static TextView adaptFontSize(TextView txtField, double fontSize, boolean textIsQuestion) {
        String word = txtField.getText().toString();
        txtField = splitLongString(txtField, word);
//        txtField.setText(word);
//        if (Parameter.doubleTextViewSize) {
//            txtField.setHeight(txtField.getHeight() * 5);
//        }

        return txtField;
    }

    public static void limitingMaxStringLengthLine() {

        if (defaultTextSize > 10) {
            Parameter.maxStringLengthLine = 35;
            Parameter.doubleTextViewSize = true;
        } else {
            Parameter.maxStringLengthLine = 55;
        }
    }

    public static TextView splitLongString(TextView textView, String word) {
        word = word.replace(System.lineSeparator(), "");
        char[] temp = word.toCharArray();
        String[] tempII = null;
        String wordPartI = "";
        String wordPartII = "";
        int stringLength = temp.length;
        limitingMaxStringLengthLine();
        if (stringLength >= Parameter.maxStringLengthLine) {
            tempII = new String[temp.length];
            for (int i = 0; i < temp.length; i++) {
                tempII[i] = "" + temp[i];
            }
            ArrayList<Integer> whiteSpaceIndex = new ArrayList<Integer>();
            int whiteSpaceCounter = 0;
            int splitIndex = 0;
            for (int i = 0; i < stringLength; i++) {
                String item = "" + temp[i];
                if (item.equals(" ")) {
                    whiteSpaceCounter++;
                    whiteSpaceIndex.add(i);
                }
            }
            if (whiteSpaceCounter > 2) {
                if (whiteSpaceCounter % 2 == 0) {
                    whiteSpaceCounter = whiteSpaceCounter + 1;
                }

                whiteSpaceCounter = (whiteSpaceCounter / 2);
                splitIndex = whiteSpaceIndex.get(whiteSpaceCounter + 1);
                word = "";
                for (int i = 0; i < stringLength; i++) {
                    if (i <= splitIndex) {
                        wordPartI = wordPartI + tempII[i];
                    } else {
                        wordPartII = wordPartII + tempII[i];
                    }
                }
            }
            textView.setText(wordPartI + wordPartII);
//            textView.setText(wordPartI + System.lineSeparator() + wordPartII);
        }
        return textView;
    }

    public static TextView adaptTextViewTextSize(TextView view, double percentage) {
        // AI
        defaultTextSize = 40;
        view.setTextSize(defaultTextSize);
        widthOfTextView = view.getWidth();
        txt = (String) view.getText().toString();
        textPaint = view.getPaint();
        bounds = new Rect();
        textPaint.getTextBounds(txt, 0, txt.length(), bounds);
        widthTextFromTextView = bounds.width();

        newTextSize = defaultTextSize;
        while (widthOfTextView < widthTextFromTextView-50) {
            newTextSize--;
            bounds = new Rect();
            textPaint = view.getPaint();
            textPaint.getTextBounds(txt, 0, txt.length(), bounds);
            widthTextFromTextView = (int)(bounds.width()*0.82);
            view.setTextSize(newTextSize);
        }

        weightedTextSize = newTextSize * percentage;
        newTextSize = (float) Math.max(20, weightedTextSize);
        view.setTextSize(newTextSize);
        return view;
    }

//    public static TextView adaptTextViewTextSize(TextView view, double percentage) {
//        // set a text size that is always greater than the width of the TextView
//        defaultTextSize = 40;
//        view.setTextSize(defaultTextSize);
//        // Here we get the width of the text from the TextView;
//      widthOfTextView = view.getWidth();
//        txt = (String) view.getText();
//        bounds = new Rect();
//        textPaint = view.getPaint();
//       textPaint.getTextBounds(txt, 0, txt.length(), bounds);
//        widthTextFromTextView = bounds.width();
//
//        newTextSize = defaultTextSize;
//        view.setTextSize(newTextSize);
//        view = splitLongString(view, txt);
//        while (widthOfTextView < widthTextFromTextView) {
//            // Here we compare the widthOfTextView with the widthTextFromTextView and decrement the widthTextFromTextView until
//            // we have the right size.
//            newTextSize--;
//            bounds = new Rect();
//           textPaint = view.getPaint();
//            textPaint.getTextBounds(txt, 0, txt.length(), bounds);
//            widthTextFromTextView = bounds.width();
//            view.setTextSize(newTextSize);
//        }
//        // Here we can set a percentage
//        weightedTextSize = newTextSize;
//        weightedTextSize = weightedTextSize * percentage;
//        if (weightedTextSize < 20) {
//            weightedTextSize = 20;
//        }
//        newTextSize = (float) weightedTextSize;
//        view.setTextSize(newTextSize);
//        view = splitLongString(view, txt);
//        return view;
//    }

}
