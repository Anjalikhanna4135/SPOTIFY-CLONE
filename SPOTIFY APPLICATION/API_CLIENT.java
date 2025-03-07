import java.io.IOException;
import java.net.URI;
import java.net.http.*;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class API_CLIENT {

    private static final String API_URL = "https://itunes.apple.com/search?term=neha+kakkar&limit=25";
    private List<Song> songsList = new ArrayList<>();

    public void fetchTrackNames() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create(API_URL)).GET().build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                System.out.println(response.body());
                processResponse(response.body());

                System.out.println("Song details successfully fetched and stored.");
            } else {
                System.err.println("Error: Failed to fetch data. Status code: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("An error occurred while sending the request: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void processResponse(String responseBody) {
        JSONObject dataObject = new JSONObject(responseBody);
        JSONArray dataArray = dataObject.getJSONArray("results");

        for (int i = 0; i < dataArray.length(); i++) {
            JSONObject singleSong = dataArray.getJSONObject(i);

            String trackName = singleSong.getString("trackName");
            String artistName = singleSong.getString("artistName"); 
            String imageurl = singleSong.getString("artworkUrl100");
            String songurl;
            if(singleSong.has("previewUrl") && !singleSong.isNull("previewUrl")){
            songurl = singleSong.getString("previewUrl");}
            else {
                songurl = "song not found";
            }
            songsList.add(new Song(trackName, artistName, imageurl, songurl ));
        }
    }

    public List<Song> getSongsList() {
        return songsList;
    }
}
