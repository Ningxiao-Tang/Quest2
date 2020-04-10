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
    private String s3 = "Please enter number 1 to 12 to choose your hero";

    public void intro(){
        prompt( "Now Your Quest Begins..." );
        System.out.println(Color.RED + "In the map, H is hero position, m is the market\n" +
                "Use w/a/s/d to explore the map\n" +
                "Press i to view your hero, press q to quit" + Color.RESET);
        System.out.println(" ");
    }



    public void selectHero(Team team, Scanner in) {
        prompt("You can choose heroes from the list below");
        HeroSet.list();
        int i = 0;
        do {
            int hero_no = IO.promptInt(in, s3, 1, 12);
            team.roles[i] = HeroSet.hero[hero_no - 1];
            team.levels[i] = team.roles[i].Level();
            i++;
        } while (i < hero_num);
        Arrays.sort(team.levels);
    }

    /**
     * print info of team hero and team monster
     */
    public void info(){
        Hero.header();
        for(int n = 0 ;n < hero_num; n++){
            team.roles[n].info();
        }
        Monsters.header();
        for(int j = 0; j < hero_num; j++) {
            monster.roles[j].info();}
    }

    public void move(char ch, Team team) {
        Position p = move(ch,team.x,team.y);
        team.x = p.x;
        team.y = p.y;
    }

    private Position move (char ch, int x, int y){
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
            this.info();
        }
        return pos;
    }


    public void play() {

        System.out.println("Welcome to Quest.");
        Scanner in = new Scanner(System.in);

        hero_num = IO.promptInt(in, s2, 1, 3);
        team = new Team(hero_num);
        this.selectHero(team, in);
        this.intro();

        board = new Board();
        board.Init(1);
        board.render();

        //game play
        char ch;
        Tile t = TileSet.DEFAULT;
        while ((ch = in.next().charAt(0)) != 'q' ){
            board.tiles[team.x][team.y] = t;
            //update heroes position
            move(ch, team);
            t = board.tiles[team.x][team.y]; //now player at an accessible cell
            board.tiles[team.x][team.y] = TileSet.HERO; // render board after move
            board.render();

            if(t == TileSet.MARKET) {
                onMarket(in, team.roles);
                board.render();
            }
            if(t == TileSet.DEFAULT) {
                if(Math.random() < 0.8) {
                    prompt("Watch out! You encounter monsters! Heroes, Let's fight!");
                    createMonster(hero_num, team.levels[hero_num-1]);
                    Fight f = new Fight(board,team.roles, monster.roles,in);
                    //fight(team.roles, monster.roles,in);
                }
            }
        }
    }

    private void onMarket(Scanner sc, Role[] heros) {

        for(int i = 0; i < heros.length; i++) {
            market.show();
            int d;
            Hero hero = (Hero) heros[i];
            prompt("For hero "+  hero.Name() +":" );
            Hero.header();
            hero.info();
            do{
                d = IO.promptInt(sc,"Please Enter 1 to buy, enter 2 to sell, enter 3 to pass",1,3);
                if (d == 1) { // buy

                    int item = IO.promptInt(sc,"Enter the item number that you want to buy",1,ItemSet.size+1);
                    if(!(market.inventory[item-1].checkLevel(hero))){
                        prompt("Hero Level doesn't meet the minimum level to buy this item");
                    }
                    else if(!(market.inventory[item-1].checkMoney(hero))) {
                        prompt("Hero doesn't have enough money");
                    }
                    else if(market.inventory[item-1] instanceof Spell && !(((Spell)market.inventory[item-1]).checkMana(hero))){
                        prompt("Hero doesn't have enough mana");
                    }
                    else {
                        hero.money -= market.inventory[item-1].Cost();
                        hero.inventory[item-1] ++;
                        hero.itemCount++;
                        prompt("You have bought item: " +market.inventory[item-1].Name());
                        if(item <= ItemSet.k[0]) hero.weapons[item-1] = (Weapon) ItemSet.items[item-1];
                        if(item> ItemSet.k[0] && item <= ItemSet.k[1]) hero.armories[item-1-ItemSet.k[0]] = (Armory) ItemSet.items[item-1];
                        if(item > ItemSet.k[1] && item <= ItemSet.k[2]) hero.potions[item-1-ItemSet.k[1]] = (Potions) ItemSet.items[item-1];
                        if(item > ItemSet.k[2]) hero.spells[item-1-ItemSet.k[2]] = (Spell) ItemSet.items[item-1];

                    }
                }
                if (d == 2) { // sell
                    hero.Inventory();
                    if(hero.itemCount == 0) continue;
                    int item = IO.promptInt(sc,"Enter the item number that you want to sell",1,hero.itemCount +1);
                    //hero.Inventory();
                    if (hero.itemCount != 0) {
                        hero.itemCount--;
                        hero.inventory[item-1]--;
                        Equipment e = market.inventory[item];
                        hero.money += e.Cost()/2;
                    }

                }
                if (d == 3){
                    hero.header();
                    hero.info();
                    hero.list();
                    break;
                }
            }while(true);
//hero.info();

        }
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

/*

    private int fight(Role[] hero, Role[] monster,Scanner sc) {
        int result = 1 ;
        do{
            for (int j = 0; j < monster.length;j++){
                if(monster[j].HP() <= 0) continue;
                for (int i= 0; i < hero.length ;i++) {
                    if(hero[i].HP() <= 0 || monster[j].HP() <= 0) continue;
                    //System.out.println(j+" "+i);
                    fight((Hero)hero[i],(Monsters)monster[j],sc);
                }
            }

        }while(lose(hero) || lose(monster));

        if(lose(hero)){
            result = -1;
            prompt("Hero failed.");
            //System.out.println("Victory "+victory+" Result "+result);
            for(int i = 0; i < hero.length; i++){
                ((Hero)hero[i]).money /=2;
            }
        }
        if(lose(monster)){
            result = 1;
            prompt("Hero defeated monsters!");
            for(int i = 0; i < hero.length; i++) {
                if(hero[i].HP() == 0)
                    ((Hero) hero[i]).revive();
                if(((Hero)hero[i]).canLevelUp())
                    ((Hero)hero[i]).LevelUp();
                ((Hero) hero[i]).gain(monster[i].level);
            }
        }
        board.render();
        return result;
    }
    private int fight(Hero h, Monsters m, Scanner sc) {
        int result;
        do{
            result = round(h,m,sc);
            //System.out.println(h.Name()+m.Name()+result);
        }while (result == 0);
        return result;
    }



    private int round(Hero hero, Monsters monster, Scanner sc) {
        //Scanner sc = new Scanner(System.in);
        int result = 0;
        prompt(hero.Name()+ " VS " + monster.Name());
        int d = IO.promptInt(sc,"To change weapon/armory, please enter 0\n" +
                "To attack please enter 1\nTo cast a spell please enter 2\nTo use a potion please enter 3",0,3);
        hero.recover();
        if (d == 3) { // consume potion
            int pid = hero.hasP();
            if (pid != 0){
                Potions p = hero.consumeP(pid);
                prompt(p.Name() +" is consumed." );
            }
            else {
                prompt("No Potion Left.");
            }
        }
        if (d == 2) { // cast a spell
                    Spell sp = null;
                    if ((sp = hero.hasSp()) != null){
                        prompt("Hero cast spell "+ sp.Name());
                        hero.cast(sp,monster);
                    }
                    else {
                        prompt("No Spell Founded.");
                    }
                }

        if (d == 1) { // regular attack
            hero.attack(monster);
        }

        if (d == 0){ // change weapon/armory
            if(hero.itemCount == 0) prompt("No armory/weapon found");
            else{
                for(int i = 0 ; i < ItemSet.k[1];i++){
                    if(hero.inventory[i] != 0){
                        ItemSet.items[i].info();
                        System.out.println(i+1);
                    }
                }
                int choice = IO.promptInt(sc,"Choose weapon/armory by the number ",1,ItemSet.k[1]);
                if (hero.inventory[choice] != 0) {
                    if (choice < ItemSet.k[0]) hero.wp = (Weapon) ItemSet.items[choice];
                    else hero.arm = (Armory) ItemSet.items[choice];
                }
                else
                    prompt("Hero doesn't have the item.");

            }
        }
        // monster attack
        if(monster.HP()>0)
            monster.attack(hero);
        //at end of each round, print roles information
        info();
//        Hero.header();
//        hero.info();
//        Monsters.header();
//        monster.info();
        if(hero.HP() <= 0) result = -1;
        if (monster.HP() <= 0) result = 1;
        return result;
    }

    private boolean lose(Role[] player) {
        int i = 0;
        boolean b = false;
        for (; i < player.length; i++) {
            if (player[i].HP() >= 0 ) break;
        }
        if(i == player.length) b = true;
        return b;
    }
*/

}
