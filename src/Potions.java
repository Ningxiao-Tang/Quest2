public class Potions extends Equipment implements Tradable{
    //Name/cost/required level/attribute increase
    public int increase;
    private String attribute;
    public Potions(String name, int c, int l, int in){
        super(name,c,l);
        increase = in;
    }
    public Potions(String name, int c, int l, int in, String s) {
        this(name,c,l,in);
        attribute = s;
    }
    public String getAttribute(){
        return attribute;
    }


    @Override
    public void info() {
        System.out.print(String.format("%-20s%-15s%-15s%-15s",this.Name(),this.Cost(),this.Level(),increase));
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
