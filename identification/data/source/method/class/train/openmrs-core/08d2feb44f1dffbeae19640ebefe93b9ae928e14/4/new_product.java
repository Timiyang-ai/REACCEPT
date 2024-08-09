public boolean isDiscontinued(Date aCheckDate) {
		if (dateStopped != null && autoExpireDate != null && dateStopped.after(autoExpireDate)) {
			throw new APIException("Order.error.invalidDateStoppedAndAutoExpireDate", (Object[]) null);
		}
		if (getVoided()) {
			return false;
		}
		Date checkDate = aCheckDate == null ? new Date() : aCheckDate;
		if (!isActivated(checkDate) || dateStopped == null) {
			return false;
		}
		return checkDate.after(dateStopped);
	}