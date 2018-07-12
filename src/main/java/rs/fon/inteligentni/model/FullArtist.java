package rs.fon.inteligentni.model;

import java.util.List;

class FullArtist {
	
	private List<String> genres;
	private String id;
	private String name;
	private Integer popularity;

	public List<String> getGenres() {
		return genres;
	}	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "FullArtist [genres=" + genres + ", id=" + id + ", name=" + name
				+ ", popularity=" + popularity + "]";
	}

	
	

}
