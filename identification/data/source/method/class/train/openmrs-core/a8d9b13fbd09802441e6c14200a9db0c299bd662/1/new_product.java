@SuppressWarnings("unchecked")
	public List<EncounterType> findEncounterTypes(String name) throws DAOException {
		List<EncounterType> results = null;
		
		// firstly, search in those unlocalized encounterTypes
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(EncounterType.class);
		crit.add(Expression.sql("UPPER(name) like ?", LocalizedStringUtil.escapeDelimiter(name).toUpperCase() + "%",
		    Hibernate.STRING));
		results = crit.addOrder(Order.asc("localizedName")).addOrder(Order.asc("retired")).list();
		
		// secondly, search in those localized encounterTypes
		List<EncounterType> temp = HibernateUtil.findMetadatasFuzzilyByLocalizedColumn(name, "name", true, false,
		    EncounterType.class, sessionFactory);
		
		// only when there exist localized encounterTypes which match the passed name,
		// then do java sorting on the orderBy fields, this is for the quick speed of query.
		if (!temp.isEmpty()) {
			results.addAll(temp);
			Collections.sort(results, new Comparator<EncounterType>() {
				@Override
				public int compare(EncounterType left, EncounterType right) {
					int res = left.getName().compareTo(right.getName());
					if (res == 0)
						res = left.isRetired().compareTo(right.isRetired());
					return res;
				}
			});
		}
		
		return results;
	}