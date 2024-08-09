public List<Encounter> getEncountersByPatientIdentifier(String identifier, boolean includeVoided) throws APIException {
		List<Encounter> encs = new Vector<Encounter>();
		for(Patient p : daoContext.getPatientDAO().getPatientsByIdentifier(identifier, includeVoided)) {
			encs.addAll(getEncountersByPatientId(p.getPatientId(), includeVoided));
		}
		return encs;
	}