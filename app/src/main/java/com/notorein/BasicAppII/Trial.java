package com.notorein.BasicAppII;


import static com.notorein.BasicAppII.Parameter.Parameter.isTrialVersion;

public class Trial {
    public static int trialCount = 0;
    public static int freeTrials = 80;

    public static String countCardsLeft(int trialCount) {
        if (isTrialVersion) {
            int count = (freeTrials - trialCount);
            if (count < 0) {
                count = 0;
            }
            return "You have " + count + " of " + freeTrials + " free cards left to learn!";
        } else {
            return "";
        }
    }

    public static String trialText = "Unfortunately your reached the number of free trial cards! " +
            "Do you actually know how long I was working on that app? " +
            "And more over. One has to make a living you know. So if you like the app you can purchase it now. " +
            "Yes, I was thinking about to release it for free. May be donation based or something. " +
            "I did that in the past. It did not work. So now I am sitting here in Fabrika in Tbilisi Georgia while " +
            "writing this text. I am freezing! It is the second of May 2022, and yesterday I had to ask my neighbours for " +
            "coffee since I had no money to buy some. You don't believe me? Yes, you are right. I am a liar. It was the day before yesterday. I don't even have " +
            "an Health Insurance. But who actually needs one as long as you live an healthy lifestyle. By the way. Where are my cigarettes? " +
            "And summer is about to come. That will be great. " +
            "If I manage to earn some money from that app I will include sound. But first of all I need a new smartphone. " +
            "My old one is broken. It was my fault. I found a sim card on the street and was trying to fiddle it in. No I have the result. " +
            "It is not completely broken. The internet still works. But I can't make phone calls anymore. " +
            "The problem is that it runs on Android 4.4.2. I bought it in 2018. It is second hand, and was already out of date at that time. " +
            "Most apps don't work on it since the latest Android version is 11. Or 13? " +
            "Anyway. I can't use the apps people here usually use in order to call taxis. And this city is not made for " +
            "bicycles. So when I need a taxi the drivers charge me a fortune. That would not happen if I could just use " +
            "the apps. What I need more? A trouser and a pair of shoes. My socks also fall apart. My landlord will ask " +
            "me for the rent I think. Not today. But within a week or so. You can uninstall the app and reinstall the free version. " +
            "But then this text will appear over and over again. It will bother you. It is no fun to learn if " +
            "someone is bothering you all the time. You got the idea.";


}
