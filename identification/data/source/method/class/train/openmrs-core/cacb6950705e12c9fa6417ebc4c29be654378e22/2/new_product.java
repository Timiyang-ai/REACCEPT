public Set<Role> getAllParentRoles() {
		Set<Role> parents = new HashSet<>();
		if (inheritsRoles()) {
			parents.addAll(this.recurseOverParents(parents));
		}
		return parents;
	}