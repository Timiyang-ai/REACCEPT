@Transactional
	public void deleteAll() {

		em.createQuery(getDeleteAllQueryString()).executeUpdate();
	}