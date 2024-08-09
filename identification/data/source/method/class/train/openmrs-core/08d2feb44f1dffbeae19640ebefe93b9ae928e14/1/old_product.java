public boolean isDiscontinued(Date checkDate) {
		if (isVoided())
			return false;
		if (checkDate == null)
			checkDate = new Date();
		
		if (dateActivated == null || checkDate.before(dateActivated)) {
			return false;
		}
		if (dateStopped != null && dateStopped.after(checkDate)) {
			return false;
		}
		if (dateStopped == null) {
			return false;
		}
		
		// guess we can't assume this has been filled correctly?
		/*
		 * if (dateStopped == null) { return false; }
		 */
		return true;
	}