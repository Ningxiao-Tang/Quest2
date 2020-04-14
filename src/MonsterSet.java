public class MonsterSet extends RoleSet{

    public static Monsters[] monsters = {
            new Dragons("Natsunomeryu",1,100,200,10),
            new Dragons("Chrysophylax",2,200,300,20),
            new Dragons("Desghidorrah",1, 200,100, 15),

            new Exoskeletons("BigBad-Wolf",1,150,250,15),
            new Exoskeletons("WickedWitch",2,250,350,25),
            new Exoskeletons("Brandobaris",1,150,150,20),
            new Exoskeletons("Aasterinian",1,100,200,15),
            new Spirits("Aim-Haborym",1,450,150,15),
            new Spirits("Andrealphus",2,600,500,40),
            new Spirits("Taltecuhtli",1,100,200,30),
            new Spirits("Melchiresas", 1,150,150,25),
            new Spirits("Andromalius",1,150,250,25),


    };
    public static int n = monsters.length;
    public static void list() {
        Monsters.header();
        for( int i = 0; i < monsters.length; i++) {
            monsters[i].info();
        }
    }
}
