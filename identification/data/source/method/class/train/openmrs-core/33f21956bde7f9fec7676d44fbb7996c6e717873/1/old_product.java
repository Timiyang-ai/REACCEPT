@Override
	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() throws DAOException {
		return sessionFactory.getCurrentSession().createQuery("from User u order by u.userId").list();
	}