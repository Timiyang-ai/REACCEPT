public boolean isActive(Date asOfDate) {
		Date date = asOfDate == null ? new Date() : asOfDate;
		return !this.getVoided() && OpenmrsUtil.compareWithNullAsEarliest(startDate, date) <= 0
				&& OpenmrsUtil.compareWithNullAsLatest(date, endDate) <= 0;
	}