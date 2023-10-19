package project.domain;

import java.util.List;

public class User {
    int id;
    String name;
    List<Integer> streams;

    public User(int id, String name, List<Integer> streams) {
        this.id = id;
        this.name = name;
        this.streams = streams;
    }

    public User() {
        this(0, "", null);
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        StringBuilder streamsString = new StringBuilder();
        for (int stream : streams) {
            streamsString.append(stream).append(" ");
        }
        return "\"" + name + "\",\"" + id + "\",\"" + streamsString + "\"";
    }

    public List<Integer> getStreams() {
        return streams;
    }

    public void removeStreamFromHistory(int streamId) {
        for (int i = 0; i < streams.size(); i++) {
            if (streams.get(i) == streamId) {
                streams.remove(i);
                break;
            }
        }
    }

    public void addStream(int streamId) {
        streams.add(streamId);
    }
}
