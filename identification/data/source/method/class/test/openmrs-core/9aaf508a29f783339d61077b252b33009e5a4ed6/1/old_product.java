public boolean isStarted(Date checkDate) {
		if (isVoided()) {
			return false;
		}
		if (checkDate == null) {
			checkDate = new Date();
		}
		if (getEffectiveStartDate() == null) {
			return false;
		}
		return !checkDate.before(getEffectiveStartDate());
	}