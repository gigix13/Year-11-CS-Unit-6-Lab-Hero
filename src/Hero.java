import javax.swing.*;
import java.util.*;
public class Hero {
    private String name;
    private int hitPoints;

    public Hero(String n) {
        name=n;
       hitPoints = 100;
    }

    public String getName() {
        return name;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public String toString(){
        return ("Hero{name='" + name + "', hitPoints=" + hitPoints + "}");
    }

    public void attack(Hero opponent) {
        Random rand = new Random();
        int r = rand.nextInt(0,99);
        if (r>=50){
            hitPoints -= 10;
        }else{
            opponent.hitPoints-=10;
        }

    }

    public void senzuBean() {
        this.hitPoints = 100;
    }

    private void fightUntilTheDeathHelper(Hero opponent) {
        while (hitPoints > 0 && opponent.hitPoints > 0) {
            attack(opponent);
        }
    }


    public String fightUntilTheDeath(Hero opponent){
        fightUntilTheDeathHelper(opponent);
        return (name + ": " + hitPoints + "         " + opponent.getName() + ": " + opponent.getHitPoints());
    }

    public int[] nFightsToTheDeathHelper(Hero opponent, int n){
        int[] score = new int[2];
        while (n >0){
            fightUntilTheDeath(opponent);
            if (hitPoints == 0 && opponent.getHitPoints()==0){
                if (hitPoints==0) {
                    score[1]++;
                }
                else
                    score[0]++;
                senzuBean();
                opponent.senzuBean();
            }
            n--;
        }
        return score;
    }

    public String nFightsToTheDeath(Hero opponent, int n){
        int[] score = nFightsToTheDeathHelper(opponent, n);
        String message = (name + ": " + score[0] + " wins /n" + opponent.getName() + ": " + score[1] + " wins /n");
        if (score[0]>score[1]){
            return (message + name + " wins!");
        }
        else if (score[0] == score[1]) {
            return (message + "OMG! It was actually a draw!");
        }
        else
            return (message + opponent.getName() + " wins!");
    }

    public void dramaticFightToTheDeath(Hero opponent) throws InterruptedException {
        while (opponent.getHitPoints()>0 &&hitPoints>0){
            attack(opponent);
            System.out.println(name + ": " + hitPoints + "     " + opponent.getName() + ": " + opponent.getHitPoints());
            Thread.sleep(1000);
        }
    }

}