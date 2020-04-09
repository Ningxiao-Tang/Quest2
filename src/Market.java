import java.util.Set;
public class Market {
    //use collection to represent inventory
    // implement add(), iterate()

    public Equipment[] inventory;

    private static int size = ItemSet.size;
    private static int[] k = new int[6];
    public Market() {
        inventory = new Equipment[size];
        for (int i = 0; i < size; i++){
            inventory[i] = ItemSet.items[i];
        }
    }
    /*
    private int add(Equipment[] Es, int k) {
        for(int i = 0; i < Es.length; i++,k++){
            inventory[k] = Es[i];
        }
        return k;
    }
    private void add(){
        k[0] = this.add(ItemSet.wp,0);
        k[1] = this.add(ItemSet.arm,k[0]);
        k[2] = this.add(ItemSet.pot,k[1]);
        k[3] = this.add(ItemSet.isp,k[2]);
        k[4] = this.add(ItemSet.fsp,k[3]);
        k[5] = this.add(ItemSet.lsp,k[4]);
    }*/
    public void show() {
        String bar = "--------------------------------------------------------------------";
        System.out.println(Color.GREEN+ "Welcome to the Market"+Color.RESET);
        System.out.println(bar);
        for(int i = 0; i < size; i++) {
            if(i == 0){
                System.out.println(Color.CYAN + "Weapon" + Color.RESET);
                System.out.println(String.format("%-15s%-15s%-15s%-15s%-5s\n","Name","Required Cost","Level","Damage","Number"));
            }
            else if (i == ItemSet.k[0]) {
                System.out.println(Color.CYAN + "Armory" + Color.RESET);
                System.out.println(String.format("%-20s%-15s%-15s%-15s%-5s\n","Name","Required Cost","Level","Defence","Number"));
            }
            else if (i == ItemSet.k[1]) {
                System.out.println(Color.CYAN + "Potion" + Color.RESET);
                System.out.println(String.format("%-20s%-15s%-15s%-15s%-5s\n","Name","Required Cost","Level","Attribute Increase","Number"));
            }
            else if (i == ItemSet.k[2]) {
                System.out.println(Color.CYAN + "Spell" + Color.RESET);
                System.out.println(String.format("%-20s%-15s%-15s%-15s%-15s%-5s\n","Name","Required Cost","Level","Damage","Mana Cost","Number"));
            }
            inventory[i].info();
            System.out.println(i+1);
        }
    }
}
