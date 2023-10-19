# Simple API for a Streaming platform

## Scope: 
### Implementing a recomandation algorithm for streams (music, podcasts and audiobooks) based on data provided about listeners and creators

## Database:
### For simplicity, this project uses a local database consisting of 3 .CSV files in /src/main/resources/inputs

Streamer:
- streamerType,
- id,
- name

Stream:
- streamType
- id
- streamGenre
- noOfStreams
- streamerId
- length
- dateAdded
- name

User:
- id
- name
- streams


## Output:
### All output will be done to the console.

## Commands:

### Add stream:
<streamerId: Integer> ADD
<streamType: Integer>
<id: Integer>
<streamGenre: Integer>
<length: Long>
<name: String>

### List streamer streams:
<streamerId: Integer> LIST

### Delete stream:
<streamerId: Integer> DELETE
<streamId: Integer>

### List user history
<userId: Integer> LIST

### Listen to a stream
<userId: Integer> LISTEN
<streamId: Integer>

### Recommend 5 streams of chosen type
<userId: Integer> RECOMMEND 
[SONG | PODCAST | AUDIOBOOK]

### recommend 3 surprise streams
<userId: Integer> SURPRISE 
[SONG | PODCAST | AUDIOBOOK]

## Bonus
### I added a simple swing GUI for working with these simple commands

To run the GUI version, run the /src/main/java/simpleGUI/simpleSwingGui.java




