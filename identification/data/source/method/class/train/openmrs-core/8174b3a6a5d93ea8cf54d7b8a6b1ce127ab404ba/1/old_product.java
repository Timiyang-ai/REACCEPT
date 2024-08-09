public GlobalProperty saveGlobalProperty(GlobalProperty gp) throws DAOException {
		sessionFactory.getCurrentSession().saveOrUpdate(gp);
		return gp;
	}