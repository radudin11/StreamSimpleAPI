package project;

import project.commands.*;
import project.domain.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ProiectPOO {

    private List<User> users = new ArrayList<>();
    private List<Stream> streams = new ArrayList<>();
    private List<Streamer> streamers = new ArrayList<>();

    private ProiectPOO() {
    }

    private static ProiectPOO project = null;

    public static ProiectPOO getInstance() {
        if (project == null) {
            project = new ProiectPOO();
        }
        return project;
    }

    public static void main(String[] args) {
        ProiectPOO project = ProiectPOO.getInstance();
        project.clearData();

        if (args == null || args.length < 4) {
            System.out.println("Nothing to read here");
            return;
        }

        String resourcesFolder = "src/main/resources/";
        String streamersFile = args[0];
        String streamsFile = args[1];
        String usersFile = args[2];
        String commandsFile = args[3];
        try {
            project.readStreamers(resourcesFolder + streamersFile);
            project.readStreams(resourcesFolder + streamsFile);
            project.readUsers(resourcesFolder + usersFile);
            // project.countStreams();
            project.executeCommands(resourcesFolder + commandsFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void clearData() {
        users = new ArrayList<>();
        streams = new ArrayList<>();
        streamers = new ArrayList<>();
    }

    private void countStreams() {
        for (User user : users) {
            for (int streamId : user.getStreams()) {
               Stream stream = getStreamById(streamId);
                if (stream != null) {
                     stream.stream();
                }
            }
        }
    }

    private void executeCommands(String commandsFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(commandsFile));

        String line = reader.readLine();
        while (line != null) {
            String[] tokens = line.split(" ");

            if (tokens.length < 2) {
                System.out.println("Invalid command: " + line);
                line = reader.readLine();
                continue;
            }

            switch (tokens[1]) {  // command name
                case "LIST":
                    Command listStreams = new ListStreamsCommand(this);
                    listStreams.execute(tokens);
                    break;
                case "ADD":
                    Command addStream = new AddStreamCommand(this);
                    addStream.execute(tokens);
                    break;
                case "DELETE":
                    Command deleteStream = new DeleteStreamCommand(this);
                    deleteStream.execute(tokens);
                    break;
                case "LISTEN":
                    Command listenStream = new ListenCommand(this);
                    listenStream.execute(tokens);
                    break;
                case "RECOMMEND":
                    Command recommendStream = new RecommendStreamCommand(this);
                    recommendStream.execute(tokens);
                    break;
                case "SURPRISE":
                    Command surpriseStream = new SurpriseCommand(this);
                    surpriseStream.execute(tokens);
                    break;
                default:
                    System.out.println("Nothing to read here");
            }
            line = reader.readLine();
        }
        reader.close();
    }
    public User getUserById(int userId) {
        for (User user : users) {
            if (user.getId() == userId) {
                return user;
            }
        }
        return null;
    }

    public void removeStreamFromHistory(int streamId) {
        for (User user : users) {
            user.removeStreamFromHistory(streamId);
        }
    }

    public Stream getStreamById(int streamId) {
        for (Stream stream : streams) {
            if (stream.getId() == streamId) {
                return stream;
            }
        }
        return null;
    }

    public boolean checkIfStreamExists(int id, int streamerId, String name) {
        for (Stream stream : streams) {
            if (stream.getId() == id || (stream.getStreamerId() == streamerId && stream.getName().equals(name))) {
                return true;
            }
        }
        return false;
    }

    public Boolean checkIfStreamerExists(int streamerId) {
        return streamers.stream()
                .anyMatch(s -> s.getId() == streamerId);
    }

    public Streamer getStreamerById(int id) {
        return streamers.stream().filter(s -> s.getId() == id).findFirst().orElse(null);
    }

    public void printStreamsAsJson(List<Stream> streams) {
        StringBuilder json = new StringBuilder();
        json.append("[");
        for (Stream stream : streams) {
            Streamer streamer = getStreamerById(stream.getStreamerId());
            if (streamer == null) {
                System.out.println("Streamer not found");
                return;
            }

            json.append(stream.toJson(streamer.getName()));
            json.append(",");
        }
        json.deleteCharAt(json.length() - 1);
        json.append("]");
        System.out.println(json.toString());
    }

    private void readStreamers(String streamersFile) throws IOException {
        // TODO
        // read streamers from csv file
        // format: "streamerType","id","name"
        BufferedReader reader = new BufferedReader(new FileReader(streamersFile));
        reader.readLine();  // read header
        String line = reader.readLine().replace("\"", "");
        StreamerFactory factory = StreamerFactory.getInstance();

        while (line != null) {
            String[] tokens = line.split(",");

            int id = Integer.parseInt(tokens[1]);
            String name = tokens[2];
            int streamerType = Integer.parseInt(tokens[0]);

            Streamer streamer = factory.createStreamer(id, name, streamerType);
            streamers.add(streamer);

            line = reader.readLine();
            if (line != null) {
                line = line.replace("\"", "");
            }
        }

        reader.close();
    }

    private void writeStreamers(String streamersFile) throws IOException {
        // TODO
        // write streamers to csv file
        // format: "streamerType","id","name"
        FileWriter writer = new FileWriter(streamersFile);
        writer.write("streamerType,id,name\n");
        for (Streamer streamer : streamers) {
            writer.write(streamer.toString() + "\n");
        }
        writer.close();
    }

    private void readStreams(String streamsFile) throws IOException {
        // TODO
        // read streams from csv file
        // format: "streamType","id","streamGenre","noOfStreams","streamerId","length","dateAdded","name"

        BufferedReader reader = new BufferedReader(new FileReader(streamsFile));
        reader.readLine();  // read header
        String line = reader.readLine().replace("\"", "");

        GenericStreamBuilder builder = new GenericStreamBuilder();
        while (line != null) {
            String[] tokens = line.split(",");

            builder.setId(Integer.parseInt(tokens[1]))
                    .setStreamGenre(Integer.parseInt(tokens[2]))
                    .setNoOfStreams(Long.parseLong(tokens[3]))
                    .setStreamerId(Integer.parseInt(tokens[4]))
                    .setLength(Integer.parseInt(tokens[5]))
                    .setDateAdded(Long.parseLong(tokens[6]))
                    .setName(tokens[7]);

            // name can have multiple commas
            for (int i = 8; i < tokens.length; i++) {
                builder.setName(builder.getName() + "," + tokens[i]);
            }

            streams.add(builder.buildStream(Integer.parseInt(tokens[0])));

            line = reader.readLine();
            if (line != null) {
                line = line.replace("\"", "");
            }
        }

        // inverse streams list beacuse of tests
        //Collections.reverse(streams);

        reader.close();
    }


    private void writeStreams(String streamsFile) throws IOException {
        // TODO
        // write streams to csv file
        // format: "streamType","id","streamGenre","noOfStreams","streamerId","length","dateAdded","name"
        FileWriter writer = new FileWriter(streamsFile);
        writer.write("\"streamType\",\"id\",\"streamGenre\",\"noOfStreams\",\"streamerId\",\"length\",\"dateAdded\",\"name\"");
        for (Stream stream : streams) {
            writer.write(stream.toString() + "\n");
        }
        writer.close();
    }

    private void readUsers(String usersFile) throws IOException {
        // TODO
        // read users from csv file
        // format: "id","name","streams"

        BufferedReader reader = new BufferedReader(new FileReader(usersFile));
        String header = reader.readLine();

        String line = reader.readLine().replace("\"", "");
        while (line != null) {
            String[] tokens = line.split(",");
            int id = Integer.parseInt(tokens[0]);
            String name = tokens[1];
            List<Integer> streams = new ArrayList<>();
            for (String token : tokens[2].split(" ")) {
                streams.add(Integer.parseInt(token));
            }

            User user = new User(id, name, streams);
            users.add(user);
            line = reader.readLine();
            if (line != null) {
                line = line.replace("\"", "");
            }
        }

        reader.close();
    }

    private void writeUsers(String usersFile) throws IOException {
        // TODO
        // write users to csv file
        // format: "id","name","streams"

        FileWriter writer = new FileWriter(usersFile);

        writer.write("\"id\",\"name\",\"streams\"\n");
        for (User user : users) {
            writer.write(user.toString());
        }
        writer.close();
    }


    public List<Stream> getStreamsByStreamerId(int id) {
        return streams.stream()
                .filter(s -> s.getStreamerId() == id)
                .collect(Collectors.toList());
        // return streams.stream().filter(s -> s.getStreamerId() == id).toList();
    }

    public void addStream(Stream stream) {
        streams.add(stream);
    }

    public void removeStream(Stream stream) {
        streams.remove(stream);
    }

    public List<Integer> getStreamersListenedTo(int id, StreamType streamType) {
        User user = getUserById(id);
        if (user == null) {
            System.out.println("User not found");
            return null;
        }

        List<Integer> streamers = new ArrayList<>();
        for (int streamId : user.getStreams()) {
            // look for streams with matching type
            Stream stream = getStreamById(streamId);
            if (stream == null) {
                System.out.println("Stream not found");
                return null;
            }
            if (stream.getStreamType() == streamType.toInt()) {
                // add stream owner to list
                streamers.add(stream.getStreamerId());
            }
        }
        return streamers;
    }

    public List<Streamer> getStreamersNotListenedTo(int userId, StreamType streamType) {
        User user = getUserById(userId);
        if (user == null) {
            System.out.println("User not found");
            return null;
        }

        List<Streamer> streamersNotListened = new ArrayList<>();
        List<Integer> streamersListened = getStreamersListenedTo(userId, streamType);
        for (Streamer streamer : streamers) {
            // check if user has listened to streamer
            if (!streamersListened.contains(streamer.getId())) {
                // check if streamer has streams of type
                if (streamer.getStreamerType() == streamType.toInt()) {
                    streamersNotListened.add(streamer);
                }
            }
        }
        return streamersNotListened;
    }
}
