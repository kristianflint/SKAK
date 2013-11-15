public class Move{
    int positionFrom;
    int positionTo;

    public Move(int positionFrom, int positionTo) {
        this.positionFrom = positionFrom;
        this.positionTo = positionTo;
    }

    public int getPositionFrom() {
        return positionFrom;
    }

    public int getPositionTo() {
        return positionTo;
    }
}