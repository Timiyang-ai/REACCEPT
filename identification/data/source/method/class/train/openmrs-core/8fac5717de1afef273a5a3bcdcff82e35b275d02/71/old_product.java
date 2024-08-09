public Drug getDrug(String drugNameOrId) {
		Integer drugId = null;
		
		try {
			drugId = new Integer(drugNameOrId);
		}
		catch (NumberFormatException nfe) {
			drugId = null;
		}
		
		if (drugId != null) {
			return getDrug(drugId);
		} else {
			List<Drug> drugs = new ArrayList<Drug>();
			drugs = dao.getDrugs(drugNameOrId, null, false);
			if (drugs.size() > 1)
				log.warn("more than one drug name returned with name:" + drugNameOrId);
			if (drugs.size() == 0)
				return null;
			return (Drug) drugs.get(0);
		}
	}