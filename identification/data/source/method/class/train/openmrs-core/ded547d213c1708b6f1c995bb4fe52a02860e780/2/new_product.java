public Set<Obs> getGroupMembers(boolean includeVoided) {
		if (includeVoided) {
			//just return all group members
			return groupMembers;
		}
		if (groupMembers == null) {
			//Empty set so return null
			return null;
		}
		Set<Obs> nonVoided = new LinkedHashSet<>(groupMembers);
		nonVoided.removeIf(BaseOpenmrsData::getVoided);
		return nonVoided;
	}