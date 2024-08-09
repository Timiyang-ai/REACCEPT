public boolean isStarted(Date aCheckDate) {
		if (getVoided()) {
			return false;
		}
		if (getEffectiveStartDate() == null) {
			return false;
		}
		Date checkDate = aCheckDate == null ? new Date() : aCheckDate;
		return !checkDate.before(getEffectiveStartDate());
	}