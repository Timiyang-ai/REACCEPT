@SuppressWarnings("unchecked")
	public List<User> getUsers(String name, List<Role> roles, boolean includeRetired) {
		
		log.debug("name: " + name);
		
		name = HibernateUtil.escapeSqlWildcards(name, sessionFactory);
		
		// Create an HQL query like this:
		// select distinct user
		// from User as user inner join user.person.names as name
		// where (user.username like :name1 or ...and for systemId givenName familyName familyName2...)
		//   and (user.username like :name2 or ...and for systemId givenName familyName familyName2...)
		//   ...repeat for all name fragments...
		//   and user.retired = false
		// order by username asc
		
		List<String> criteria = new ArrayList<String>();
		int counter = 0;
		Map<String, String> namesMap = new HashMap<String, String>();
		if (name != null) {
			name = name.replace(", ", " ");
			String[] names = name.split(" ");
			for (String n : names) {
				if (n != null && n.length() > 0) {
					// compare each fragment of the query against username, systemId, given, middle, family, and family2
					String key = "name" + ++counter;
					String value = n + "%";
					namesMap.put(key, value);
					criteria.add("(user.username like :" + key + " or user.systemId like :" + key
					        + " or name.givenName like :" + key + " or name.middleName like :" + key
					        + " or name.familyName like :" + key + " or name.familyName2 like :" + key + ")");
				}
			}
		}
		if (includeRetired == false)
			criteria.add("user.retired = false");
		
		// build the hql query
		String hql = "select distinct user from User as user inner join user.person.names as name ";
		if (criteria.size() > 0)
			hql += "where ";
		for (Iterator<String> i = criteria.iterator(); i.hasNext();) {
			hql += i.next() + " ";
			if (i.hasNext())
				hql += "and ";
		}
		hql += " order by username asc";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		for (Map.Entry<String, String> e : namesMap.entrySet())
			query.setString(e.getKey(), e.getValue());
		
		// Now apply the roles criteria
		// TODO add this to the HQL query
		// maybe: +inner join user.roles as role +where role.id in :roleIdList
		
		if (roles != null && roles.size() > 0) {
			List returnList = new Vector();
			
			log.debug("looping through to find matching roles");
			for (Object o : query.list()) {
				User u = (User) o;
				for (Role r : roles)
					if (u.hasRole(r.getRole(), true)) {
						returnList.add(u);
						break;
					}
			}
			
			return returnList;
		} else {
			log.debug("not looping because there appears to be no roles");
			return query.list();
		}
		
	}