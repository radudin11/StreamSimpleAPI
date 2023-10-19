package project.domain;

import project.domain.Streamer;
import project.domain.StreamerType;

public class Musician extends Streamer {
    public Musician(int id, String name) {
        super(id, name, StreamerType.toInt(StreamerType.MUSICIAN));
    }
}
