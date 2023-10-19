package project.domain;

public class AudioBook extends GenericStream {

    public AudioBook(int id, int streamGenre, long noOfStreams, int streamerId, long length, long dateAdded, String name) {
        super(StreamType.AUDIOBOOK.toInt(), id, streamGenre, noOfStreams, streamerId, length, dateAdded, name);
    }
}
