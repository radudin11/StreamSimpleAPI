package project.domain;

import project.domain.Streamer;
import project.domain.StreamerType;

public class PodcastHost extends Streamer {
    public PodcastHost(int id, String name) {
        super(id, name, StreamerType.toInt(StreamerType.PODCAST_HOST));
    }
}
