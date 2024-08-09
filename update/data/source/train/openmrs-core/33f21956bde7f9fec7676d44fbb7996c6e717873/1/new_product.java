@Override
	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() throws DAOException {
		return sessionFactory.getCurrentSession().createQuery("from User where not uuid = :daemonUserUuid order by userId")
				                                                     .setString("daemonUserUuid", Daemon.getDaemonUserUuid()).list();
		
	}