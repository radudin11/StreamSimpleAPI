package project.commands;

import project.ProiectPOO;
import project.domain.Stream;
import project.domain.Streamer;
import project.domain.User;

import java.util.ArrayList;
import java.util.List;

public class ListStreamsCommand extends Command {
    public ListStreamsCommand(ProiectPOO proiect) {
        super(proiect);
    }

    @Override
    public void execute(String[] params) {
        // List all streams from a streamer or user
        int id = Integer.parseInt(params[0]);

        // Get either the streamer or the user
        User user = proiect.getUserById(id);
        Streamer streamer = proiect.getStreamerById(id);

        if (user != null) {
            // list streams for user
            List<Stream> userStreams = new ArrayList<>();
            for (int streamId : user.getStreams()) {
                // get stream from streamId
                Stream stream = proiect.getStreamById(streamId);
                if (stream != null) {
                    userStreams.add(stream);
                }
            }
            proiect.printStreamsAsJson(userStreams);
        } else {
            // list streams for streamer
            if ( streamer != null) {
                // find all streams for streamer
                List<Stream> streamerStreams = proiect.getStreamsByStreamerId(id);

                proiect.printStreamsAsJson(streamerStreams);
            } else {
                System.out.println("ID not found");
            }
        }
    }
}
