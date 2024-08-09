@Transactional(readOnly = true)
	@Authorized(PrivilegeConstants.VIEW_OBS)
	public List<Obs> getObservationsByPerson(Person who);