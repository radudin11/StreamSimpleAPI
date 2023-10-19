package project.domain;

public enum StreamerType {
    AUDIOBOOK_AUTHOR, PODCAST_HOST, MUSICIAN;

    public String toString() {
        switch(this) {
            case AUDIOBOOK_AUTHOR: return "Audiobook Author";
            case PODCAST_HOST: return "Podcast Host";
            case MUSICIAN: return "Musician";
            default: return "Unknown";
        }
    }

    public static StreamerType toStreamerType(int x) {
        switch(x) {
            case 0: return MUSICIAN;
            case 1: return PODCAST_HOST;
            case 2: return AUDIOBOOK_AUTHOR;
            default: return null;
        }
    }

    public static int toInt(StreamerType x) {
        switch(x) {
            case MUSICIAN: return 1;
            case PODCAST_HOST: return 2;
            case AUDIOBOOK_AUTHOR: return 3;
            default: return -1;
        }
    }

    public int toInt() {
        return toInt(this);
    }
}
