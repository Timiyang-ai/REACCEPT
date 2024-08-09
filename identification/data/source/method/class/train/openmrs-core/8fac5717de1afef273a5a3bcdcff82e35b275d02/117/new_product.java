public boolean getActive(Date onDate) {
		if (onDate == null) {
			onDate = new Date();
		}
		return !getVoided() && (startDate == null || OpenmrsUtil.compare(startDate, onDate) <= 0) && (endDate == null || OpenmrsUtil.compare(endDate, onDate) > 0);
	}