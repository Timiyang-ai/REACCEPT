@SuppressWarnings("unchecked")
	public List<User> getUsers(String name, List<Role> roles, boolean includeVoided) {
		log.debug("name: " + name);
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		if (name != null) {
			criteria.createAlias("names", "name");
			
			name = name.replace(", ", " ");
			String[] names = name.split(" ");
			for (String n : names) {
				if (n != null && n.length() > 0) {
					criteria.add(Expression.or(Expression.like("name.familyName2", name, MatchMode.START), Expression.or(
					    Expression.like("name.givenName", n, MatchMode.START), Expression.or(Expression.like(
					        "name.familyName", n, MatchMode.START), Expression.or(Expression.like("name.middleName", n,
					        MatchMode.START), Expression.or(Expression.like("systemId", n, MatchMode.START), Expression
					            .like("username", n, MatchMode.START)))))));
				}
			}
		}
		/*
		if (roles != null && roles.size() > 0) {
			criteria.createAlias("roles", "r")
				.add(Expression.in("r.role", roles))
				.createAlias("groups", "g")
				.createAlias("g.roles", "gr")
					.add(Expression.or(
							Expression.in("r.role", roles),
							Expression.in("gr.role", roles)
							));
		}
		 */

		if (includeVoided == false)
			criteria.add(Expression.eq("voided", false));
		
		criteria.addOrder(Order.asc("userId"));
		
		// TODO figure out how to get Hibernate to do the sql for us
		
		if (roles != null && roles.size() > 0) {
			List returnList = new Vector();
			
			log.debug("looping through to find matching roles");
			for (Object o : criteria.list()) {
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
			return criteria.list();
		}
		
	}