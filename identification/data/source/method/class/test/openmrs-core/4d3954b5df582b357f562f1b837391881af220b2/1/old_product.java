public void handle(Voidable voidableObject, User voidingUser, Date voidedDate, String voidReason) {
		
		if (StringUtils.isBlank(voidReason)) {
			throw new IllegalArgumentException("The 'reason' argument is required");
		}
	}