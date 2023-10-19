package project.commands;

import project.domain.GenericStreamBuilder;
import project.ProiectPOO;
import project.domain.Stream;

import java.time.LocalDate;
import java.time.ZoneOffset;

public class AddStreamCommand extends Command {
    public AddStreamCommand(ProiectPOO proiect) {
        super(proiect);
    }

    @Override
    public void execute(String[] params) {
        // Create and add an unique stream to the list
        GenericStreamBuilder streamBuilder = new GenericStreamBuilder();

        if (params.length < 7) {
            System.out.println("Usage: <streamerId:Integer> ADD " +
                    "<streamType: Integer> " +
                    "<id: Integer> " +
                    "<streamGenre: Integer> " +
                    "<length: Long> " +
                    "<name:String>");
            return;
        }

        // Check if the streamer id is valid
        if (!Boolean.TRUE.equals(proiect.checkIfStreamerExists(Integer.parseInt(params[0])))) {
            System.out.println("Streamer with id " + params[0] + " does not exist");
            return;
        }

        // Get date of the added stream

        // Date is 13/01/2023 as that is the one given in refference. In practice, it should be the current date
        LocalDate date = LocalDate.of(2023, 1, 13);  // given in reference file
        // LocalDate date = LocalDate.now();

        long epochSecond = date.atStartOfDay().toEpochSecond(ZoneOffset.UTC);

        // Build the stream
        streamBuilder
                .setStreamerId(Integer.parseInt(params[0]))
                .setId(Integer.parseInt(params[3]))
                .setStreamGenre(Integer.parseInt(params[4]))
                .setLength(Long.parseLong(params[5]))
                .setDateAdded(epochSecond);

        // name could be multiple words
        StringBuilder name = new StringBuilder();
        for (int i = 6; i < params.length; i++) {
            name.append(params[i]).append(" ");
        }
        streamBuilder.setName(name.toString().trim());

        Stream stream = streamBuilder.buildStream(Integer.parseInt(params[2]));

        if (stream == null) {
            System.out.println("Error, invalid stream type");
            return;
        }

        // check if stream already exists
        if (proiect.checkIfStreamExists(stream.getId(), stream.getStreamerId(), stream.getName())) {
            System.out.println("Stream with id " + stream.getId() + " already exists");
            return;
        }

        proiect.addStream(stream);

    }
}
