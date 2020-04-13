import java.lang.Math;
import java.util.Scanner;
import java.util.Arrays;
public class Quest extends Game {
    private void prompt(String s) {
        System.out.println(Color.CYAN+s+Color.RESET);
    }

    //private int bsize = 8;
    public Market market = new Market();
    public Team team;
    public Team monster;
    //private int[] levels;
    private int hero_num;
    private String s2 = "Please enter number of heroes you want from 1-3";


    public void intro1(){
        //System.out.println("Welcome to Quest.");
        prompt( "Now Your Quest Begins..." );
        System.out.println(Color.RED + "In the map, H is hero position, m is the market\n" +
                "Use w/a/s/d to explore the map\n" +
                "Press i to view your hero, press q to quit" + Color.RESET);
        System.out.println(" ");
    }
    public void intro2() {
        IO.prompt( "Quest of Legends Begins..." );
        System.out.println(Color.RED + "In the map, H is hero position, \" is the BUSH cell, · is KOULOU cell, ▲ is CAVE cell, █ is inaccessible\n" +
                "Use w/a/s/d to explore the map, use t to TELEPORT to another lane\n" +
                "Press i to view your hero, press q to quit" + Color.RESET);
        System.out.println(" ");
    }

    public static void selectHero(Team team, Scanner in) {
        String SELECT_HERO = "Please enter number 1 to 12 to choose your hero";
        IO.prompt("You can choose heroes from the list below");
        HeroSet.list();
        int i = 0;
        do {
            int hero_no = IO.promptInt(in, SELECT_HERO, 1, 12);
            team.roles[i] = HeroSet.hero[hero_no - 1];
            team.levels[i] = team.roles[i].Level();
            i++;
        } while (i < team.roles.length);
        Arrays.sort(team.levels);
    }

    /**
     * print info of team hero and team monster
     */

    private void info(Team hero, Team monster){
        Hero.header();
        for(int n = 0 ;n < hero.roles.length; n++){
            hero.roles[n].info();
        }
        if (monster != null) {
            Monsters.header();
            for(int j = 0; j < monster.roles.length; j++) {
                monster.roles[j].info();}
        }
    }

    public void move(Board b, char ch, Team team) {
        Position p = move(b, ch,team.x,team.y);
        team.x = p.x;
        team.y = p.y;
    }

    private Position move (Board board, char ch, int x, int y){
        Position pos = new Position(x,y);
        if (ch == 'w'){
            if(board.checkMove(x-1, y))
                pos.x--;
        }
        if (ch == 'a') {
            if(board.checkMove(x, y-1))
                pos.y--;
        }
        if (ch == 's') {
            if(board.checkMove(x+1, y))
                pos.x++;
        }
        if (ch == 'd') {
            if(board.checkMove(x, y+1))
                pos.y++;
        }
        if (ch == 'b') {
            pos.x = board.getHeight()-1;
        }
        if (ch == 'i'){
            this.info(team, monster);
        }
        return pos;
    }
    private void createMonster(int n, int maxl) {
        monster = new Team(n);
        int r;
        for (int i = 0; i < n; i++) {
            do{
                r =(int) (Math.random()* MonsterSet.n);
            }while (MonsterSet.monsters[r].Level()!=maxl);
            Monsters m = MonsterSet.monsters[r];
            monster.roles[i] = new Monsters(m.name,m.level,m.damage,m.Defence(),m.Dodge());
        }

    }

    public void playQ1() {
        Scanner in = new Scanner(System.in);

        hero_num = IO.promptInt(in, s2, 1, 3);
        team = new Team(hero_num);
        selectHero(team, in);
        this.intro1();

        board = new Board();
        board.Init(1);
        board.render();

        //game play
        char ch;
        Tile t = TileSet.DEFAULT;
        while ((ch = in.next().charAt(0)) != 'q' ){
            board.tiles[team.x][team.y] = t;
            //update heroes position
            move(board, ch, team);

            t = board.tiles[team.x][team.y]; //now player at an accessible cell
            board.tiles[team.x][team.y] = TileSet.HERO; // render board after move
            board.render();

            if(t == TileSet.MARKET) {
                market.onMarket(in, team.roles);
                //board.render();
            }
            if(t == TileSet.DEFAULT) {
                if(Math.random() < 0.8) {
                    prompt("Watch out! You encounter monsters! Heroes, Let's fight!");
                    createMonster(hero_num, team.levels[hero_num-1]);
                    Fight f = new Fight(board,team.roles, monster.roles,in);
                    if (f.result == 0) break;
                    //fight(team.roles, monster.roles,in);
                }
            }
            board.render();
        }
    }

    public void playQ2() {
        Scanner in = new Scanner(System.in);
        team = new Team(3);
        monster = new Team(3);
        selectHero(team, in);

        intro2();
        board = new Board();
        board.Init(2);
        board.render();
        char ch;
        Tile t = TileSet.DEFAULT;
        while ((ch = in.next().charAt(0)) != 'q' ){

        }



    }

}
