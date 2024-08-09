@Deprecated
	public String getCommaSeparatedPatientIds() {
		return StringUtils.join(getMemberIds(), ',');
	}