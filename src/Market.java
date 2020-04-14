import java.util.Scanner;
import java.util.Set;
public class Market {
    //use collection to represent inventory
    // implement add(), iterate()

    public Equipment[] inventory;

    private static int size = ItemSet.size;
    private static int[] k = new int[6];
    public Market() {
        inventory = new Equipment[size];
        for (int i = 0; i < size; i++){
            inventory[i] = ItemSet.items[i];
        }
    }
    /*
    private int add(Equipment[] Es, int k) {
        for(int i = 0; i < Es.length; i++,k++){
            inventory[k] = Es[i];
        }
        return k;
    }
    private void add(){
        k[0] = this.add(ItemSet.wp,0);
        k[1] = this.add(ItemSet.arm,k[0]);
        k[2] = this.add(ItemSet.pot,k[1]);
        k[3] = this.add(ItemSet.isp,k[2]);
        k[4] = this.add(ItemSet.fsp,k[3]);
        k[5] = this.add(ItemSet.lsp,k[4]);
    }*/
    public void show() {
        String bar = "--------------------------------------------------------------------";
        System.out.println(Color.GREEN+ "Welcome to the Market"+Color.RESET);
        System.out.println(bar);
        for(int i = 0; i < size; i++) {
            if(i == 0){
                System.out.println(Color.CYAN + "Weapon" + Color.RESET);
                System.out.println(String.format("%-15s%-15s%-15s%-15s%-5s\n","Name","Required Cost","Level","Damage","Number"));
            }
            else if (i == ItemSet.k[0]) {
                System.out.println(Color.CYAN + "Armory" + Color.RESET);
                System.out.println(String.format("%-20s%-15s%-15s%-15s%-5s\n","Name","Required Cost","Level","Defence","Number"));
            }
            else if (i == ItemSet.k[1]) {
                System.out.println(Color.CYAN + "Potion" + Color.RESET);
                System.out.println(String.format("%-20s%-15s%-15s%-15s%-5s\n","Name","Required Cost","Level","Attribute Increase","Number"));
            }
            else if (i == ItemSet.k[2]) {
                System.out.println(Color.CYAN + "Spell" + Color.RESET);
                System.out.println(String.format("%-20s%-15s%-15s%-15s%-15s%-5s\n","Name","Required Cost","Level","Damage","Mana Cost","Number"));
            }
            inventory[i].info();
            System.out.println(i+1);
        }
    }

    public void onMarket(Scanner sc, Hero hero) {
        this.show();
        int d;

        IO.prompt("For hero "+  hero.Name() +":" );
        Hero.header();
        hero.info();
        do{
            d = IO.promptInt(sc,"Please Enter 1 to buy, enter 2 to sell, enter 3 to pass",1,3);
            if (d == 1) { // buy

                int item = IO.promptInt(sc,"Enter the item number that you want to buy",1,ItemSet.size+1);
                if(!(this.inventory[item-1].checkLevel(hero))){
                    IO.prompt("Hero Level doesn't meet the minimum level to buy this item");
                }
                else if(!(this.inventory[item-1].checkMoney(hero))) {
                    IO.prompt("Hero doesn't have enough money");
                }
                else if(this.inventory[item-1] instanceof Spell && !(((Spell)this.inventory[item-1]).checkMana(hero))){
                    IO.prompt("Hero doesn't have enough mana");
                }
                else {
                    hero.money -= this.inventory[item-1].Cost();
                    hero.inventory[item-1] ++;
                    hero.itemCount++;
                    IO.prompt("You have bought item: " +this.inventory[item-1].Name());
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
                    Equipment e = this.inventory[item];
                    hero.money += e.Cost()/2;
                }

            }
            if (d == 3){
                Hero.header();
                hero.info();
                hero.list();
                break;
            }
        }while(true);
    }

    public void onMarket(Scanner sc, Role[] heros) {

        for(int i = 0; i < heros.length; i++) {
            //Hero hero = (Hero) heros[i];
            onMarket(sc,(Hero) heros[i]);
        }
    }
}
