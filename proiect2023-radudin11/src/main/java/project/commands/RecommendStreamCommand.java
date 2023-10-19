package project.commands;

import project.ProiectPOO;
import project.domain.Stream;
import project.domain.StreamType;

import java.util.ArrayList;
import java.util.List;

public class RecommendStreamCommand extends Command {

    public RecommendStreamCommand(ProiectPOO proiect) {
        super(proiect);
    }

    @Override
    public void execute(String[] params) {
        // Recommend 5 streams to a user
        if (params.length != 3) {
            System.out.println("Usage: <userId> RECOMMEND <streamType>");
            return;
        }

        int id = Integer.parseInt(params[0]);
        StreamType streamType = StreamType.valueOf(params[2]);

        // Find streamers listened
        List<Integer> streamersListenedTo = proiect.getStreamersListenedTo(id, streamType);
        if (streamersListenedTo.isEmpty()) {
            System.out.println("No streamers listened to");
            return;
        }

        List<Stream> recommendedStreams = getRecommendedStreams(id, streamersListenedTo);

        if (recommendedStreams.isEmpty()) {
            System.out.println("No recommended streams");
            return;
        }

        if (recommendedStreams.size() > 5) {
            recommendedStreams = recommendedStreams.subList(0, 5);
        }

        proiect.printStreamsAsJson(recommendedStreams);
    }

    private List<Stream> getRecommendedStreams(int id, List<Integer> streamersListenedTo) {
        // Algorithm to find recommended streams
        List<Stream> recommendedStreams = new ArrayList<>();

        // Get all streams from streamers listened to
        for (int streamerId : streamersListenedTo) {
            recommendedStreams.addAll(proiect.getStreamsByStreamerId(streamerId));
        }

        // Remove streams already listened to
        for (int streamId : proiect.getUserById(id).getStreams()) {
            recommendedStreams.removeIf(stream -> stream.getId() == streamId);
        }

        // Sort by number of streams
        recommendedStreams.sort((stream1, stream2) -> (int)(stream2.getNoOfStreams() - stream1.getNoOfStreams()));

        return recommendedStreams;
    }
}
