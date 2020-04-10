public class Team {
    // player can have 1 to 3 heros
    public Role[] roles;
    public int[] levels;
    // the position of player
    public int x = 0;
    public int y = 0;
    //private int n;
    public Team(int n) {
        roles =  new Role[n];
        levels = new int[n];
    }
    public void info(){
        Hero.header();
        for(int n = 0 ;n < roles.length; n++){
            roles[n].info();
        }
    }



}
