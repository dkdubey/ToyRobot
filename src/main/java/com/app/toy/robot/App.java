package com.app.toy.robot;

import java.util.Scanner;

public class App {

    public static void main( String[] args ) {
        String command;
        Table table = new Table();
        ToyRobot toyRobot = table.createToyRobot();

        Scanner in = new Scanner(System.in);

        System.out.println("'PLACE X,Y,NORTH', 'MOVE', 'LEFT', 'RIGHT', 'REPORT' OR 'EXIT'");

        while(true){
            command = in.nextLine();
            if(command.equalsIgnoreCase("exit")){
                break;
            }

            try {
                toyRobot.runCommand(command);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

        }

    }
}
