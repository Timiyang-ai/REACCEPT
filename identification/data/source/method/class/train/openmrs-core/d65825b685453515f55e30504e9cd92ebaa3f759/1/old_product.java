public boolean isActive(Date checkDate) {
		if (isVoided() || action == Action.DISCONTINUE) {
			return false;
		}
		if (checkDate == null) {
			checkDate = new Date();
		}
		
		return !isFuture(checkDate) && !isDiscontinued(checkDate) && !isExpired(checkDate);
	}