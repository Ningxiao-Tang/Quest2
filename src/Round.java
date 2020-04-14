import java.util.Scanner;

public class Round {
//    private Hero hero;
////    private Monsters monster;
////    public Round()

    /**
     *
     * @param hero to choose action for
     * @param monster  object
     * @param sc  scanner
     * @return
     */
    public int HeroAction(Hero hero, Monsters monster, Scanner sc) {
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
                if (hero.prev.equals(TileSet.BUSH) ){
                    hero.dexterity = hero.dexterity*1.1;
                    hero.cast(sp,monster);
                    hero.dexterity = hero.dexterity/1.1;
                }
                else
                    hero.cast(sp,monster);
            }
            else {
                IO.prompt("No Spell Founded.");
            }
        }

        if (d == 1) { // regular attack
            if (hero.prev.equals(TileSet.KOULOU) ){
                hero.strength *= 1.1;
                hero.attack(monster);
                hero.strength /= 1.1;
            }
            else
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
        //at end of each round, print roles information
        Hero.header();
        hero.info();
        Monsters.header();
        monster.info();

        if(hero.HP() <= 0) result = -1;
        if (monster.HP() <= 0) result = 1;
        return result;
    }
}
