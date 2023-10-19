package project.domain;

public class StreamerFactory {

    public static StreamerFactory streamerFactory = null;

    public Streamer createStreamer(int id, String name, int streamerType) {
        switch (streamerType) {
            case 3:
                return new AudiobookAuthor(id, name);
            case 2:
                return new PodcastHost(id, name);
            case 1:
                return new Musician(id, name);
            default:
                return null;
        }
    }

    private StreamerFactory() {
    }

    public static StreamerFactory getInstance() {
        if (streamerFactory == null) {
            streamerFactory = new StreamerFactory();
        }
        return streamerFactory;
    }
}
