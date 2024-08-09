public boolean isFuture(Date checkDate) {
		if (isVoided())
			return false;
		if (checkDate == null)
			checkDate = new Date();
		
		return dateActivated != null && checkDate.before(dateActivated);
	}