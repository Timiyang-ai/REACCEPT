public Set<Role> getAllChildRoles() {
		Set<Role> children = new HashSet<Role>();
		if (hasChildRoles()) {
			children.addAll(this.recurseOverChildren(children));
		}
		return children;
	}