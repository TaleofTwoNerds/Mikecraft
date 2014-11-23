package mikecraft;

public enum BlockType {

    STONE("res/images/stone.png"), REDSTONE("res/images/redstone_block.png"), GRASS("res/images/grass.png"),
    DIRT("res/images/dirt.png");
    public final String location;

    BlockType(String location) {
        this.location = location;
    }
}