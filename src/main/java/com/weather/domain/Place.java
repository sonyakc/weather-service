package com.weather.domain;

public class Place {
	private String woeid;

	public String getWoeid() {
		return woeid;
	}

	public void setWoeid(String woeid) {
		this.woeid = woeid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((woeid == null) ? 0 : woeid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Place other = (Place) obj;
		if (woeid == null) {
			if (other.woeid != null)
				return false;
		} else if (!woeid.equals(other.woeid))
			return false;
		return true;
	}
}
