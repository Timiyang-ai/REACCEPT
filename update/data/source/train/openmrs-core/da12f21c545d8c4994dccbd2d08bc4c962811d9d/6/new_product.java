public boolean isDiscontinued(Date checkDate) {
		if (dateStopped != null && autoExpireDate != null && dateStopped.after(autoExpireDate)) {
			throw new APIException("Order.error.invalidDateStoppedAndAutoExpireDate", (Object[]) null);
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