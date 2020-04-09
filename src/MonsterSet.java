public class MonsterSet extends RoleSet{

    public static Monsters[] monsters = {
            new Dragons("Natsunomeryu",1,100,200,10),
            new Dragons("Chrysophylax",2,200,300,20),
            new Dragons("Desghidorrah",3, 300,400, 35),

            new Exoskeletons("BigBad-Wolf",1,150,250,15),

            new Spirits("Aim-Haborym",1,450,150,15),
    };
    public static int n = monsters.length;
    public static void list() {
        Monsters.header();
        for( int i = 0; i < monsters.length; i++) {
            monsters[i].info();
        }
    }
}
