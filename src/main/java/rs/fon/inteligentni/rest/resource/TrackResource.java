package rs.fon.inteligentni.rest.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rs.fon.inteligentni.model.FullTrack;
import rs.fon.inteligentni.spotifyapi.SpotifyApiManager;

@RestController
public class TrackResource {

	@Autowired
	SpotifyApiManager spotifyApiManager;
	
	
	@RequestMapping(value = "/tracks",method=RequestMethod.GET)
	public List<FullTrack> getTracksByNames(
			@RequestParam(value = "trackNames", required = true) List<String> trackNames) {
		return spotifyApiManager.getTracksByTrackNames(trackNames);
	}
}
