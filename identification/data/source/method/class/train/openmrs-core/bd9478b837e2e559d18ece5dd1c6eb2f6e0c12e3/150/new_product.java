@Deprecated
    @Transactional(readOnly = true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_OBS)
	public List<Obs> getObservations(Cohort patients, List<Concept> concepts, Date fromDate, Date toDate);