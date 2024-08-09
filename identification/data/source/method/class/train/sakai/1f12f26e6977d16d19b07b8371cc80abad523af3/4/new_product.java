public Map<String, String> getUserRolesForGroup(String id) {
		if(log.isDebugEnabled()) log.debug("------------------CMGP.getUserRolesForGroup(" + id + ")");
		Map<String, String> userRoleMap = new HashMap<String, String>();
		
		String[] sectionEids = unpackId(id);
		if(log.isDebugEnabled()) log.debug(id + " is mapped to " + sectionEids.length + " sections");

		for (RoleResolver rr : roleResolvers) {
			for(int i=0; i < sectionEids.length; i++) {
				String sectionEid = sectionEids[i];
				Section section;
				try {
					section = cmService.getSection(sectionEid);
				} catch (IdNotFoundException e) {
					if (log.isWarnEnabled()) log.warn("Unable to find CM section " + sectionEid);
					continue;
				}
				if(log.isDebugEnabled()) log.debug("Looking for roles in section " + sectionEid);
			
				Map<String, String> rrUserRoleMap = rr.getUserRoles(cmService, section);

				for(Iterator<Entry<String, String>> rrRoleIter = rrUserRoleMap.entrySet().iterator(); rrRoleIter.hasNext();) {
					Entry<String, String> entry = rrRoleIter.next();
					String userEid = entry.getKey();
					String existingRole = userRoleMap.get(userEid);
					String rrRole = entry.getValue();

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