public class Armory extends Equipment implements Tradable{
    private int defence;

    public Armory(String s, int cost, int level, int damageDeduction){
        super(s,cost,level);
        this.defence = damageDeduction;
    }
    public int Defence() {
        return defence;
    }

    @Override
    public void info() {
        System.out.print(String.format("%-20s%-15s%-15s%-15s",this.Name(),this.Cost(),this.Level(),this.Defence()));
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
