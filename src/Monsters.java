public class Monsters extends Role implements Attackable{

    //private int prev_level_exp;

    public double damage; //damage they can cause
    private double agility; // chance of dodge attack
    private double defence; // deducted from the damage of an incoming attack

    public Monsters(String n, int level, double damage, double defense, double dodgeChance) {
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
        System.out.println(String.format("%-24s%-10s%-10s%-10s%-10s%-10s\n",
                name,level,hp,damage,defence,agility));

    }
    public void attack(Hero hero) {
        if(Math.random() <=  hero.Dodge())
            IO.prompt("Hero "+ hero.Name()+ " dodged the attack");
        else if( this.damage > hero.Defence()){
            IO.prompt("Monster "+this.name + " cause " + this.damage + " damage to " +hero.name);
            hero.setHP(hero.HP()-(this.Damage()-hero.Defence()));
        }

    }

    @Override
    public double getHP() {
        return HP();
    }

    @Override
    public double Defence() {
        return defence;
    }
    public double Damage() {
        return damage*0.05;
    }
    public double Dodge() {
        return agility;
    }
    public void setDamage(double x){
        this.damage = x;
    }
    public void setDefence(double x) {
        defence = x;
    }
    public void setAgility(double x) {
        agility = x;
    }


}
