package com.mygdx.game.pavelzzzzz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.StringBuilder;


/**
 * Created by Pavel on 04.12.16.
 */

public class Score {

    private String scoreArray[][];
    private byte count;


    public Score(){
        scoreArray = new String [10][2];
        count = 0;
        if (Gdx.files.local("Score.txt").exists()){
            FileHandle fileHandle = Gdx.files.local("Score.txt");
            String str = fileHandle.readString();
            boolean first = true;
            int strStart = 0;
            for (int ind = 0; (ind < str.length()) && (count < 10); ind++){
                if ((str.charAt(ind) == ' ') && first) {
                    scoreArray[count][0] = str.substring(strStart, ind);
                    first = false;
                    strStart = ind;
                } else if (str.charAt(ind) == '\n'){
                    scoreArray[count][1] = str.substring(strStart, ind+1);
                    count++;
                    strStart = ind+1;
                    first = true;
                }
            }
        }
    }

    private void arrayShit(String[] el, int ind){
        if (ind < 9){
            arrayShit(scoreArray[ind], ind+1);
            scoreArray[ind] = el;
        }
    }

    public void setScore(String score[]){
        boolean doing = true;
        for (byte ind = 0; (ind < count) && doing; ind++){
            if  (Integer.valueOf(scoreArray[ind][0]) < Integer.valueOf(score[0])){
                arrayShit(scoreArray[ind], ind+1);
                scoreArray[ind] = score;
                doing = false;
                count++;
        }}
        if (doing && (count < 10)){
            scoreArray[count][0] = score[0];
            scoreArray[count][1] = score[1];
            count++;
        }
    }

    public String getString(){
        StringBuilder str = new StringBuilder();
        for (byte ind = 0; ind < count; ind++){
            str.append(scoreArray[ind][0]);
            str.append(scoreArray[ind][1]);
        }
        return str.toString();
    }

    public void writeToFile(){
        FileHandle file = Gdx.files.local("Score.txt");
        file.writeString(getString(), false);
    }
}


