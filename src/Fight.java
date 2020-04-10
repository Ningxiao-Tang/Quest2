import java.util.Scanner;

public class Fight {
    /**
     * A fight consists of multiple rounds. The first to attack is always the team of heroes.
     * The fight ends only when the hp of either all the monsters or all the heroes is zeroed.
     * If the heroes win the fight then they earn some money based on the level and the number of monsters that they faced in that fight.
     * If the monsters win the fight the heroes lose half of their money.
     */

    public Board board;
    public Hero[] hero;
    public Monsters[] monster;

    public Fight(Board b, Role[] h, Role[] m, Scanner sc) {
        board = b;
        //hero = (Hero[]) h;
        //monster = (Monsters[]) m;
        //Scanner sc = new Scanner(System.in);
        fight(h,m,sc);

    }


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
            IO.prompt("Hero failed.");
            //System.out.println("Victory "+victory+" Result "+result);
            for(int i = 0; i < hero.length; i++){
                ((Hero)hero[i]).money /=2;
            }
        }
        if(lose(monster)){
            result = 1;
            IO.prompt("Hero defeated monsters!");
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
        IO.prompt(hero.Name()+ " VS " + monster.Name());
        int d = IO.promptInt(sc,"To change weapon/armory, please enter 0\n" +
                "To attack please enter 1\nTo cast a spell please enter 2\nTo use a potion please enter 3",0,3);
        hero.recover();
        if (d == 3) { // consume potion
            int pid = hero.hasP();
            if (pid != 0){
                Potions p = hero.consumeP(pid);
                IO.prompt(p.Name() +" is consumed." );
            }
            else {
                IO.prompt("No Potion Left.");
            }
        }
        if (d == 2) { // cast a spell
            Spell sp = null;
            if ((sp = hero.hasSp()) != null){
                IO.prompt("Hero cast spell "+ sp.Name());
                hero.cast(sp,monster);
            }
            else {
                IO.prompt("No Spell Founded.");
            }
        }

        if (d == 1) { // regular attack
            hero.attack(monster);
        }

        if (d == 0){ // change weapon/armory
            if(hero.itemCount == 0)
                IO.prompt("No armory/weapon found");
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
                    IO.prompt("Hero doesn't have the item.");

            }
        }
        // monster attack
        if(monster.HP()>0)
            monster.attack(hero);
        //at end of each round, print roles information
        hero.header();
        hero.info();
        monster.header();
        monster.info();


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

}
