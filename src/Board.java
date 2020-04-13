public class Board {
    private int width;
    private int height;
    public Tile[][] tiles;

    public Board(int h, int w){
        //if(w < 0 || h < 0)
        this.width = w;
        this.height = h;
        tiles =  new Tile[height][width];

        for(int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                tiles[i][j] = TileSet.DEFAULT;
            }
        }
        //System.out.println();
    }
    public Board(int d){
        this(d,d);
    }
    public Board(){
        this(8,8);
    }

    public void Init(int d) {
        if (d == 2) {// quest 2 board

            //Nexus()
//            for(int j = 0; j < width; j++){
//                this.tiles[0][j] = TileSet.monsterNexus;
//                this.tiles[height-1][j] = TileSet.heroNexus;
//            }
            //createWall();
            for (int i = 0; i < height; i++){
                for (int j = 2; j < width; j+=3) {
                    tiles[i][j] = TileSet.WALL;
                }
            }
            //create random cells
            for(int i = 1; i < height-1; i++){
                for (int j = 0; j < width; j++){
                    if (tiles[i][j].equals(TileSet.WALL)) continue;
                    double r = Math.random();
                    if (r < 0.1) tiles[i][j] = TileSet.BUSH;
                    else if (r < 0.2) tiles[i][j] = TileSet.KOULOU;
                    else if (r < 0.3) tiles[i][j] = TileSet.CAVE;
                }
            }
            //spawn monster and heroes
            for(int j = 0; j < width; j+=3){
                spawnMonster(j);
                spawnHero(j);
            }
        }
        else{
            // default quest 1 board
            for(int i = 0; i < height; i++){
                for (int j = 0; j < width; j++){
                    if (i == 0 && j == 0) {
                        tiles[i][j] = TileSet.HERO;
                        continue;
                    }
                    double r = Math.random();
                    if (r < 0.2) tiles[i][j] = TileSet.WALL;
                    else if (r < 0.5) tiles[i][j] = TileSet.MARKET;
                    else tiles[i][j] = TileSet.DEFAULT;
                }
            }
        }
    }

    public void spawnHero(int col) {
        this.tiles[height-1][col] = TileSet.HERO;
    }
    public void spawnMonster(int col) {
        this.tiles[0][col] = TileSet.MONSTER;
    }
    public boolean checkMove(int x, int y){
        boolean ok = false;
        if (x < 0 || y < 0 || x > this.height || y > this.width) {
            IO.prompt("Move out of board");
        }
        else if (this.tiles[x][y] == TileSet.WALL){
            IO.prompt("Inaccessible.");
        }
        else ok = true;
        return ok;
    }

    public int getHeight() {
        return height;
    }

    public void render(){
        char c = '|';
        for (int i = 0; i < height; i++) {
            for (int j = 0, k = 0; j < 2*width+1; j++) {
                if(j%2 == 0) System.out.print('|');
                else {
                    System.out.print(tiles[i][k].Color()+tiles[i][k]+Color.RESET);
                    k++;
                }
            }
            System.out.println();
        }
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                if(tiles[i][j] == null){
                    throw new IllegalArgumentException("The Tile at row " + i+1 + " column "+ j+1 + " is null");
                }
                sb.append(tiles[i][j].Character());
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
