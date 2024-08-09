@SuppressWarnings("unchecked")
	public List<Person> getPeople(String searchString, Boolean dead, Boolean voided) {
		if (searchString == null) {
			return new ArrayList<>();
		}

		int maxResults = HibernatePersonDAO.getMaximumSearchResults();

		boolean includeVoided = (voided != null) ? voided : false;

		if (StringUtils.isBlank(searchString)) {
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Person.class);
			if (dead != null) {
				criteria.add(Restrictions.eq("dead", dead));
			}

			if (!includeVoided) {
				criteria.add(Restrictions.eq("personVoided", false));
			}

			criteria.setMaxResults(maxResults);
			return criteria.list();
		}

		String query = LuceneQuery.escapeQuery(searchString);

		PersonLuceneQuery personLuceneQuery = new PersonLuceneQuery(sessionFactory);

		LuceneQuery<PersonName> nameQuery = personLuceneQuery.getPersonNameQueryWithOrParser(query, includeVoided);
		if (dead != null) {
			nameQuery.include("person.dead", dead);
		}

		List<Person> people = new ArrayList<>();

		ListPart<Object[]> names = nameQuery.listPartProjection(0, maxResults, "person.personId");
		names.getList().stream().forEach(name -> people.add(getPerson((Integer) name[0])));

		LuceneQuery<PersonAttribute> attributeQuery = personLuceneQuery.getPersonAttributeQueryWithOrParser(query, includeVoided, nameQuery);
		ListPart<Object[]> attributes = attributeQuery.listPartProjection(0, maxResults, "person.personId");
		attributes.getList().stream().forEach(attribute -> people.add(getPerson((Integer) attribute[0])));

		return people;
	}