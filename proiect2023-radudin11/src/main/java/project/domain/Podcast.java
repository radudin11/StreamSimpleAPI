package project.domain;

public class Podcast extends GenericStream {
    public Podcast(int id, int streamGenre, long noOfStreams, int streamerId, long length, long dateAdded, String name) {
        super(StreamType.PODCAST.toInt(), id, streamGenre, noOfStreams, streamerId, length, dateAdded, name);
    }

    public Podcast() {
        super(StreamType.PODCAST.toInt(), 0, 0, 0, 0, 0, 0, "");
    }
}
