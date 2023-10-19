package project.commands;

import project.ProiectPOO;
import project.domain.Stream;
import project.domain.User;

public class ListenCommand extends Command {
    public ListenCommand(ProiectPOO proiect) {
        super(proiect);
    }

    @Override
    public void execute(String[] params) {
        // Listen command executed when a user listens to a stream
        if (params.length < 3) {
            System.out.println("Usage: <userId> LISTEN <streamId>");
        }

        int userId = Integer.parseInt(params[0]);
        int streamId = Integer.parseInt(params[2]);

        // Check if the user id is valid
        User user = proiect.getUserById(userId);
        if (user == null) {
            System.out.println("User not found");
            return;
        }

        // Check if the stream id is valid
        Stream stream = proiect.getStreamById(streamId);
        if (stream == null) {
            System.out.println("Stream not found");
            return;
        }

        user.addStream(streamId);
        stream.stream();
    }
}
