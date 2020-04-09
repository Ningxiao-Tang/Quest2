public abstract class Equipment {
    private String name;
    private int cost;
    private int level;
    public Equipment(String s, int c, int l){
        name = s ;
        cost = c;
        level = l;
    }
    public String Name() {
        return this.name;
    }
    public int Cost() {
        return cost;
    }
    public int Level() {
        return level;
    }

    public boolean checkLevel(Hero h){
        return h.level >= this.level;
    }
    public boolean checkMoney(Hero h){
        return h.money >= cost;
    }

    @Override
    public String toString() {
        return name+cost+level;
    }

    abstract public void info();
}
