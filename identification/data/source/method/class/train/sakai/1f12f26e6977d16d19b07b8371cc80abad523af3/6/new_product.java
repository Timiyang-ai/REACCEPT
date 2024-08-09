public Map getUserRolesForGroup(String id) {
		if(log.isDebugEnabled()) log.debug("------------------CMGP.getUserRolesForGroup(" + id + ")");
		Map<String, String> userRoleMap = new HashMap<String, String>();
		
		String[] sectionEids = unpackId(id);
		if(log.isDebugEnabled()) log.debug(id + " is mapped to " + sectionEids.length + " sections");

		for(Iterator<RoleResolver> rrIter = roleResolvers.iterator(); rrIter.hasNext();) {
			RoleResolver rr = rrIter.next();

			for(int i=0; i < sectionEids.length; i++) {
				String sectionEid = sectionEids[i];
				Section section = cmService.getSection(sectionEid);
				if(log.isDebugEnabled()) log.debug("Looking for roles in section " + sectionEid);
			
				Map<String, String> rrUserRoleMap = rr.getUserRoles(cmService, section);
				// Only add the roles if the user isn't already in the map.  Earlier resolvers take precedence.
				for(Iterator<String> rrRoleIter = rrUserRoleMap.keySet().iterator(); rrRoleIter.hasNext();) {
					String userEid = rrRoleIter.next();
					String existingRole = userRoleMap.get(userEid);
					String rrRole = rrUserRoleMap.get(userEid);

					// The Role Resolver has found no role for this user
					if(rrRole == null) {
						continue;
					}
					
					// Add or replace the role in the map if this is a more preferred role than the previous role
					if(existingRole == null) {
						if(log.isDebugEnabled()) log.debug("Adding "+ userEid + " to userRoleMap with role=" + rrRole);
						userRoleMap.put(userEid, rrRole);
					} else if(preferredRole(existingRole, rrRole).equals(rrRole)){
						if(log.isDebugEnabled()) log.debug("Changing "+ userEid + "'s role in userRoleMap from " + existingRole + " to " + rrRole + " for section " + sectionEid);
						userRoleMap.put(userEid, rrRole);
					}
				}
			}
		}
		if(log.isDebugEnabled()) log.debug("_____________getUserRolesForGroup=" + userRoleMap);
		return userRoleMap;
	}