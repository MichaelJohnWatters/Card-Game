package Test;


import java.util.Scanner;

public class CardTestSpec {


    public static void main(String[]args){

        Scanner scanner = new Scanner(System.in);

        boolean playing = true;
        int round = 0;
        boolean won = false;

        while(playing && (!won)){


            boolean inRound = true;

            while (inRound){
                System.out.println("Started round:  " + round);

                if(round > 4){
                    won = true;
                    playing = false;
                }

                String input = "";

                while(!input.equals( "a")){
                    //System.out.println("while(!input.equals(a)){");
                    System.out.println("input a:");
                    input = scanner.nextLine();

                }

                if(round == 5){
                    System.out.println("round Winn");
                } else {
                    won  = false;
                    playing = false;
                    System.out.println("round Fail");
                }

                //end round
                System.out.println("goinng to nnext round..");
                round++;
                inRound = false;

            }

        }
        System.out.println("Game over result: " + won);
    }
}
