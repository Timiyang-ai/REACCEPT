public boolean isDiscontinued(Date checkDate) {
		if (voided)
			return false;
		if (checkDate == null)
			checkDate = new Date();
		
		if (discontinued == null || !discontinued)
			return false;
		
		if (startDate == null || checkDate.before(startDate)) {
			return false;
		}
		if (discontinuedDate != null && discontinuedDate.after(checkDate)) {
			return false;
		}
		
		// guess we can't assume this has been filled correctly?
		/*
		if (discontinuedDate == null) { 
			return false; 
		} 
		*/
		return true;
	}