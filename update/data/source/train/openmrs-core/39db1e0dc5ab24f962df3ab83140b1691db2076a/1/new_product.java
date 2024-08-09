@Override
	public void handle(Retireable retireableObject, User retiringUser, Date retireDate, String retireReason) {
		
		// skip over doing retire stuff if already retired
		if (!retireableObject.getRetired() || retireableObject.getRetiredBy() == null) {
			
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