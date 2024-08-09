@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append("hashCode",
		    Integer.toHexString(hashCode())).append("uuid", getUuid()).build();
	}