public boolean isExpired(Date checkDate) {
		if (dateStopped != null && autoExpireDate != null && dateStopped.after(autoExpireDate)) {
			throw new APIException("Order.error.invalidDateStoppedAndAutoExpireDate", (Object[]) null);
		}
		if (isVoided()) {
			return false;
		}
		if (checkDate == null) {
			checkDate = new Date();
		}
		if (!isActivated(checkDate)) {
			return false;
		}
		if (isDiscontinued(checkDate) || autoExpireDate == null) {
			return false;
		}
		
		return checkDate.after(autoExpireDate);
	}