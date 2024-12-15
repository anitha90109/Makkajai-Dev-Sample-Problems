package com.springboot.controller;

import java.util.ArrayList;
import java.util.List;

class Talk {
    String title;
    int duration; // Duration in minutes

    public Talk(String title, int duration) {
        this.title = title;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public int getDuration() {
        return duration;
    }
}

class Track {
    List<Talk> morningTalks = new ArrayList<>();
    List<Talk> afternoonTalks = new ArrayList<>();
    
    private static final int MORNING_START = 9 * 60; // 9:00 AM
    private static final int LUNCH_START = 12 * 60; // 12:00 PM
    private static final int AFTERNOON_START = 13 * 60; // 1:00 PM
    private static final int NETWORKING_START = 16 * 60; // 4:00 PM

    public void addMorningTalk(Talk talk) {
        morningTalks.add(talk);
    }

    public void addAfternoonTalk(Talk talk) {
        afternoonTalks.add(talk);
    }

    public void printSchedule(int trackNumber) {
        System.out.println("Track " + trackNumber + ":");
        printSession(morningTalks, MORNING_START, LUNCH_START);
        System.out.println("12:00PM Lunch");
        printSession(afternoonTalks, AFTERNOON_START, NETWORKING_START);
        System.out.println("05:00PM Networking Event\n");
    }

    private void printSession(List<Talk> talks, int startTime, int endTime) {
        int currentTime = startTime;

        for (Talk talk : talks) {
            if (currentTime + talk.getDuration() > endTime) break; // No more time left
            System.out.printf("%02d:%02d %s %dmin%n", currentTime / 60, currentTime % 60, talk.getTitle(), talk.getDuration());
            currentTime += talk.getDuration();
        }
        
        // Handle lightning talks
        for (Talk talk : talks) {
            if (talk.getDuration() == 5 && currentTime < endTime) { // Lightning talk
                System.out.printf("%02d:%02d %s lightning%n", currentTime / 60, currentTime % 60, talk.getTitle());
                currentTime += talk.getDuration();
            }
        }
    }
}

public class ConferenceScheduler {

    public static void main(String[] args) {
        List<Talk> talks = new ArrayList<>();
        
        // Sample input talks
        talks.add(new Talk("Writing Fast Tests Against Enterprise Rails", 60));
        talks.add(new Talk("Overdoing it in Python", 45));
        talks.add(new Talk("Lua for the Masses", 30));
        talks.add(new Talk("Ruby Errors from Mismatched Gem Versions", 45));
        talks.add(new Talk("Common Ruby Errors", 45));
        talks.add(new Talk("Rails for Python Developers", 5)); // lightning
        talks.add(new Talk("Communicating Over Distance", 60));
        talks.add(new Talk("Accounting-Driven Development", 45));
        talks.add(new Talk("Woah", 30));
        talks.add(new Talk("Sit Down and Write", 30));
        talks.add(new Talk("Pair Programming vs Noise", 45));
        talks.add(new Talk("Rails Magic", 60));
        talks.add(new Talk("Ruby on Rails: Why We Should Move On", 60));
        talks.add(new Talk("Clojure Ate Scala (on my project)", 45));
        talks.add(new Talk("Programming in the Boondocks of Seattle", 30));
        talks.add(new Talk("Ruby vs. Clojure for Back-End Development", 30));
        talks.add(new Talk("Ruby on Rails Legacy App Maintenance", 60));
        talks.add(new Talk("A World Without HackerNews", 30));
        talks.add(new Talk("User Interface CSS in Rails Apps", 30));

        // Create tracks and distribute the talks
        Track track1 = new Track();
        Track track2 = new Track();

        distributeTalks(talks, track1, track2);

        // Print the schedules
        track1.printSchedule(1);
        track2.printSchedule(2);
    }

    private static void distributeTalks(List<Talk> availableTalks, Track track1, Track track2) {
         fillSession(availableTalks, track1.morningTalks, 180); // Morning session limit
         fillSession(availableTalks, track1.afternoonTalks, Integer.MAX_VALUE); // Afternoon session no limit
         fillSession(availableTalks, track2.morningTalks, 180); // Morning session limit
         fillSession(availableTalks, track2.afternoonTalks, Integer.MAX_VALUE); // Afternoon session no limit
     }

     private static void fillSession(List<Talk> availableTalks, List<Talk> sessionTalks, int timeLimit) {
         int totalDuration = 0;

         for (int i = availableTalks.size() - 1; i >=0; i--) { 
        	 System.out.println(i);
               if (totalDuration + availableTalks.get(i).getDuration() <= timeLimit) { 
                   sessionTalks.add(availableTalks.remove(i)); 
                   System.out.println("sessionTalks " + availableTalks.get(i-1));
                   totalDuration += sessionTalks.get(sessionTalks.size() -1).getDuration(); 
               } 
         } 
     }
}
