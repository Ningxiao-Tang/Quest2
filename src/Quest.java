import java.lang.Math;
import java.util.ArrayList;
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

    /**
     * Quest1 game intro
     */

    public void intro1(){
        //System.out.println("Welcome to Quest.");
        prompt( "Now Your Quest Begins..." );
        System.out.println(Color.RED + "In the map, @ is hero position, m is the market\n" +
                "Use w/a/s/d to explore the map\n" +
                "Press i to view your hero, press q to quit" + Color.RESET);
        System.out.println(" ");
    }

    /**
     * Quest2 game intro
     */
    public void intro2() {
        IO.prompt( "Quest of Legends Begins..." );
        System.out.println(Color.RED + "In the map, @,#,$ are heroes' position, \" is the BUSH cell, · is KOULOU cell, ▲ is CAVE cell, █ is inaccessible\n" +
                "Use w/a/s/d to explore the map, use t to TELEPORT to another lane, and b to move your hero to market which is located in nexus\n" +
                "Press i to view your hero, press q to quit" + Color.RESET);
        System.out.println(" ");
    }
    /**
     * Initialize heroes from user's choice, sort selected heroes' level;
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
     * print info of team hero and team monster if monster is not null
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

    /**
     * move() interface for quest 1 controller to move all hero
     * @param b board
     * @param ch user's input
     * @param team hero
     */

    public void move(Board b, char ch, Team team) {
        Position p = move(b, ch,team.x,team.y);
        team.setPosition(p.x,p.y);
    }
    /**
     * move() interface for quest2 controller to move each hero
     * @param b
     * @param ch
     * @param h
     */
    public void move(Board b, char ch, Hero h) {
        if (ch == 'w'){
            if (willPassMonster(b,h.x,h.y)){
                IO.prompt("Cannot pass monster without fight");
                return;
            }

        }
        if (ch == 't'){
            teleport(b,h);
        }
        else {
            Position p = move(b, ch,h.x,h.y);
            h.setPosition(p.x,p.y);
        }
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

    /**
     *
     * @param m initialize a team of monster m,
     * @param n number of monster to create
     * @param maxl the level of all monsters is the maximum level of all hero
     */
    private void randomMonster(Team m, int n, int maxl) {
        int r;
        for (int i = 0; i < n; i++) {
            do{
                r =(int) (Math.random()* MonsterSet.n);
            }while (MonsterSet.monsters[r].Level()!=maxl);
            Monsters monster = MonsterSet.monsters[r];
            m.roles[i] = new Monsters(monster.name,monster.level,monster.damage,monster.Defence(),monster.Dodge());
            //m.role.add(m.roles[i]);
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

        selectHero(team, in);
        randomMonster(monster,monster_num,team.levels[2]);
        for (int i = 0,j=0; i < HERO_LIMIT && j < height; i++,j+=3){
            team.roles[i].setPosition(height -1,j);
            monster.roles[i].setPosition(0,j);
        }
        // board initialization
        intro2();
        board = new Board();
        board.Init(2);
        board.render();

        // start round
        int round = 1;
        boolean quit = false;
        while(!board.reachNexus(team) && !board.reachNexus(monster) && !quit) {
            if(round%8==0){
                //respawn monster
                //board.respawnMonster();
                //
                Team tempTeam = new Team(monster_num+3);
                Team newTeam = new Team(3);
                randomMonster(newTeam,3,team.levels[2]);
                for (int i = 0; i < monster_num;i++){
                    tempTeam.roles[i] = monster.roles[i];
                }
                for (int i = monster_num,j=0,k=0; i < monster_num+3;i++,j++,k+=3){
                    tempTeam.roles[i] = newTeam.roles[j];
                    tempTeam.roles[i].setPosition(0,k);
                }
                monster = tempTeam;
                monster.addDescription("Monster");
                monster_num+=3;
                System.out.println("new monster set length="+monster.roles.length+" new monster_num="+monster_num);
            }
            //monster's turn
            for (int i = 0; i < monster_num; i++) {
                Monsters m = (Monsters)monster.get(i);
                Hero[] temp = radiusMonster(m,team);
                if (m.HP() <= 0) continue;
                if(temp[0]!= null)
                    m.attack(temp[0]);
                else{
                    forward(board,m);
                    IO.prompt("Monster "+(i+1)+" "+m.Name()+" moves forward");
                }

            }board.render();
            if(board.reachNexus(monster)) {//monster win
                break;
            }

            //hero's turn
            for (int i = 0; i < HERO_LIMIT; i++) {
                Hero h = (Hero)team.get(i);
                Monsters[] temp = radiusHero(h, monster);

                if(h.x == height-1){
                    IO.prompt("Hero "+(i+1)+" "+ TileSet.HERO[i]+"is on the hero nexus" );
                    onNexusMarket(in,h);
                    board.render();
                }

                if(temp[0]!= null && temp[0].HP() > 0){
                    //if(temp[0].HP() <= 0) continue;
                    //can choose move or attack monster
                    int d = IO.promptInt(in,"Hero "+(i+1)+" "+ TileSet.HERO[i]+" encounter a monster. Please enter 1 to move, 2 to fight, enter other digit to quit",0,9);
                    if (d == 2) {
                        h.recover();
                        Round fight = new Round();
                        int roundResult = fight.HeroAction(h, temp[0], in);
                        if (roundResult == 1) {
                            //monster die
                            board.tiles[temp[0].x][temp[0].y]  = TileSet.DEFAULT;
                            IO.prompt("Hero "+(i+1)+" "+ TileSet.HERO[i]+" defeated monster "+ temp[0].Name() + "!");
                            monster_num--;
                        }
                        if (roundResult == -1) {
                            //respawn hero in the nexus
                            h.x = height-1;
                            board.tiles[h.x][h.y] = TileSet.HERO[i];
                            h.prev = TileSet.DEFAULT; //nexus
                            h.revive(); //recover half hp

                        }
                    }
                    else if(d == 1){
                        moveHero(board,h,i,in);
                    }
                    else {
                        quit = true;
                        break;
                    }
                }
                else {// no monster next to hero
                    moveHero(board,h,i,in);
                }
                board.render();
            }
            if(board.reachNexus(team))
                break;
            //end of a round
            round++;

        }
        if(board.reachNexus(team)) {
            IO.prompt("Hero Win!");
        }
        else if(board.reachNexus(monster)) {
            IO.prompt("Hero Lost");
        }
    }

    public void moveHero(Board board, Hero h, int i, Scanner in){
        IO.prompt("For hero "+(i+1)+ " "+TileSet.HERO[i]+", please enter w/a/s/d to move, enter b to go back to Nexus, enter t to teleport to another lane");
        char ch = in.next().charAt(0);
        board.tiles[h.x][h.y] = h.prev;
        //update hero(i) 's position
        move(board,ch,h);
        h.prev = board.tiles[h.x][h.y]; //now player at a new position, store its tile
        board.tiles[h.x][h.y] = TileSet.HERO[i];
    }

    /**
     * return an array of heroes that monster m can attack
     * @param m team monster
     * @param team team hero
     * @return
     */
    private Hero[] radiusMonster(Monsters m, Team team) {
        Hero[] temp = new Hero[team.roles.length];
        int k = 0;
        for(int i = 0; i< team.roles.length;i++){
            //hero is below m
            if(Math.abs(m.y-team.roles[i].y)<=1 && team.roles[i].x - m.x <= 1 )
                temp[k++] = (Hero)team.roles[i];
        }
        return temp;
    }

    private Monsters[] radiusHero(Hero h, Team team) {
        Monsters[] temp = new Monsters[team.roles.length];
        int k = 0;
        for(int i = 0; i< team.roles.length;i++){
            if(Math.abs(h.y-team.roles[i].y)<=1 && h.x-team.roles[i].x<=1)
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
        for ( int row = x; row > 0; row --) {
            if ((y>0 && b.existHero(x,y-1))|| (y<width-1 && b.existHero(x,y+1)))
                return true;
        }
        return false;

    }
    private boolean willPassMonster(Board b, int x, int y) {
        for (int row = x; row < height; row++){
            if ((y > 0)&&b.existMonster(row,y-1) || (y<width-1)&&b.existMonster(row,y+1))
                return true;
        }
        return false;
    }
    private void onNexusMarket(Scanner in, Hero h){
//        int d = IO.promptInt(in," Please enter 1 to go to market, enter other number to pass",0,9);
//        if(d == 1)
//        char ans;
        System.out.println("Would you like to go to the Market? (y for yes, other to pass)");
        if(in.next().charAt(0) == 'y')
            market.onMarket(in,h);
    }



}
