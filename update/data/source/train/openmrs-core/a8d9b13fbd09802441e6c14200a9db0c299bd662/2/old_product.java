@SuppressWarnings("unchecked")
	public List<EncounterType> findEncounterTypes(String name) throws DAOException {
		// define query orders
		LinkedHashMap<String, String> orders = new LinkedHashMap<String, String>();
		orders.put("localizedName", "asc");
		orders.put("retired", "asc");
		
		return HibernateUtil.findMetadataInexactlyInLocalizedColumn(name, "name", "localizedName", EncounterType.class,
		    orders,
		    sessionFactory);
	}