public abstract class Role {
    public String name;
    public double hp;
    public int level;
    //position
    public int x;
    public int y;
    public Role(String name, int level){
        this.name = name;
        this.level = level;
        hp = 100 * level;
    }
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String Name() {
        return name;
    }

    public double HP(){
        return hp;
    }

    public void setHP(double x){
        if(x <0) x = 0;
        hp = x;
    }
    public int Level(){
        return level;
    }
    abstract public double Defence();
    abstract public void info();

}
