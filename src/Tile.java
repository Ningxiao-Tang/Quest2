public class Tile {

    private String color;
    private String description;
    private char character;

    public Tile (char ch, String color){
        this.character = ch;
        this.color = color;
    }
    public Tile (char ch){
        this(ch,Color.BLACK);
    }

    public char Character(){
        return character;
    }
    public String Color(){
        return color;
    }
    @Override
    public String toString() {
        return String.valueOf(this.character);
    }

    @Override
    public boolean equals(Object obj) {
        Tile t = (Tile) obj;
        return this.Character()==t.Character();
    }
}
