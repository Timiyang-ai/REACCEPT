@SuppressWarnings("unchecked")
	public List<Drug> getDrugs() {

		Session session = HibernateUtil.currentSession();
		
		List<Drug> drugs = session.createQuery("from Drug").list();
		
		return drugs;
	}