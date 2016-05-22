package rs.fon.inteligentni.model;

public class ReleaseDate {
	private String release_date;
	private String release_date_precision;

	public String getRelease_date() {
		return release_date;
	}

	public void setRelease_date(String release_date) {
		this.release_date = release_date;
	}

	public String getRelease_date_precision() {
		return release_date_precision;
	}

	public void setRelease_date_precision(String release_date_precision) {
		this.release_date_precision = release_date_precision;
	}

	@Override
	public String toString() {
		return "ReleaseDate [release_date=" + release_date
				+ ", release_date_precision=" + release_date_precision + "]";
	}

}
