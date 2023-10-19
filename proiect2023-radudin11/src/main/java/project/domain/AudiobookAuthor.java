package project.domain;

import project.domain.Streamer;
import project.domain.StreamerType;

public class AudiobookAuthor extends Streamer {
    public AudiobookAuthor(int id, String name) {
        super(id, name, StreamerType.toInt(StreamerType.AUDIOBOOK_AUTHOR));
    }
}
