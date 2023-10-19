package project.domain;

public enum StreamType {
    SONG, PODCAST, AUDIOBOOK;

    public static int toInt(StreamType streamType) {
        switch (streamType) {
            case SONG:
                return 1;
            case PODCAST:
                return 2;
            case AUDIOBOOK:
                return 3;
            default:
                return 0;
        }
    }

    public static StreamType toStreamType(int streamType) {
        switch (streamType) {
            case 1:
                return SONG;
            case 2:
                return PODCAST;
            case 3:
                return AUDIOBOOK;
            default:
                return null;
        }
    }

    public int toInt() {
        return toInt(this);
    }

    public String toString() {
        switch(this) {
            case SONG: return "Song";
            case PODCAST: return "Podcast";
            case AUDIOBOOK: return "Audiobook";
            default: return "Unknown";
        }
    }
}
