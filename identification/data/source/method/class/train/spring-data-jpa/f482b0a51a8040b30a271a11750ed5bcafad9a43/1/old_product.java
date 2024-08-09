@Transactional
	public void deleteAll() {

		em.createQuery(getDeleteAllQueryString()).executeUpdate();
		em.clear();
	}