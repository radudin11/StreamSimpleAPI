package project.domain;

public class GenericStreamBuilder {
    protected GenericStream genericStream;

    public GenericStreamBuilder() {
        genericStream = new GenericStream();
    }

    public GenericStreamBuilder setId(int id) {
        genericStream.setId(id);
        return this;
    }

    public GenericStreamBuilder setStreamGenre(int streamGenre) {
        genericStream.setStreamGenre(streamGenre);
        return this;
    }

    public GenericStreamBuilder setNoOfStreams(long noOfStreams) {
        genericStream.setNoOfStreams(noOfStreams);
        return this;
    }

    public GenericStreamBuilder setStreamerId(int streamerId) {
        genericStream.setStreamerId(streamerId);
        return this;
    }

    public GenericStreamBuilder setLength(long length) {
        genericStream.setLength(length);
        return this;
    }

    public GenericStreamBuilder setDateAdded(long dateAdded) {
        genericStream.setDateAdded(dateAdded);
        return this;
    }

    public GenericStreamBuilder setName(String name) {
        genericStream.setName(name);
        return this;
    }

    public Song buildSong() {
        return new Song(genericStream.getId(), genericStream.getStreamGenre(), genericStream.getNoOfStreams(),
                genericStream.getStreamerId(), genericStream.getLength(), genericStream.getDateAdded(), genericStream.getName());
    }

    public AudioBook buildAudioBook() {
        return new AudioBook(genericStream.getId(), genericStream.getStreamGenre(), genericStream.getNoOfStreams(),
                genericStream.getStreamerId(), genericStream.getLength(), genericStream.getDateAdded(), genericStream.getName());
    }

    public Podcast buildPodcast() {
        return new Podcast(genericStream.getId(), genericStream.getStreamGenre(), genericStream.getNoOfStreams(),
                genericStream.getStreamerId(), genericStream.getLength(), genericStream.getDateAdded(), genericStream.getName());
    }

    public GenericStream build() {
        return this.genericStream;
    }


    public String getName() {
        return genericStream.getName();
    }


    public Stream buildStream(int streamType) {
        switch (streamType) {
            case 1:
                return buildSong();
            case 2:
                return buildPodcast();
            case 3:
                return buildAudioBook();
            default:
                return null;
        }
    }

}

