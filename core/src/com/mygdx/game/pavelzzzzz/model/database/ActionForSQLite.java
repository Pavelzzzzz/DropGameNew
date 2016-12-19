package com.mygdx.game.pavelzzzzz.model.database;

import com.badlogic.gdx.utils.StringBuilder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Pavel on 18.12.16.
 */

public class ActionForSQLite {

    public static Connection conn;
    public static Statement statmt;
    public static ResultSet resSet;

    public static void connection() throws ClassNotFoundException, SQLException
    {
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:results.db");

        System.out.println("Database connected.");
    }

    public static void createDB() throws ClassNotFoundException, SQLException
    {
        statmt = conn.createStatement();
        statmt.execute("CREATE TABLE if not exists results (" +
                "'Score' INTEGER NOT NULL, 'CollectedDrops' INTEGER NOT NULL, " +
                "'CreationTime' INTEGER NOT NULL, 'DeclineSpeed' INTEGER NOT NULL);");

        System.out.println("The table is created or already exists.");
    }

    public static void noteAddDB(int score, int collectedDrops, int creationTime, int declineSpeed) throws SQLException
    {
        //statmt.execute("INSERT INTO results ('Score', 'Collected drops', 'Creation time', 'Decline speed') " +
        //"VALUES(4, 4, 4, 4);");
            statmt.execute("INSERT INTO results (Score, CollectedDrops, CreationTime, DeclineSpeed) " +
                    //"VALUES(4, 4, 4, 4);");
                "VALUES (" + score + ", " + collectedDrops + ", " + creationTime + ", " + declineSpeed + "); ");
        //statmt.execute("Insert into results ('Score') values (4)");

        System.out.println("Note added.");
    }

    public static String readTenBestNomeDB() throws ClassNotFoundException, SQLException, NullPointerException
    {
        resSet = statmt.executeQuery("SELECT * FROM results ORDER BY Score ASC LIMIT 10");

        StringBuilder stringOut = new StringBuilder();

        while(resSet.next())
        {
            int score = resSet.getInt("Score");
            int collectedDrops = resSet.getInt("CollectedDrops");
            int creationTime = resSet.getInt("CreationTime");
            int declineSpeed = resSet.getInt("DeclineSpeed");
            stringOut.append(String.format("%15d %15d %20d %20d\n", score, collectedDrops, creationTime, declineSpeed));
        }

        System.out.println("The table is derived");
        return stringOut.toString();
    }

    // --------Закрытие--------
    public static void CloseDB() throws ClassNotFoundException, SQLException
    {
        conn.close();
        statmt.close();

        System.out.println("Connection closed");
    }
}
