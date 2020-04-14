public class TileSet {
    public static final Tile DEFAULT = new Tile(' ',Color.BLACK);
    public static final Tile[] HERO = {new Tile('@', Color.PURPLE),new Tile('#', Color.PURPLE),new Tile('$', Color.PURPLE)};
    public static final Tile MONSTER = new Tile('M', Color.PURPLE);
    public static final Tile MARKET = new Tile('m', Color.GREEN);
    public static final Tile WALL = new Tile('█',Color.YELLOW);
    public static final Tile monsterNexus = new Tile('▢',Color.BLUE);
    public static final Tile heroNexus = new Tile('▢', Color.GREEN);
    public static final Tile BUSH = new Tile('"',Color.RED); // boost dexterity
    public static final Tile KOULOU = new Tile('·',Color.RED); // boost strength
    public static final Tile CAVE =  new Tile('▲',Color.RED);




}
