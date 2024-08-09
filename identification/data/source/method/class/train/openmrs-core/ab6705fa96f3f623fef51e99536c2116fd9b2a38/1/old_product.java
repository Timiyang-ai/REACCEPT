public void setGroupMembers(Set<Obs> groupMembers) {
		if (CollectionUtils.isNotEmpty(this.groupMembers) && CollectionUtils.isNotEmpty(groupMembers)) {
			dirty = !CollectionUtils.disjunction(this.groupMembers, groupMembers).isEmpty();
		} else if (CollectionUtils.isEmpty(this.groupMembers) && CollectionUtils.isNotEmpty(groupMembers)) {
			dirty = true;
		} else if (CollectionUtils.isNotEmpty(this.groupMembers) && CollectionUtils.isEmpty(groupMembers)) {
			dirty = true;
		}
		this.groupMembers = new HashSet<Obs>(groupMembers); //Copy over the entire list
		
	}