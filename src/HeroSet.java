public class HeroSet extends RoleSet{
    public static Hero[] hero = {
            new Warriors("Gaerdal_Ironhand",100,700,500,600,1354,7),
            new Warriors("Sehanine_Monnbow",600, 700, 800, 500, 2500,8),
            new Warriors("Muamman_Duathall",300, 900, 500, 750, 2546,6),
            new Warriors("Flandal_Steelskin",200, 750, 650, 700, 2500, 7),
            new Sorcerers("Garl_Glittergold", 700, 550, 600,500, 2500,7),
            new Sorcerers("Rillifane_Rallathil",1300,750, 450,500, 2500,9),
            new Sorcerers("Segojan_Earthcaller",900,800,500,650, 2500, 5),
            new Sorcerers("Skoraeus_Stonebones",800,850,600,450, 2500, 6),
            new Paladins("Solonor_Thelandira",300,750,650,700,2500,7),
            new Paladins("Sehanine_Moonbow",300,750,700,700,2500,7),
            new Paladins("Skoraeus_Stonebones",  250,650,600,350,2500,4),
            new Paladins("Garl_Glittergold",100,600,500,400,500,5),
    };

    public static void list() {
        for (int i = 0; i < hero.length; i++){
            if (i == 0) {
                System.out.println(Color.CYAN+"Warriors:"+Color.RESET);
                Hero.header();
            }
            if (i == Warriors.n) {
                System.out.println(Color.CYAN+"Sorcerers:"+Color.RESET);
                Hero.header();
            }
            if (i == Warriors.n+Sorcerers.n) {
                System.out.println(Color.CYAN+"Paladins:"+Color.RESET);
                Hero.header();
            }
            hero[i].info();

        }

    }

}
