package rs.fon.inteligentni.spotifyapi;

import com.wrapper.spotify.Api;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.methods.TrackRequest;
import com.wrapper.spotify.methods.TrackSearchRequest;
import com.wrapper.spotify.methods.authentication.ClientCredentialsGrantRequest;
import com.wrapper.spotify.models.ClientCredentials;
import com.wrapper.spotify.models.Page;
import com.wrapper.spotify.models.SimpleArtist;
import com.wrapper.spotify.models.Track;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import rs.fon.inteligentni.model.FullArtists;
import rs.fon.inteligentni.model.FullTrack;
import rs.fon.inteligentni.model.ReleaseDate;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class SpotifyApiManager {

    @Value("${spotify.api.clientId}")
    private String clientId;
    @Value("${spotify.api.clientSecret}")
    private String clientSecret;

    private Api api;
    private ClientCredentials clientCredentials;

    private static final Logger logger = LoggerFactory.getLogger(SpotifyApiManager.class);

    private void authorize() {
        logger.info("Going to Authorize on Spotify.");
        api = Api.builder().clientId(clientId).clientSecret(clientSecret)
                .build();

        /* Create a request object. */
        ClientCredentialsGrantRequest request = api.clientCredentialsGrant()
                .build();
        /*
         * Use the request object to make the request, either asynchronously
         * (getAsync) or synchronously (get)
         */
        try {
            clientCredentials = request.get();
        } catch (IOException | WebApiException e) {
            e.printStackTrace();
            throw new SpotifyProxyRuntimeException(e.getMessage());
        }

        logger.debug("Successfully retrieved an access token! "
                + clientCredentials.getAccessToken());
        logger.debug("The access token expires in "
                + clientCredentials.getExpiresIn() + " seconds");

        /*
         * Set access token on the Api object so that it's used going forward
         */
        String accessToken = clientCredentials.getAccessToken();
        logger.debug("Access token: " + accessToken);
        api.setAccessToken(accessToken);
        logger.info("Spotify authorization successful");
    }

    private FullTrack convertToFullTrack(Track track) {
        String audioInfouri = "https://api.spotify.com/v1/audio-features/"
                + track.getId();

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",
                "Bearer " + clientCredentials.getAccessToken());
        HttpEntity<String> entity = new HttpEntity<>("parameters",
                headers);

        ResponseEntity<FullTrack> result = restTemplate.exchange(audioInfouri,
                HttpMethod.GET, entity, FullTrack.class);
        FullTrack fullTrack = result.getBody();

        if (track.getAlbum() != null) {
            fullTrack.setAlbum(track.getAlbum().getName());
        }
        fullTrack.setDuration(track.getDuration());
        fullTrack.setId(track.getId());
        fullTrack.setName(track.getName());
        fullTrack.setPopularity(track.getPopularity());
        fullTrack.setPreviewUrl(track.getPreviewUrl());

        List<SimpleArtist> simpleArtists = track.getArtists();
        if (!simpleArtists.isEmpty()) {
            StringBuilder artistsIds = new StringBuilder();
            for (int i = 0; i < simpleArtists.size(); i++) {
                if (i != 0) {
                    artistsIds.append(",");
                }
                artistsIds.append(simpleArtists.get(i).getId());
            }
            final String artistsUri = "https://api.spotify.com/v1/artists/?ids="
                    + artistsIds;
            ResponseEntity<FullArtists> resultArtists = restTemplate.exchange(
                    artistsUri, HttpMethod.GET, entity, FullArtists.class);
            FullArtists fullArtists = resultArtists.getBody();
            fullTrack.setArtists(fullArtists.getArtists());

        }

        if (track.getAlbum() != null && track.getAlbum().getHref() != null) {
            logger.debug("Album is not null, href is not null: " + track.getAlbum().getName() + ", href: " + track.getAlbum().getHref());
            ResponseEntity<ReleaseDate> resultReleaseDate = restTemplate.exchange(track.getAlbum().getHref(), HttpMethod.GET, entity, ReleaseDate.class);
            ReleaseDate releaseDateObj = resultReleaseDate.getBody();
            logger.debug("Received release date: " + releaseDateObj);
            try {
                Integer year = Integer.parseInt(releaseDateObj.getRelease_date().substring(0, 4));
                fullTrack.setAlbumYear(year);
            } catch (Exception e) {
                logger.error("Failed to parse year from release date", e);
            }
        }

        return fullTrack;
    }


    public Optional<FullTrack> getTrackByName(String name) {
        try {
            if (api == null) {
                authorize();
            }

            TrackSearchRequest request = api.searchTracks(name).build();
            Page<Track> trackSearchResult = request.get();

            logger.debug(String.format("Got %d results for %s", trackSearchResult.getTotal(), name));

            return trackSearchResult.getItems().stream()
                    .findFirst()
                    .map(this::convertToFullTrack);
        } catch (IOException | WebApiException e) {
            logger.error(String.format("Error retreiving song from Spotify: %s", name));
            logger.error(e.getLocalizedMessage());
            return Optional.empty();
        }
    }

    public Optional<FullTrack> getTrackById(String id) {
        try {
            if (api == null) {
                authorize();
            }

            TrackRequest request = api.getTrack(id).build();

            return Optional.ofNullable(request.get())
                    .map(this::convertToFullTrack);
        } catch (IOException | WebApiException e) {
            logger.error(String.format("Error retreiving song from Spotify: %s", id));
            logger.error(e.getLocalizedMessage());
            return Optional.empty();
        }
    }
}
