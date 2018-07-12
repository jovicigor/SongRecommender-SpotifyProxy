package rs.fon.inteligentni.model;

import java.util.ArrayList;
import java.util.List;


public class FullTrack {
	
	// TRACK-INFO
	private String id;
	private String album;
	private Integer albumYear;
	private Integer duration;
	private String name;
	private Integer popularity;
	private String previewUrl;
	
	private List<FullArtist> artists = new ArrayList<>();

	// AUDIO-INFO
	private Float danceability;
	private Float energy;
	private Integer key;
	private Float loudness;
	private Integer mode;
	private Float speechiness;
	private Float acousticness;
	private Float instrumentalness;
	private Float liveness;
	private Float valence;
	private Float tempo;
	private Integer duration_ms;
	private Integer time_signature;

	public FullTrack() {

	}

	public Float getDanceability() {
		return danceability;
	}	

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}		

	public Integer getAlbumYear() {
		return albumYear;
	}

	public void setAlbumYear(Integer albumYear) {
		this.albumYear = albumYear;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPopularity() {
		return popularity;
	}

	public void setPopularity(Integer popularity) {
		this.popularity = popularity;
	}

	public String getPreviewUrl() {
		return previewUrl;
	}

	public void setPreviewUrl(String previewUrl) {
		this.previewUrl = previewUrl;
	}

	public void setDanceability(Float danceability) {
		this.danceability = danceability;
	}

	public Float getEnergy() {
		return energy;
	}

	public void setEnergy(Float energy) {
		this.energy = energy;
	}

	public Integer getKey() {
		return key;
	}

	public void setKey(Integer key) {
		this.key = key;
	}

	public Float getLoudness() {
		return loudness;
	}

	public void setLoudness(Float loudness) {
		this.loudness = loudness;
	}

	public Integer getMode() {
		return mode;
	}

	public void setMode(Integer mode) {
		this.mode = mode;
	}

	public Float getSpeechiness() {
		return speechiness;
	}

	public void setSpeechiness(Float speechiness) {
		this.speechiness = speechiness;
	}

	public Float getAcousticness() {
		return acousticness;
	}

	public void setAcousticness(Float acousticness) {
		this.acousticness = acousticness;
	}

	public Float getInstrumentalness() {
		return instrumentalness;
	}

	public void setInstrumentalness(Float instrumentalness) {
		this.instrumentalness = instrumentalness;
	}

	public Float getLiveness() {
		return liveness;
	}

	public void setLiveness(Float liveness) {
		this.liveness = liveness;
	}

	public Float getValence() {
		return valence;
	}

	public void setValence(Float valence) {
		this.valence = valence;
	}

	public Float getTempo() {
		return tempo;
	}

	public void setTempo(Float tempo) {
		this.tempo = tempo;
	}

	public String getId() {
		return id;
	}

	public List<FullArtist> getArtists() {
		return artists;
	}

	public void setArtists(List<FullArtist> myArtists) {
		this.artists = myArtists;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public Integer getDuration_ms() {
		return duration_ms;
	}

	public void setDuration_ms(Integer duration_ms) {
		this.duration_ms = duration_ms;
	}

	public Integer getTime_signature() {
		return time_signature;
	}

	public void setTime_signature(Integer time_signature) {
		this.time_signature = time_signature;
	}

	@Override
	public String toString() {
		return "FullTrack [id=" + id + ", album=" + album + ", artists="
				+ artists + ", duration=" + duration + ", name=" + name
				+ ", popularity=" + popularity + ", previewUrl=" + previewUrl
				+ ", danceability=" + danceability + ", energy=" + energy
				+ ", key=" + key + ", loudness=" + loudness + ", mode=" + mode
				+ ", speechiness=" + speechiness + ", acousticness="
				+ acousticness + ", instrumentalness=" + instrumentalness
				+ ", liveness=" + liveness + ", valence=" + valence
				+ ", tempo=" + tempo + ", duration_ms=" + duration_ms
				+ ", time_signature=" + time_signature + "]";
	}	

}
