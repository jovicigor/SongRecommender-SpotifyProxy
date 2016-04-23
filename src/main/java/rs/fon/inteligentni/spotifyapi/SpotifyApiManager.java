package rs.fon.inteligentni.spotifyapi;

import java.util.List;

import rs.fon.inteligentni.model.FullTrack;

public interface SpotifyApiManager {

	public abstract FullTrack getTrackByName(String name);
	public abstract List<FullTrack> getTracksByTrackNames(List<String> names);

}