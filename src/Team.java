import java.util.ArrayList;

public class Team {
    // player can have 1 to 3 heros
    private String teamType;
    public Role[] roles;
    public ArrayList<Role> role;
    public int[] levels;
    // the position of player
    public int x = 0;
    public int y = 0;
    //private int n;
    public Team(int n) {
        roles =  new Role[n];
        levels = new int[n];
    }
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void info(){
        Hero.header();
        for(int n = 0 ;n < roles.length; n++){
            roles[n].info();
        }
    }
    public void addDescription(String s){
        teamType = s;
    }
    public String getTeamType(){
        return teamType;
    }
    public Role get(int n){
        return roles[n];
    }



}
