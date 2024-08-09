public boolean isDiscontinued(Date checkDate) {
		if (dateStopped != null && autoExpireDate != null && dateStopped.after(autoExpireDate)) {
			throw new APIException("The order has invalid dateStopped and autoExpireDate values");
		}
		if (isVoided()) {
			return false;
		}
		if (checkDate == null) {
			checkDate = new Date();
		}
		if (dateActivated == null || isFuture(checkDate) || dateStopped == null) {
			return false;
		}
		return checkDate.after(dateStopped);
	}