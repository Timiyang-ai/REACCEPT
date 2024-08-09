public GlobalProperty saveGlobalProperty(GlobalProperty gp) throws DAOException {
		GlobalProperty gpObject = getGlobalPropertyObject(gp.getProperty());
		if (gpObject != null) {
			gpObject.setPropertyValue(gp.getPropertyValue());
			gpObject.setDescription(gp.getDescription());
			sessionFactory.getCurrentSession().update(gpObject);
			return gpObject;
		} else {
			sessionFactory.getCurrentSession().save(gp);
			return gp;
		}
	}