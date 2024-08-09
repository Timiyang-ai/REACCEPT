public boolean isExpired(Date aCheckDate) {
		if (dateStopped != null && autoExpireDate != null && dateStopped.after(autoExpireDate)) {
			throw new APIException("Order.error.invalidDateStoppedAndAutoExpireDate", (Object[]) null);
		}
		if (getVoided()) {
			return false;
		}
		Date checkDate = aCheckDate == null ? new Date() : aCheckDate;
		if (!isActivated(checkDate)) {
			return false;
		}
		if (isDiscontinued(checkDate) || autoExpireDate == null) {
			return false;
		}

		return checkDate.after(autoExpireDate);
	}