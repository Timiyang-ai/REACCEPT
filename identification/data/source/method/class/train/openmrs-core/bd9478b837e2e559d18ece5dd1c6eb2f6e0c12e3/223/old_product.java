@Deprecated
	@Transactional(readOnly = true)
	@Authorized(PrivilegeConstants.VIEW_OBS)
	public List<Obs> getObservations(Cohort patients, List<Concept> concepts, Date fromDate, Date toDate);