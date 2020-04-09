public abstract class Spell extends Equipment{

    private int damage;
    private int mana;

    public Spell(String s, int c, int l, int d, int m) {
        super(s,c,l);
        damage = d;
        mana = m;

    }

    public int Damage() {
        return this.damage;
    }
    public int Mana() {return mana;}
    public boolean checkMana(Hero h) {
        return h.mana >= this.mana;
    }
    public int magicEffect(int skill) {
        return (int)(skill*0.9);
    }

    @Override
    public void info() {
        System.out.print(String.format("%-20s%-15s%-15s%-15s%-15s",this.Name(),this.Cost(),this.Level(),damage,mana));
    }
}
