public void handle(Retireable retireableObject, User retiringUser, Date retireDate, String retireReason) {
		
		if (retireReason == null || retireReason.equals(""))
			throw new IllegalArgumentException("The 'reason' argument is required");
		
		// skip over doing retire stuff if already retired
		if (!retireableObject.isRetired() || retireableObject.getRetiredBy() == null) {
			
			retireableObject.setRetired(true);
			retireableObject.setRetireReason(retireReason);
			
			if (retireableObject.getRetiredBy() == null) {
				retireableObject.setRetiredBy(retiringUser);
			}
			if (retireableObject.getDateRetired() == null) {
				retireableObject.setDateRetired(retireDate);
			}
			
		}
		
	}