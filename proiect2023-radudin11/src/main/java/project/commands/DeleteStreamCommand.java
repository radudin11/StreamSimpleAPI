package project.commands;

import project.ProiectPOO;
import project.domain.Stream;
import project.domain.Streamer;

public class DeleteStreamCommand extends Command {
    public DeleteStreamCommand(ProiectPOO proiect) {
        super(proiect);
    }

    @Override
    public void execute(String[] params) {
        // Delete a stream from the list
        if (params.length < 3) {
            System.out.println("Usage: <streamerId> DELETE <streamId>");
            return;
        }

        int streamerId = Integer.parseInt(params[0]);
        int streamId = Integer.parseInt(params[2]);

        // Check if the streamer id is valid
        Streamer streamer = proiect.getStreamerById(streamerId);
        if (streamer == null) {
            System.out.println("Streamer with id " + streamerId + " does not exist");
            return;
        }

        // Check if the stream id is valid
        Stream stream = proiect.getStreamById(streamId);
        if (stream == null) {
            System.out.println("Stream with id " + streamId + " does not exist");
            return;
        }

        // Check if the streamer owns the stream
        if (stream.getStreamerId() != streamerId) {
            System.out.println("Stream with id " + streamId + " does not belong to streamer with id " + streamerId);
            return;
        }

        proiect.removeStream(stream);
        proiect.removeStreamFromHistory(streamId);
    }
}
