public class ItemSet{
    public static Weapon[] wp = {
            new Weapon("Sword",500,1,800,1),
            new Weapon("Bow",300,2,500,2),
            new Weapon("Scythe",1000,6 ,1100,2),
            new Weapon("Axe", 550, 5,850,1),
            new Weapon("Shield",400,1,100,1),
            new Weapon("TSwords",1400,8,1600,2),
            new Weapon("Dagger",200,1,250,1)};


    public static Armory[] arm = {
            new Armory("Platinum_Shield",150,1,200),
            new Armory("Breastplate",350,3,600),
            new Armory("Full_Body_Armor",1000,8,1100),
            new Armory("Wizard_Shield",1200,10,1500),
            new Armory("Speed_Boots",550,4,600)};

    public static Potions[] pot = {
            new Potions("Healing_Potion",250,1,100,"hp"),
            new Potions("Strength_Potions",200,1,75,"strength"),
            new Potions("Magic_Potions",500,4,65,"mana"),
            new Potions("Luck_Elixir",500,4,65,"agility"),
            new Potions("Mermaid_Tears",850,5,100,"dexterity"),
            new Potions("Ambrosia",1000,8,150,"strength")};

    public static IceSpell[] isp = {
            new IceSpell("Snow_Canon",250,1,450,100),
            new IceSpell("Frost_Blizzard",750,5,850,350),
            new IceSpell("Arctic_Storm",700,6,800,300),
            new IceSpell("Ice_Blade",250,1,450,100)};

    public static FireSpell[] fsp = {
            new FireSpell("Flame_Tornado",700,4,850,300),
            new FireSpell("Breath_of_Fire",350,1,450,100),
            new FireSpell("Heat_Wave",450,2,600,150),
            new FireSpell("Lave_Commet",800,7,1000,550),
            new FireSpell("Breath_of_Fire",350,1,450,100)};
    public static LightningSpell[] lsp = {
            new LightningSpell("LightningDagger",400,1,500,150),
            new LightningSpell("Thunder_Blast",750,4,950,400),
            new LightningSpell("Electric_Arrows",550,5,650,200),
            new LightningSpell("Spark_Needles",500,2,600,200)};

    public static Equipment[] items = {
            new Weapon("Sword",500,1,800,1),
            new Weapon("Bow",300,2,500,2),
            new Weapon("Scythe",1000,6 ,1100,2),
            new Weapon("Axe", 550, 5,850,1),
            new Weapon("Shield",400,1,100,1),
            new Weapon("TSwords",1400,8,1600,2),
            new Weapon("Dagger",200,1,250,1),
            new Armory("Platinum_Shield",150,1,200),
            new Armory("Breastplate",350,3,600),
            new Armory("Full_Body_Armor",1000,8,1100),
            new Armory("Wizard_Shield",1200,10,1500),
            new Armory("Speed_Boots",550,4,600),
            new Potions("Healing_Potion",250,1,100,"hp"),
            new Potions("Strength_Potions",200,1,75,"strength"),
            new Potions("Magic_Potions",500,4,65,"mana"),
            new Potions("Luck_Elixir",500,4,65,"agility"),
            new Potions("Mermaid_Tears",850,5,100,"dexterity"),
            new Potions("Ambrosia",1000,8,150,"strength"),
            new IceSpell("Snow_Canon",250,1,450,100),
            new IceSpell("Frost_Blizzard",750,5,850,350),
            new IceSpell("Arctic_Storm",700,6,800,300),
            new IceSpell("Ice_Blade",250,1,450,100),
            new FireSpell("Flame_Tornado",700,4,850,300),
            new FireSpell("Breath_of_Fire",350,1,450,100),
            new FireSpell("Heat_Wave",450,2,600,150),
            new FireSpell("Lave_Commet",800,7,1000,550),
            new FireSpell("Breath_of_Fire",350,1,450,100),
            new LightningSpell("LightningDagger",400,1,500,150),
            new LightningSpell("Thunder_Blast",750,4,950,400),
            new LightningSpell("Electric_Arrows",550,5,650,200),
            new LightningSpell("Spark_Needles",500,2,600,200)};


    public static int weaponCount(){
        return wp.length;
    }
    public static int armoryCount(){
        return arm.length;
    }
    public static int potionCount(){
        return pot.length;
    }
    public static int spellCount(){
        return (isp.length+fsp.length+lsp.length);
    }
    public static int size = ItemSet.items.length;
    //k[0],k[1],k[2],k[3]
    public static int[] k = {wp.length, wp.length+arm.length, wp.length+arm.length+pot.length,
            wp.length+arm.length+pot.length+isp.length+fsp.length+lsp.length};




}
