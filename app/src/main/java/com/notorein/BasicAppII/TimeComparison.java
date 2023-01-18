package com.notorein.BasicAppII;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TimeComparison {

    //    private static final String dateFormat = "yyyyMMddHHmmss";
    public static final String dateFormat = "MMddHHmm";
    public final static long firstTime = 0;
    public static final String dateFormatMonth = "MM";
    //    public static final String dateFormatYear = "y";
    //    Time limit is two month
    public static final long timeLimit = 5184000;
    private static final int stepLimit = 12;
    public static int addTimeInMinutes = 4;
    public static long years = 0;
    // This int will be incremented as long the compared time (dateNow/dateFromCard) does not give a card that should be repeated.
    private static int tempTimeFinder = 0;
    private static int tempTimeII;


    public static long convertSecondsToDate(long timeInSeconds) {
        tempTimeII = 0;
        String secondsZero = "0";
        String minutesZero = "0";
        String hoursZero = "0";
        String daysZero = "0";
        String monthZero = "0";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormatMonth);
        String tempMonth = sdf.format(new Date());
//        sdf = new SimpleDateFormat(dateFormatYear);
//        String tempYear = sdf.format(new Date());
        long seconds = 0;
        long minutes = 0;
        long hours = 0;
        long days = 0;
        long months = 0;
        long years = 0;
        String timeString = "";

        for (int i = 0; i < timeInSeconds; i++) {
            seconds++;
            if (seconds < 10) {
                secondsZero = "0";
            } else {
                secondsZero = "";
            }
            if (seconds == 60) {
                minutes++;
                if (minutes < 10) {
                    minutesZero = "0";
                } else {
                    minutesZero = "";
                }
                seconds = 0;
                secondsZero = "0";
            }
            if (minutes == 60) {
                hours++;
                if (hours < 10) {
                    hoursZero = "0";
                } else {
                    hoursZero = "";
                }
                minutes = 0;
                minutesZero = "0";
            }
            if (hours == 24) {
                days++;
                if (days < 10) {
                    daysZero = "0";
                } else {
                    daysZero = "";
                }
                hours = 0;
                hoursZero = "0";
            }
            if (days == 30) {
                months++;
                if (months < 10) {
                    monthZero = "0";
                } else {
                    monthZero = "";
                }
                days = 0;
                daysZero = "0";
            }
            if (months > 12) {
//                years++;
                months = 12 - months;
                monthZero = "0";
            }
        }
        // This step is necessary because the long for the value can not handle yyyyMMddHHmmss but yMMddHHmms so I need to get rid of the last
        // For example 21 sec becomes 2
//        if(seconds > 9){
//            seconds = seconds/10;
//        }
//        if(years > 9){
//            years = years/10;
//        }
        timeString = monthZero + months + daysZero + days + hoursZero + hours + minutesZero + minutes;
        tempTimeII = Integer.parseInt(timeString);
        return tempTimeII;
    }

    public static void compareRepeatTime() {
        tempTimeFinder = 1;
        boolean foundRepeatTime = false;
        // The index is used to find the time when questionIsReversed
        int tempIndex;
        long tempDateFromCard = firstTime;
        if (AnswerLogic.questionIsReversed) {
            tempIndex = Arrays.answerRepeatTimeIndexMarker;
        } else {
            tempIndex = Arrays.questionRepeatTimeIndexMarker;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String temp = sdf.format(new Date());
        long dateNow = Long.parseLong(temp);
        long dateFromCard = Long.parseLong(Arrays.loadedCardsAsArray[tempTimeFinder][tempIndex]);
        ArrayList<long[]> diff = new ArrayList<>();


        for (int i = 1; i < Arrays.loadedCardsAsArray.length - 1; i++) {
            tempTimeFinder++;
            if (dateNow > dateFromCard) {
                dateFromCard = Long.parseLong(Arrays.loadedCardsAsArray[tempTimeFinder][tempIndex]);

                if (!("" + dateFromCard).equals(""+firstTime)) {
                    long timeDiff = dateNow - Long.parseLong(Arrays.loadedCardsAsArray[tempTimeFinder][tempIndex]);
                    long[] storeData = {timeDiff, tempTimeFinder};
                    diff.add(storeData);
                    foundRepeatTime = true;
                    System.err.println("Found a Date to repeat " + Arrays.loadedCardsAsArray[tempTimeFinder][Arrays.questionCardIndex] + " now " + dateNow + " card " + dateFromCard + " diff " + timeDiff);
                }
            }

        }

        if (!foundRepeatTime) {
            Arrays.indexNextCard = 1;
        } else {
            for (int n = 0; n < diff.size(); n++) {
                for (int i = diff.size() - 1; i >= 1; i--) {
                    long upper = diff.get(i)[0];
                    long lower = diff.get(i - 1)[0];
                    if (upper > lower) {
                        diff.add(i - 1, diff.get(i));
                        diff.remove(i + 1);
                    }
                }
            }
            Arrays.indexNextCard = (int) diff.get(0)[1];
            System.out.println(" FFF " + diff.get(0)[0] + " " + diff.get(0)[1]);
        }
        tempTimeFinder = 1;
    }

    public static long setTimeFromTimeStamp(int timeStep) {
        long timeToAdd = 0;
        if (timeStep == 1) {
            // two minutes
            timeToAdd = 120;
        }
        if (timeStep == 2) {
            // four minutes
            timeToAdd = 240;
        }
        if (timeStep == 3) {
            // ten minutes
            timeToAdd = 600;
        }
        if (timeStep == 4) {
            // twenty minutes
            timeToAdd = 1200;
        }

        if (timeStep == 5) {
            // one hour
            timeToAdd = 3600;
        }

        if (timeStep == 6) {
            // five hours
            timeToAdd = 3600;
        }
        if (timeStep == 7) {
            // two days
            timeToAdd = 172800;
        }

        if (timeStep == 8) {
            // four days
            timeToAdd = 34560;
        }
        if (timeStep == 9) {
            // eight days
            timeToAdd = 69120;
        }
        if (timeStep == 9) {
            // fourteen days
            timeToAdd = 1209600;
        }
        if (timeStep == 10) {
            // one month
            timeToAdd = 2592000;
        }
        if (timeStep == 11) {
            // one and a half month
            timeToAdd = 3801600;
        }
        if (timeStep == stepLimit) {
            // two months
            timeToAdd = timeLimit;
        }
        return timeToAdd;
    }


    public static void setRepeatTimeAfterAnswer() {
    }

    public static void setRepeatTimeAfterAnswerIsRight() {
        if (AnswerLogic.useTimeToRepeat) {
            SimpleDateFormat sdf = new SimpleDateFormat(TimeComparison.dateFormat);
            String date = sdf.format(new Date());
            long timeNow = Long.parseLong(date);
            long timeToAdd;
            if (AnswerLogic.questionIsReversedTemp) {
                int step = Integer.parseInt(Arrays.loadedCardsAsArray[Arrays.indexShownCard][Arrays.answerTimeStepIndexMarker]);
                if (step < 1) {
                    step = 1;
                }
                long secondsToConvertIntoDate = TimeComparison.setTimeFromTimeStamp(step);
                timeToAdd = TimeComparison.convertSecondsToDate(secondsToConvertIntoDate);
                System.err.println(" secondsToConvertIntoDate " + secondsToConvertIntoDate + " timeToAdd " + timeToAdd + " timeNow " + timeNow + " timeNow + timeToAdd " + (timeNow + timeToAdd));
                Arrays.loadedCardsAsArray[Arrays.indexShownCard][Arrays.answerRepeatTimeIndexMarker] = "" + (timeNow + timeToAdd);
                step++;
                if (step > stepLimit) {
                    step = stepLimit;
                }
                Arrays.loadedCardsAsArray[Arrays.indexShownCard][Arrays.answerTimeStepIndexMarker] = "" + step;
            } else {
                int step = Integer.parseInt(Arrays.loadedCardsAsArray[Arrays.indexShownCard][Arrays.questionTimeStepIndexMarker]);
                if (step < 1) {
                    step = 1;
                }
                long secondsToConvertIntoDate = TimeComparison.setTimeFromTimeStamp(step);
                timeToAdd = TimeComparison.convertSecondsToDate(secondsToConvertIntoDate);
                System.err.println(" secondsToConvertIntoDate " + secondsToConvertIntoDate + " timeToAdd " + timeToAdd + " timeNow " + timeNow + " timeNow + timeToAdd " + timeNow + timeToAdd);
                Arrays.loadedCardsAsArray[Arrays.indexShownCard][Arrays.questionRepeatTimeIndexMarker] = "" + (timeNow + timeToAdd);
                step++;
                if (step > stepLimit) {
                    step = stepLimit;
                }
                Arrays.loadedCardsAsArray[Arrays.indexShownCard][Arrays.questionTimeStepIndexMarker] = "" + step;
            }
        }
    }

    public static void setRepeatTimeAfterAnswerIsWrong() {
        // Wrong card will be repeated after half an hour.
        if (AnswerLogic.useTimeToRepeat) {
            @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat(TimeComparison.dateFormat);
            String date = sdf.format(new Date());
            long timeNow = Long.parseLong(date);
            long timeToAdd;
            int step = 4;
            long secondsToConvertIntoDate = TimeComparison.setTimeFromTimeStamp(step);
            timeToAdd = TimeComparison.convertSecondsToDate(secondsToConvertIntoDate);
            Arrays.loadedCardsAsArray[Arrays.indexShownCard][Arrays.questionRepeatTimeIndexMarker] = "" + (timeNow + timeToAdd);
            Arrays.loadedCardsAsArray[Arrays.indexShownCard][Arrays.questionTimeStepIndexMarker] = "" + step;
        }
    }
}
