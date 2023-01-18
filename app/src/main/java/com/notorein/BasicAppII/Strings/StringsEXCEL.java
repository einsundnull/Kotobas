package com.notorein.BasicAppII.Strings;

public class StringsEXCEL {

    public static    String missingQuestion = "QUESTION IS MISSING";
    public static   String missingAnswer = "ANSWER IS MISSING";

    public static     String missingQuestionHint = "#";
    public static    String missingAnswerHint = "#";
    public static    String newColumn = "↔";
    public static    String newLine  = "↨";

    boolean allowRevereCard = true;
    boolean showQuestionHint = false;


    public static String QUESTION = "Your question";
    public static String ANSWER = "Your answer";
    public static String QUESTIONHINT = "Your hint";
    public static String ANSWERHINT = "#";
    public static String ALLOWREVERSECARD = "TRUE";
    public static String SHOWHINTQUESTION = "FALSE";
    public static String REPEATINDEXQUESTION = "1";
    public static String REPEATINDEXANSWER = "1";
    public static String REPEATTIMEQUESTION = "0";
    public static String REPEATTIMEANSWER = "0";
    public static String TIMESTEPQUESTION = "1";
    public static String TIMESTEPANSWER = "1";
    public static String COUNTRIGHTQUESTION = "0";
    public static String COUNTRIGHTANSWER = "0";
    public static String COUNTWRONGQUESTION = "0";
    public static String COUNTWRONGANSWER = "0";
    public static String SORTINDEX = "1";
    public static String LESSON_INFO_I = "SOUNDDIRECTORY";
    public static String LESSON_INFO_II = "0";

    public static String ckBxShowQuestionHintText = "Show hint with answer";
    public static String ckBxAllowReverseText = "Allow to reverse card";

    public static String[] lessonTemplateEmpty = {QUESTION, ANSWER, QUESTIONHINT, ANSWERHINT, ALLOWREVERSECARD, SHOWHINTQUESTION, REPEATINDEXQUESTION, REPEATINDEXANSWER, REPEATTIMEQUESTION, REPEATTIMEANSWER, TIMESTEPQUESTION, TIMESTEPANSWER, COUNTRIGHTQUESTION, COUNTRIGHTANSWER, COUNTWRONGQUESTION, COUNTWRONGANSWER, SORTINDEX, LESSON_INFO_I, LESSON_INFO_II};
    public static String removeRegex(String text) {
        text = text.replaceAll("\t", StringsEXCEL.newColumn).replaceAll("\n", StringsEXCEL.newLine);
        return text;
    }

    public static  String insertRegex(String text) {
        text = text.replaceAll(StringsEXCEL.newColumn, "\t").replaceAll(StringsEXCEL.newLine, "\n");
        return text;
    }
}
