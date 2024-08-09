public Set<Role> getAllChildRoles() {
		Set<Role> children = new HashSet<>();
		if (hasChildRoles()) {
			children.addAll(this.recurseOverChildren(children));
		}
		return children;
	}