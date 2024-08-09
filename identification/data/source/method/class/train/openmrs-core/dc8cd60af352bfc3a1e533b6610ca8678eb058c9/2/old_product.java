public boolean isActive(Date asOfDate) {
		Date date;
		if (asOfDate == null) {
			date = new Date();
		} else {
			date = asOfDate;
		}
		return !this.getVoided() && (date.equals(this.getStartDate()) || date.after(this.getStartDate()))
		        && (this.getEndDate() == null || date.before(this.getEndDate()));
	}