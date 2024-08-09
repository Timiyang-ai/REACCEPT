public boolean getActive(Date onDate) {
		if (onDate == null) {
			onDate = new Date();
		}
		return !getVoided() && (OpenmrsUtil.compareWithNullAsEarliest(startDate, onDate) <= 0)
		        && (OpenmrsUtil.compareWithNullAsLatest(endDate, onDate) > 0);
	}