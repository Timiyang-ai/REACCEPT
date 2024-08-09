@Override
	public List<User> getUsers(String name, List<Role> roles, boolean includeRetired, Integer start, Integer length)
	                                                                                                                throws APIException {
		if (name != null)
			name = name.replace(", ", " ");
		
		if (roles == null)
			roles = new Vector<Role>();
		
		// add the requested roles and all child roles for consideration
		Set<Role> allRoles = new HashSet<Role>();
		for (Role r : roles) {
			allRoles.add(r);
			allRoles.addAll(r.getAllChildRoles());
		}
		
		// if the authenticated role is in the list of searched roles, then all
		// persons should be searched
		Role auth_role = getRole(RoleConstants.AUTHENTICATED);
		if (roles.contains(auth_role))
			return dao.getUsers(name, new Vector<Role>(), includeRetired, start, length);
		
		return dao.getUsers(name, new ArrayList<Role>(allRoles), includeRetired, start, length);
	}