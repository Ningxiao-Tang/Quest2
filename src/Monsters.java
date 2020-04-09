public class Monsters extends Role {

    //private int prev_level_exp;

    private int damage; //damage they can cause
    private int agility; // chance of dodge attack
    private int defence; // deducted from the damage of an incoming attack

    public Monsters(String n, int level, int damage, int defense, int dodgeChance) {
        super(n,level);
        this.damage = damage;
        this.defence = defense;
        this.agility = dodgeChance;

    }

    public static void header() {
        System.out.println(String.format("%-24s%-10s%-10s%-10s%-10s%-10s\n",
                "Name","Level","HP","Damage","Defence","Dodge chance"));
    }
    public void info() {
        System.out.println(String.format("%-24s%-10s%-10s%-10s%-10s\n",
                name,level,hp,damage,defence,agility));

    }
    public void attack(Hero hero) {
        if(Math.random() <=  hero.Dodge())
            IO.prompt("Hero "+ hero.Name()+ " dodged the attack");
        else if( this.damage > hero.Defence()){
            IO.prompt(this.name + " cause " + this.damage + " damage to " +hero.name);
            hero.setHP(hero.HP()-(int)(this.Damage()-hero.Defence()));
        }

    }



    public double Damage() {
        return damage*0.05;
    }
    public void setDamage(int x){
        this.damage = x;
    }
    public void setDefence(int x) {
        defence = x;
    }
    public int Dodge() {
        return agility;
    }
    public void setAgility(int x) {
        agility = x;
    }
    @Override
    public int Defence() {
        return defence;
    }

}
