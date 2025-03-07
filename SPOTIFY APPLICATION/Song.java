public class Song {
    private String trackName;
    private String artistName;
    private String imageurl;
    private String songurl;
    public Song(String trackName, String artistName, String imageurl, String songurl) {
        this.trackName = trackName;
        this.artistName = artistName;
        this.imageurl = imageurl;
        this.songurl = songurl;
    }

    public String getTrackName() {
        return trackName;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getImageUrl(){
        return imageurl;
    }
    public String getSongUrl(){
        return songurl;
    }
    @Override
    public String toString() {
        return trackName + " - " + artistName + " - " +imageurl + " - " + songurl;
    }
}
