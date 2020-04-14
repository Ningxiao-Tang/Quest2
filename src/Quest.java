import java.lang.Math;
import java.util.Scanner;
import java.util.Arrays;
public class Quest extends Game {
    private void prompt(String s) {
        System.out.println(Color.CYAN+s+Color.RESET);
    }

    public Market market = new Market();
    public Team team;
    public Team monster;
    public int HERO_LIMIT = 3;
    public int width = 8;
    public int height = 8;


    public void intro1(){
        //System.out.println("Welcome to Quest.");
        prompt( "Now Your Quest Begins..." );
        System.out.println(Color.RED + "In the map, @ is hero position, m is the market\n" +
                "Use w/a/s/d to explore the map\n" +
                "Press i to view your hero, press q to quit" + Color.RESET);
        System.out.println(" ");
    }
    public void intro2() {
        IO.prompt( "Quest of Legends Begins..." );
        System.out.println(Color.RED + "In the map, @,#,$ is heroes' position, \" is the BUSH cell, · is KOULOU cell, ▲ is CAVE cell, █ is inaccessible\n" +
                "Use w/a/s/d to explore the map, use t to TELEPORT to another lane\n" +
                "Press i to view your hero, press q to quit" + Color.RESET);
        System.out.println(" ");
    }
    /*
    initialize heroes from user's choice, sort selected heroes' level;
     */
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
        team.setPosition(p.x,p.y);
    }

    public Position move (Board board, char ch, int x, int y){
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
    public void move(Board b, char ch, Hero h) {
        if (ch == 'w'){
            if (willPassMonster(b,h.x,h.y))
                IO.prompt("Cannot pass monster without fight");
        }
        if (ch == 't'){
            teleport(b,h);
        }
        else {
            Position p = move(b, ch,h.x,h.y);
            h.setPosition(p.x,p.y);
        }

    }

    public void teleport(Board b, Hero hero) {
        Scanner in = new Scanner(System.in);
        int x = IO.promptInt(in, "Please enter the row you want to teleport to", 1,8);
        int y = IO.promptInt(in, "Please enter the column you want to teleport to", 1,8);
        x = x-1;
        y = y-1;
        if (Math.abs(x - hero.x)<2 || b.tiles[x][1].equals(TileSet.WALL) || willPassMonster(b,x,y)) {
            System.out.println("Illegal index, please try again");
            //teleport(b,hero);
        }
        else {
            hero.x = x;
            hero.y = y;
        }
    }

    private void randomMonster(Team m, int n, int maxl) {
        int r;
        for (int i = 0; i < n; i++) {
            do{
                r =(int) (Math.random()* MonsterSet.n);
            }while (MonsterSet.monsters[r].Level()!=maxl);
            Monsters monster = MonsterSet.monsters[r];
            m.roles[i] = new Monsters(monster.name,monster.level,monster.damage,monster.Defence(),monster.Dodge());
        }

    }

    public void playQ1() {
        Scanner in = new Scanner(System.in);
        String s2 = "Please enter number of heroes you want from 1-3";
        int hero_num = IO.promptInt(in, s2, 1, 3);
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
            board.tiles[team.x][team.y] = TileSet.HERO[0];

            if(t == TileSet.MARKET) {
                market.onMarket(in, team.roles);
            }
            if(t == TileSet.DEFAULT) {
                if(Math.random() < 0.8) {
                    prompt("Watch out! You encounter monsters! Heroes, Let's fight!");
                    monster = new Team(hero_num);
                    randomMonster(monster,hero_num, team.levels[hero_num -1]);
                    Fight f = new Fight(board,team.roles, monster.roles,in);
                    if (f.result == 0) break;
                }
            }
            // render board after move
            board.render();
        }
    }

    public void playQ2() {
        Scanner in = new Scanner(System.in);
        //roles initialization
        int monster_num = HERO_LIMIT;
        team = new Team(HERO_LIMIT);
        team.addDescription("Hero");
        monster = new Team(monster_num);
        monster.addDescription("Monster");
        intro2();
        selectHero(team, in);
        randomMonster(monster,monster_num,team.levels[2]);
        for (int i = 0,j=0; i < HERO_LIMIT && j < height; i++,j+=3){
            team.roles[i].setPosition(height -1,j);
            monster.roles[i].setPosition(0,j);
        }
        // board initialization

        board = new Board();
        board.Init(2);
        board.render();

        // start round
        int round = 1;
        Tile[]  t = {TileSet.DEFAULT, TileSet.DEFAULT, TileSet.DEFAULT};
        while(!board.reachNexus(team) && !board.reachNexus(monster)) {
            if(round%8==0){
                //respawn monster
            }
            for (int i = 0; i < monster_num; i++) {
                Monsters m = (Monsters)monster.get(i);
                Hero[] temp = radiusMonster(m,team);
                if(temp[0]!= null)
                    m.attack(temp[0]);
                else{
                    forward(board,m);
                    IO.prompt("Monster "+(i+1)+" "+m.Name()+" moves forward");
                }

            }
            board.render();

            if(board.reachNexus(monster)) //monster win
                break;

            for (int i = 0; i < HERO_LIMIT; i++) {
                Hero h = (Hero)team.get(i);
                Monsters[] temp = radiusHero(h, monster);
                if(temp[0]!= null){
                    //can choose move or attack monster
                    int d = IO.promptInt(in,"Hero "+(i+1)+" "+ TileSet.HERO[i]+" encounter a monster. Please enter 1 to move, enter 2 to fight",1,2);
                    if (d == 2) {
                        //Round round = new Round(Hero hero, Monsters monster, Scanner sc);
                    }
                    else {
                        moveHero(board,h,i,t,in);
                    }


                }
                else {
                    moveHero(board,h,i,t,in);
                }
                board.render();
            }
            if(board.reachNexus(team))
                break;
            //end of a round
            round++;

        }
    }

    public void moveHero(Board board, Hero h, int i, Tile[] t, Scanner in){
        IO.prompt("For hero "+(i+1)+ " "+TileSet.HERO[i]+", please enter w/a/s/d to move, enter b to go back to Nexus, enter t to teleport to another lane");
        char ch = in.next().charAt(0);
        board.tiles[h.x][h.y] = t[i];
        //update hero(i) 's position
        move(board,ch,h);
        t[i] = board.tiles[h.x][h.y]; //now player at a new position, store its tile
        board.tiles[h.x][h.y] = TileSet.HERO[i];
    }




    private Hero[] radiusMonster(Monsters m, Team team) {
        //return an array of heroes that monster m can attack
        Hero[] temp = new Hero[team.roles.length];
        int k = 0;
        for(int i = 0; i< team.roles.length;i++){
            //hero is below m
            if(Math.abs(m.y-team.roles[i].y)<=1 && team.roles[i].x - m.x == 1)
                temp[k++] = (Hero)team.roles[i];
        }
        return temp;
    }

    private Monsters[] radiusHero(Hero h, Team team) {
        Monsters[] temp = new Monsters[team.roles.length];
        int k = 0;
        for(int i = 0; i< team.roles.length;i++){
            if(h.x-team.roles[i].x==1)
                temp[k++] = (Monsters)team.roles[i];
        }
        return temp;
    }

    private void forward(Board b, Monsters m){
        if(!willPassHero(b,m.x,m.y)){
            b.tiles[m.x][m.y] = TileSet.DEFAULT;
            b.tiles[++m.x][m.y] = TileSet.MONSTER;
        }
    }
    private boolean willPassHero(Board b, int x, int y) {
        // hero.x == monster.x
        return ((y>0 && b.existHero(x,y-1))|| (y<width-1 && b.existHero(x,y+1)));
    }
    private boolean willPassMonster(Board b, int x, int y) {
        for (int row = x; row < height; row++){
            if ((y > 0)&&b.existMonster(row,y-1) || (y<width)&&b.existMonster(row,y+1))
                return true;
        }
        return false;
    }



}
