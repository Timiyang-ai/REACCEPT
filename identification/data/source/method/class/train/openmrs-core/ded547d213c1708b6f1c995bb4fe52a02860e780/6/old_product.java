public Set<Obs> getGroupMembers(boolean includeVoided) {
		if (includeVoided) {
			//just return all group members
			return groupMembers;
		}
		if (groupMembers == null) {
			//Empty set so return null
			return null;
		}
		Set<Obs> nonVoided = new LinkedHashSet<Obs>(groupMembers);
		Iterator<Obs> i = nonVoided.iterator();
		while (i.hasNext()) {
			Obs obs = i.next();
			if (obs.getVoided()) {
				i.remove();
			}
		}
		return nonVoided;
	}