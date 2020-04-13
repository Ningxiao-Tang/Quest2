public abstract class Spell extends Equipment implements Tradable{

    private double damage;
    private double mana;

    public Spell(String s, int c, int l, int d, int m) {
        super(s,c,l);
        damage = d;
        mana = m;

    }

    public double Damage() {
        return this.damage;
    }
    public double Mana() {return mana;}
    public boolean checkMana(Hero h) {
        return h.mana >= this.mana;
    }
    public double magicEffect(double skill) {
        return skill*0.9;
    }

    @Override
    public void info() {
        System.out.print(String.format("%-20s%-15s%-15s%-15s%-15s",this.Name(),this.Cost(),this.Level(),damage,mana));
    }

    @Override
    public int getCost() {
        return Cost();
    }

    @Override
    public int getLevel() {
        return Level();
    }
}
