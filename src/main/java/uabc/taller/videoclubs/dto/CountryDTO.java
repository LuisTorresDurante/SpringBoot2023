package uabc.taller.videoclubs.dto;

import java.util.Date;

public class CountryDTO {
	private Integer countryId;
	private String countryName;
	private Date lastUpdate;
	public CountryDTO(Integer countryId, String countryName, Date lastUpdate) {
		super();
		this.countryId = countryId;
		this.countryName = countryName;
		this.lastUpdate = lastUpdate;
	}
	
	public Integer getCountryId() {
		return countryId;
	}
	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
	@Override
	public String toString() {
		return "CountryDTO [countryId=" + countryId + ", countryName=" + countryName + ", lastUpdate=" + lastUpdate + "]";
	}
}
