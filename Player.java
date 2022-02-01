public class Player{
    int points = 0;
    int foulCount = 0;
    int pocketLessCount = 0;
    int number;
    Player(int number){
        this.number = number;
    }
    public int getPoints() {
        return points;
    }
    public void setPoints(int points) {
        this.points = points;
    }
    public int getFoulCount() {
        return foulCount;
    }
    public void setFoulCount(int foulCount) {
        this.foulCount = foulCount;
    }
    public int getPocketLessCount() {
        return pocketLessCount;
    }
    public void setPocketLessCount(int pocketLessCount) {
        this.pocketLessCount = pocketLessCount;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public void printPlayer(){
        System.out.println("Player "+number+":");
        System.out.println(" Points obtained = "+points);
    }
}
