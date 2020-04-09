public class Weapon extends Equipment{
    private int damage;
    private int hands;

    public Weapon(String s, int cost, int level, int damage, int hands){
        super(s,cost,level);
        this.damage = damage;
        this.hands = hands;
    }


    @Override
    public String toString() {
        /*StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(damage);
        return sb.toString();*/
        return super.toString()+damage;
    }

    @Override
    public void info() {
        System.out.print(String.format("%-15s%-15s%-15s%-15s",this.Name(),this.Cost(),this.Level(),damage));
    }
    public int Damage() {
        return damage;
    }
}
