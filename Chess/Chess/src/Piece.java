public class Piece{
    int type;
    int position;   
    
    public Piece(int type, int position) {
        this.type = type;
        this.position = position;
    }

    public int getType() {
        return type;
    }
    
    /**
     * Used for promotion(s)
     * @param type
     */
    public void setType(int type) {
        this.type = type;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
        
}