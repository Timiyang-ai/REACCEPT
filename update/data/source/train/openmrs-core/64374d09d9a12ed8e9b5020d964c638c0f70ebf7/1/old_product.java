@Override
	public Visit saveVisit(Visit visit) throws DAOException {
		getCurrentSession().saveOrUpdate(visit);
		return visit;
	}