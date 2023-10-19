package project.domain;

import project.domain.Stream;

/*
 * Concrete implementation of the Stream class used for builder pattern
 */
public class GenericStream extends Stream {
    public GenericStream(int streamType, int id, int streamGenre, long noOfStreams, int streamerId, long length, long dateAdded, String name) {
        super(streamType, id, streamGenre, noOfStreams, streamerId, length, dateAdded, name);
    }

    public GenericStream() {
        super(0, 0, 0, 0, 0, 0, 0, "");
    }

    public void setStreamType(int streamType) {
        this.streamType = streamType;
    }
}
