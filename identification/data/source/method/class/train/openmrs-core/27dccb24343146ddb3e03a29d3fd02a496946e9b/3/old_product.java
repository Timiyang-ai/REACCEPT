@SuppressWarnings("unchecked")
	public EncounterType getEncounterType(String name) throws DAOException {
		return HibernateUtil.findMetadataExactlyInLocalizedColumn(name, "name", EncounterType.class, sessionFactory);
	}