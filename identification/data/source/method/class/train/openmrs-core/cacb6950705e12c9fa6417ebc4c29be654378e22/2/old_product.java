public Set<Role> getAllParentRoles() {
		Set<Role> parents = new HashSet<Role>();
		if (inheritsRoles()) {
			parents.addAll(this.recurseOverParents(parents));
		}
		return parents;
	}