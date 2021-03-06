package rs.fon.inteligentni.rest.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.fon.inteligentni.model.FullTrack;
import rs.fon.inteligentni.spotifyapi.SpotifyApiManager;

@RestController
@RequestMapping(value = "/tracks")
public class TrackResource {

    private final SpotifyApiManager spotifyApiManager;

    @Autowired
    public TrackResource(SpotifyApiManager spotifyApiManager) {
        this.spotifyApiManager = spotifyApiManager;
    }

    @GetMapping("/search")
    //    query = track:is%20this%20love%20artist:whitesnake
    public ResponseEntity<FullTrack> getTracksByQuery(@RequestParam(value = "query") String query) {
        return spotifyApiManager.getTrackByName(query)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/{trackId}")
    public ResponseEntity<FullTrack> getTrackById(@PathVariable("trackId") String trackId) {
        return spotifyApiManager.getTrackById(trackId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
