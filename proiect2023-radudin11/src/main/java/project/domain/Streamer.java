package project.domain;

public abstract class Streamer {

    private int id;
    private String name;
    private int streamerType;

    public Streamer(int id, String name, int streamerType) {
        this.id = id;
        this.name = name;
        this.streamerType = streamerType;
    }
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getStreamerType() {
        return streamerType;
    }

    public String toString() {
        return "\"" + name + "\",\"" + id + "\",\"" + streamerType + "\"";
    }
}
