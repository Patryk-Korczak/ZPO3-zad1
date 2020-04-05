package com.company;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;



public class TranslationExam {
    private double currentPoints = 0.0;
    private final String fileName = "PolEngTest.json";
    private List <Integer> usedQuestions = new ArrayList<>();
    String jsonString = new String(Files.readAllBytes(Paths.get(fileName)));
    Gson g = new GsonBuilder().setPrettyPrinting().create();
    TestTask[] tasks = g.fromJson(jsonString, TestTask[].class);
    public List<TestTask> answersToSave = new ArrayList<>();

    public TranslationExam() throws IOException {
    }


    public double getCurrentPoints() {
        return currentPoints;
    }

    private void saveAnswersToJson(String fileName) throws IOException {
        String jsonString = g.toJson(answersToSave);
        PrintWriter writer = new PrintWriter(fileName, StandardCharsets.UTF_8);
        writer.print(jsonString);
        writer.close();
    }

    private int generateQuestionNumber() {
        Random r = new Random();
        int generatedNumber;

        generatedNumber = r.nextInt(10) ;
        while(usedQuestions.contains(generatedNumber)){
            generatedNumber = r.nextInt(10) ;
        }

        usedQuestions.add(generatedNumber);
        return generatedNumber;
    }

    private void runQuestion() {
        int questionNumber = generateQuestionNumber();
        String question = tasks[questionNumber].getQuestion();
        List <String> answers = tasks[questionNumber].getAnswer();
        double score = 0.0;
        String userAnswer = JOptionPane.showInputDialog("Translate: " + question);

        for (String possibleAnswer:answers) {
            if (userAnswer.equals(possibleAnswer)) {
                score = 1.0;
                break;
            } else if (levDistance(userAnswer, possibleAnswer) == 1) {
                score = 0.5;
                break;
            }
            else score = 0.0;
        }
        TestTask answer = new TestTask();
        answer.setQuestion(question);
        answer.setAnswer(Collections.singletonList(userAnswer));
        answersToSave.add(answer);
        this.currentPoints = getCurrentPoints() + score;
    }

    public void runTest() throws IOException {
        String firstName = JOptionPane.showInputDialog("Podaj imie: ");
        String lastName = JOptionPane.showInputDialog("Podaj nazwisko: ");
        String fileName = firstName + "_" + lastName + ".json";
        long startTime = System.nanoTime();

        for(int i = 0; i < 5; i++) {
            runQuestion();
        }

        long finishTime = System.nanoTime();
        double testTime = finishTime - startTime;
        testTime/=1000000000;
        saveAnswersToJson(fileName);
        double maxPoints = 5.0;
        JOptionPane.showMessageDialog(null, "Twój wynik to: " + currentPoints + " z " +
                maxPoints + " możliwych. Czas testu to: " + String.format("%.2f", testTime) +" sekund."   );

    }

    public static double levDistance(String string1, String string2) {
        String s1 = string1.toLowerCase().trim();
        String s2 = string2.toLowerCase().trim();

        double[][] distanceTable = new double[s1.length()+1][s2.length()+1];

        for(int i = 0; i<= s1.length(); i++) {
            distanceTable[i][0] = i;
        }
        for(int j = 1; j<= s2.length(); j++) {
            distanceTable[0][j] = j;
        }

        for(int i = 1; i<=s1.length(); i++) {
            for(int j = 1; j<=s2.length(); j++) {
                double costOfSubstitution;
                if(s1.charAt(i -1) == s2.charAt(j -1)) costOfSubstitution = 0;
                else costOfSubstitution = 1;

                distanceTable[i][j] = Math.min(Math.min(distanceTable[i-1][j]+1,
                                                        distanceTable[i][j-1]+1),
                                                        distanceTable[i-1][j-1] + costOfSubstitution);
            }
        }

        return distanceTable[s1.length()][s2.length()];
    }


}
