public boolean isActive(Date aCheckDate) {
		if (getVoided() || action == Action.DISCONTINUE) {
			return false;
		}
		Date checkDate = aCheckDate == null ? new Date() : aCheckDate;
		return isActivated(checkDate) && !isDiscontinued(checkDate) && !isExpired(checkDate);
	}