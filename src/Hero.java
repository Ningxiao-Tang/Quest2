import java.lang.*;

public class Hero extends Role implements Attackable{
    //private String name;
    public double mana;
    public double strength;
    public double agility;
    public double dexterity; // is added to the amount of damage when casting a spell
    public int money;
    public int exp;
    public double maxHP;
    public double maxMana;
    //public int hp;
    //public int level;
    public int favored  = 0;
    public int[] inventory = new int[ItemSet.size];
    public int itemCount = 0;
    public Weapon[] weapons = new Weapon[ItemSet.weaponCount()];
    public Armory[] armories = new Armory[ItemSet.armoryCount()];
    public Potions[] potions = new Potions[ItemSet.potionCount()];
    public Spell[] spells = new Spell[ItemSet.spellCount()];
    public Weapon wp = null;
    public Armory arm = null;
    public Tile prev = TileSet.DEFAULT;

    public Hero(String n, double mana, double strength, double agility, double dexterity, int money, int exp, int f) {
        super(n,1);
        //this.name = n;
        this.mana = mana;
        this.strength = strength;
        this.agility = agility;
        this.dexterity = dexterity;
        this.money = money;
        this.exp = exp;
        this.favored = f;
        maxHP = hp;
        maxMana = this.mana;
        //level = 1;
        //hp = 100*level;
    }
    public static void header() {
        System.out.println(String.format("%-24s%-10s%-10s%-10s%-10s%-10s%-10s%-10s%-10s\n",
                "Name","Level","HP","Money","Exp","Strength","Agility","Dexterity","Mana"));
    }
    public void info() {
        System.out.println(String.format("%-24s%-10s%-10.2f%-10s%-10s%-10s%-10s%-10s%-10s\n",
                name,level,hp,money,exp,strength,agility,dexterity,mana));

    }
    public void Inventory() {
        if (itemCount == 0) {
            System.out.println(Color.GREEN+ "Inventory is empty"+Color.RESET);
        }
        else{
            for(int i = 0; i< inventory.length; i++){
                if (inventory[i] == 0) continue;
                ItemSet.items[i].info();
                System.out.println(i+1);
            }
        }
    }
    public void list() {
        IO.prompt(this.Name()+"'s inventory ");
        if(itemCount != 0) {
            for(int i = 0; i< inventory.length; i++){
                if (inventory[i] != 0) {
                    System.out.println(ItemSet.items[i].Name());
                }
            }
        }
        else
            IO.prompt("is empty");
    }
    public int getItemCount() {
        return itemCount;
    }
    public void recover() {
        this.hp = Math.min(this.hp*1.01, maxHP);
        this.mana = Math.min(this.mana*1.01, maxMana);
    }
    public void gain(int d){
        this.money += 100*d;
        this.exp +=2;
    }

    public void attack(Monsters monster) {
        if(Math.random() < monster.Dodge()/10000)
            IO.prompt("Monster "+monster.Name() + " dodged the attack");
        else{
            if(this.Strength() > monster.Defence()){
                IO.prompt("Hero "+this.name + " cause " + this.Strength() + " damage to " +"Monster "+monster.name);
                monster.setHP(monster.HP()-(this.Strength() - monster.Defence()));
            }
            else
                IO.prompt("Hero "+this.name +"'s attack is defended");
        }

    }

    public int hasP() {
        int n = 0;
        for(int i = ItemSet.k[1]; i < ItemSet.k[2]; i++) {
            if(inventory[i] != 0)
                n = i;
        }
        return n;
    }
    public Potions consumeP(int n){
        Potions p = (Potions) ItemSet.items[n];

                if (p.getAttribute().equalsIgnoreCase("strength"))
                    strength += p.increase;
                if (p.getAttribute().equalsIgnoreCase("agility"))
                    agility += p.increase;
                if (p.getAttribute().equalsIgnoreCase("dexterity"))
                    dexterity +=p.increase;
                if (p.getAttribute().equalsIgnoreCase("mana"))
                    mana += p.increase;
        return p;
    }

    public Spell hasSp() {
        Spell has = null;
        for(int i = ItemSet.k[2]; i < ItemSet.k[3]; i++) {
            if(inventory[i] != 0){
                has = (Spell) ItemSet.items[i];
            }
        }
        return has;
    }

    /**
     * spell has two effect: cause damage and reduce enemy's skill
     * @param spell A spell’s final damage is spells_base_damage + (dexterity/10000)*spells_base_damage
     * @param m
     * An ice spell, apart from the damage it causes it also reduces the damage range of the enemy.
     * A fire spell,apart from the damage it causes it also reduces the defense level of the enemy.
     * A lightning spell, apart from the damage it causes it also reduces the dodge chance of the enemy.
     */
    public void cast(Spell spell, Monsters m) {
        double d = spell.Damage() *(1+this.dexterity/10000);
        if (m.Defence() < d) m.setHP(m.HP()-(d-m.Defence()));
        if (spell instanceof IceSpell){
            m.setDamage(spell.magicEffect(m.Damage()));
        }
        else if (spell instanceof FireSpell) {
            m.setDefence(spell.magicEffect(m.Defence()));
        }
        else{
            m.setAgility(spell.magicEffect(m.Dodge()));
        }
        this.mana -= spell.Mana();

    }

    public boolean canLevelUp(){
        if (exp/10  == level)
            return true;
        return false;
    }
    public void LevelUp() {
        if (this.canLevelUp()){
            double ds, dd, da;
            ds= this.strength*0.05;
            dd= this.dexterity*0.05;
            da= this.agility*0.05;
            switch (this.favored) {
                case 1: //Warriors are favored on the strength and the agility.
                    this.strength  += 2*ds;
                    this.dexterity += dd;
                    this.agility   += 2*da;
                    break;
                case 2: //Sorcerers are favored on the dexterity and the agility.
                    this.strength  += ds;
                    this.dexterity += 2*dd;
                    this.agility   += 2*da;
                    break;
                case 3:  //Paladins are favored on the strength and the dexterity.
                    this.strength  += 2*ds;
                    this.dexterity += 2*dd;
                    this.agility   += da;
                    break;
                default:
                    this.strength  += ds;
                    this.dexterity += dd;
                    this.agility   += da;
            }

            this.level++;
            this.mana += mana/10;
            this.hp = this.level *100;
            maxHP = hp;
            maxMana = mana;
            this.exp = 0;
        }
    }
    public void revive(){
        this.hp = maxHP/2;
    }
    public double Strength() {
        if (wp != null) return (strength + wp.Damage())*0.05;
        return strength*0.05;
    }
    public double Dodge() {
        return agility*1.0/10000;
    }

    @Override
    public double getHP() {
        return HP();
    }

    @Override
    public double Defence() {
        // if hero has no armory, return 0; else return armory's defence
        if (arm != null) return arm.Defence()*0.02;
        return 0;
    }



}
