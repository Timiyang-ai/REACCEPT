public void setGroupMembers(Set<Obs> groupMembers) {
		if (CollectionUtils.isNotEmpty(this.groupMembers) && CollectionUtils.isNotEmpty(groupMembers)) {
			setDirty(!CollectionUtils.disjunction(this.groupMembers, groupMembers).isEmpty());
		} else if (CollectionUtils.isEmpty(this.groupMembers) && CollectionUtils.isNotEmpty(groupMembers)) {
			setDirty(true);
		} else if (CollectionUtils.isNotEmpty(this.groupMembers) && CollectionUtils.isEmpty(groupMembers)) {
			setDirty(true);
		}
		this.groupMembers = new HashSet<Obs>(groupMembers); //Copy over the entire list
		
	}