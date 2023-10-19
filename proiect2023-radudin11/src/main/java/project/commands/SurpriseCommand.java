package project.commands;

import project.ProiectPOO;
import project.domain.Stream;
import project.domain.StreamType;
import project.domain.Streamer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SurpriseCommand extends Command {
    public SurpriseCommand(ProiectPOO proiect) {
        super(proiect);
    }

    @Override
    public void execute(String[] params) {
        // Give 3 streams to the user based on the streamType given
        if (params.length != 3) {
            System.out.println("Usage: <userId> SURPRISE <streamType>");
            return;
        }

        int userId = Integer.parseInt(params[0]);
        StreamType streamType = StreamType.valueOf(params[2]);

        // Get all streamers that stream the given streamType and the user hasn't listened to
        List<Streamer> streamersNotListenedTo = proiect.getStreamersNotListenedTo(userId, streamType);

        if (streamersNotListenedTo.isEmpty()) {
            System.out.println("No new streamers to listen to");
            return;
        }

        // Apply algorithm
        List<Stream> surpriseStreams = getSurpriseStreams(userId, streamersNotListenedTo);

        if (surpriseStreams.isEmpty()) {
            System.out.println("No surprise streams");
            return;
        }

        if (surpriseStreams.size() > 3) {
            surpriseStreams = surpriseStreams.subList(0, 3);
        }

        proiect.printStreamsAsJson(surpriseStreams);
    }

    private List<Stream> getSurpriseStreams(int userId, List<Streamer> streamersNotListenedTo) {
        // Algorithm to find surprise streams
        List<Stream> surpriseStreams = new ArrayList<>();

        for (Streamer streamer : streamersNotListenedTo) {
            surpriseStreams.addAll(proiect.getStreamsByStreamerId(streamer.getId()));
        }

        surpriseStreams.sort(Stream::compareTo);

        return surpriseStreams;
    }
}
