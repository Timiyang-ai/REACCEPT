@Override
	@Transactional(readOnly = true)
	public Cohort getCohort(Integer id) {
		return dao.getCohort(id);
	}