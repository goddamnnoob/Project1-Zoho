import java.util.*;

public class CleanSlate{
     int playerCount = 2;
     boolean redPresent = true;
     ArrayList<Coin> coins;
     Player player1;
     Player player2;
     final String black = "Black";
     final String red = "Red";
     final String striker = "Striker";

    CleanSlate(){}

    void startGame(){
        init();
        gameLogic();
        stats();
    }
     void init(){
        coins  = new ArrayList<Coin>();
        player1 = new Player(1);
        player2 = new Player(2);
        for(int i=0;i<9;i++){
            Coin c = new Coin(black);
            coins.add(c);
        }
        coins.add(new Coin(red));
    }

     void gameLogic(){
        Scanner s = new Scanner(System.in);
        boolean turn = true;
        while(!coins.isEmpty()){
            /*
                Player 1: Choose an outcome from the list below
                1. Strike
                2. Multistrike
                3. Red strike
                4. Striker strike
                5. Defunct coin
                6. None
            */
            if(checkWin()){
                s.close();
                return;
            }
            Player player;
            if(turn){
                player = player1;
            }
            else{
                player = player2;
            }
            checkFouls(player);
            System.out.println("Player " + player.getNumber()+": "+"Choose an outcome from the list below" );
            System.out.println("1. Strike");
            System.out.println("2. Multistrike");
            System.out.println("3. Red strike");
            System.out.println("4. Striker strike");
            System.out.println("5. Defunct coin");
            System.out.println("6. None");
            int choice = s.nextInt();
            switch(choice){
                case 1:
                    strike(player);
                    break;
                case 2:
                    multipleStrike(player);
                    break;
                case 3:
                    if(redPresent){
                        redStrike(player);
                        redPresent = false;
                    }
                    else{
                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
                        System.out.println("Invalid Input");
                        System.out.println("Red coin is not present");
                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
                        turn = !turn;
                    }
                    break;
                case 4:
                    strikerStrike(player);
                    break;
                case 5:
                    System.out.println("Enter Colour 1.Black 2.Red");
                    int colour = s.nextInt();
                    if(colour == 1){
                        defunctCoin(black, player);
                    }
                    else{
                        if(redPresent){
                            defunctCoin(red, player);
                            redPresent = false;
                        }
                        else{
                            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
                            System.out.println("Invalid Input");
                            System.out.println("Red coin is not present");
                            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
                            turn = !turn;
                        }
                    }
                    break;
                case 6:
                    none(player);
                    break;
                default:
                    System.out.println("Invalid Input");
                    turn = !turn;
                    break; 
            }
            turn = !turn;
            stats();
            printCoins();
            checkFouls(player);
        }
        s.close();
        checkDraw();
    }
     void strike(Player p){
        p.setPoints(p.getPoints()+1);
        p.setPocketLessCount(0);
        for(int i=0;i<coins.size();i++){
            if(coins.get(i).colour.equals(black)){
                coins.remove(i);
                return;
            }
        }
    }

     void multipleStrike(Player p){
        p.setPoints(p.getPoints()+2);
        p.setPocketLessCount(0);
    }
    
     void redStrike(Player p){
        p.setPoints(p.getPoints()+3);
        p.setPocketLessCount(0);
        for(int i=0;i<coins.size();i++){
            if(coins.get(i).colour.equals(red)){
                coins.remove(i);
                return;
            }
        }
    }

     void strikerStrike(Player p){
        p.setPoints(p.getPoints()-1);
        p.setPocketLessCount(p.getPocketLessCount()+1);
        p.setFoulCount(p.getFoulCount()+1);
    }

     void defunctCoin(String colour,Player p){
        p.setPoints(p.getPoints()-2);
        p.setPocketLessCount(p.getFoulCount()+1);
        p.setFoulCount(p.getFoulCount()+1);
        for(int i=0;i<coins.size();i++){
            if(coins.get(i).colour.equals(colour)){
                coins.remove(i);
                return;
            }
        }
    }

     void none(Player p){
        p.setPocketLessCount(p.getPocketLessCount()+1);
    }

     void checkFouls(Player p){
        if(p.getPocketLessCount() >= 3){
            p.setPoints(p.getPoints()-1);
            p.setPocketLessCount(0);
            p.setFoulCount(p.getFoulCount()+1);
        }
        if(p.getFoulCount() >= 3){
            p.setPoints(p.getPoints()-1);
            p.setFoulCount(0);
        }
        
    }

     boolean checkWin(){
        if(player1.getPoints()>=5){
            if(player1.getPoints() - player2.getPoints() >=3){
                System.out.println("The winner is Player 1");
                System.out.println("Player 1 has "+player1.getPoints()+" points which is more than 4 and difference is "+(player1.getPoints()-player2.getPoints()));
                return true;
            }
        }
        else if(player2.getPoints() >= 5){
            if(player2.getPoints() - player1.getPoints() >=3){
                
                System.out.println("The winner is Player 2");
                System.out.println("Player 2 has "+player2.getPoints()+" points which is more than 4 and difference is "+(player2.getPoints()-player1.getPoints()));
                return true;
            }
        }
        return false;
    }

     void checkDraw(){
        if(player1.getPoints() < 5 && player2.getPoints() < 5&& Math.abs(player2.getPoints() - player1.getPoints()) >=3){
            System.out.println("Match ends in a DRAW");
        }
    }
     void stats(){
        System.out.println("-------------------------------------------");
        player1.printPlayer();
        System.out.println("-------------------------------------------");
        player2.printPlayer();
        System.out.println("-------------------------------------------");
    }
    void printCoins(){
        System.out.println("-------------------------------------------");
        System.out.println("Remaining Coins");
        for(int i=0;i<coins.size();i++){
            System.out.println(coins.get(i).getColour());
        }
        System.out.println("-------------------------------------------");
    }
}