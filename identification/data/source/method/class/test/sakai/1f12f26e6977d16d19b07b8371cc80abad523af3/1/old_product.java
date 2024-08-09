public Map getUserRolesForGroup(String id) {
		if(log.isDebugEnabled()) log.debug("------------------CMGP.getUserRolesForGroup(" + id + ")");
		Map userRoleMap = new HashMap();
		
		String[] sectionEids = unpackId(id);
		if(log.isDebugEnabled()) log.debug(id + " is mapped to " + sectionEids.length + " sections");
		for(int i=0; i < sectionEids.length; i++) {
			String sectionEid = sectionEids[i];
			Section section = cmService.getSection(sectionEid);
			if(log.isDebugEnabled()) log.debug("Looking for roles in section " + sectionEid);
			for(Iterator rrIter = roleResolvers.iterator(); rrIter.hasNext();) {
				RoleResolver rr = (RoleResolver)rrIter.next();
				Map rrUserRoleMap = rr.getUserRoles(cmService, section);
				// Only add the roles if the user isn't already in the map.  Earlier resolvers take precedence.
				for(Iterator rrRoleIter = rrUserRoleMap.keySet().iterator(); rrRoleIter.hasNext();) {
					String userEid = (String)rrRoleIter.next();
					String existingRole = (String)userRoleMap.get(userEid);
					String rrRole = (String)rrUserRoleMap.get(userEid);
					if(existingRole == null && rrRole != null) {
						if(log.isDebugEnabled()) log.debug("Adding "+ userEid + " to userRoleMap with role=" + rrRole);
						userRoleMap.put(userEid, rrRole);
					}
				}
			}
		}
		if(log.isDebugEnabled()) log.debug("_____________getUserRolesForGroup=" + userRoleMap);
		return userRoleMap;
	}