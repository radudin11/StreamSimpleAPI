package project.domain;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

public abstract class Stream implements Comparable<Stream> {
    int streamType;
    int id;
    int streamGenre;
    long noOfStreams;
    int streamerId;
    long length;
    long dateAdded;
    String name;

    public Stream(int streamType, int id, int streamGenre, long noOfStreams, int streamerId, long length, long dateAdded, String name) {
        this.streamType = streamType;
        this.id = id;
        this.streamGenre = streamGenre;
        this.noOfStreams = noOfStreams;
        this.streamerId = streamerId;
        this.length = length;
        this.dateAdded = dateAdded;
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getStreamType() {
        return streamType;
    }

    public void setStreamType(int streamType) {
        this.streamType = streamType;
    }

    public int getStreamGenre() {
        return streamGenre;
    }

    public void setStreamGenre(int streamGenre) {
        this.streamGenre = streamGenre;
    }

    public long getNoOfStreams() {
        return noOfStreams;
    }

    public void setNoOfStreams(long noOfStreams) {
        this.noOfStreams = noOfStreams;
    }

    public int getStreamerId() {
        return streamerId;
    }

    public void setStreamerId(int streamerId) {
        this.streamerId = streamerId;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public long getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(long dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "\"" + streamType + "\",\"" + id + "\",\"" + streamGenre + "\",\"" + noOfStreams + "\",\"" +
                streamerId + "\",\"" + length + "\",\"" + dateAdded + "\"" + ",\"" + name + "\"";
    }

    public String toJson(String streamerName) {
        return "{" +
                "\"id\":\"" + id + "\"" +
                ",\"name\":\"" + name + "\"" +
                ",\"streamerName\":\"" + streamerName + "\"" +
                ",\"noOfListenings\":\"" + noOfStreams + "\"" +
                ",\"length\":\"" + calculateLength(length) + "\"" +
                ",\"dateAdded\":\"" + calculateDate(dateAdded) + "\"" +
                "}";
    }

    public static String calculateLength(long length) {
        long hours = length / 3600;
        long minutes = (length % 3600) / 60;
        long seconds = length % 60;
        if (hours == 0) {
            return String.format("%02d:%02d", minutes, seconds);
        }
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public static String calculateDate(long dateAdded) {
        Date date = new Date(dateAdded * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        return sdf.format(date);
    }

    public void stream() {
        noOfStreams++;
    }

    public int compareTo(Stream stream) {
        if (this.dateAdded > stream.dateAdded) {
            return -1;
        } else if (this.dateAdded < stream.dateAdded) {
            return 1;
        } else {
            return this.noOfStreams > stream.noOfStreams ? -1 : 1;
        }
    }
}


