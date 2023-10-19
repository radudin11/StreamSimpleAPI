package project.domain;

public class Song extends GenericStream {
    public Song(int id, int streamGenre, long noOfStreams, int streamerId, long length, long dateAdded, String name) {
        super(StreamType.SONG.toInt(), id, streamGenre, noOfStreams, streamerId, length, dateAdded, name);
    }

    public Song() {
        super(StreamType.SONG.toInt(), 0, 0, 0, 0, 0, 0, "");
    }
}
