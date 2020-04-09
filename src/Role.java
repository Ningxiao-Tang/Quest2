public abstract class Role {
    public String name;
    public int hp;
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

    public int HP(){
        return hp;
    }

    public void setHP(int x){
        if(x <0) x = 0;
        hp = x;
    }
    public int Level(){
        return level;
    }
    abstract public int Defence();
    abstract public void info();

}
