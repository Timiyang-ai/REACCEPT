	@Test
	public void getAllParentRoles_shouldOnlyReturnParentRoles() {
		Role grandparent = new Role("Grandparent");
		Role aunt = new Role("Aunt");
		Role uncle = new Role("Uncle");
		Role parent = new Role("Parent");
		Role child1 = new Role("Child 1");
		Role child2 = new Role("Child 2");
		Role niece = new Role("Niece");
		
		Set<Role> inheritedRoles = new HashSet<>();
		
		// grandparent -> aunt, uncle, parent
		inheritedRoles.add(grandparent);
		parent.setInheritedRoles(new HashSet<>(inheritedRoles));
		aunt.setInheritedRoles(new HashSet<>(inheritedRoles));
		uncle.setInheritedRoles(new HashSet<>(inheritedRoles));
		
		// aunt, uncle -> niece
		inheritedRoles.clear();
		inheritedRoles.add(uncle);
		inheritedRoles.add(aunt);
		niece.setInheritedRoles(new HashSet<>(inheritedRoles));
		
		// parent -> child1, child2
		inheritedRoles.clear();
		inheritedRoles.add(parent);
		child1.setInheritedRoles(new HashSet<>(inheritedRoles));
		child2.setInheritedRoles(new HashSet<>(inheritedRoles));
		
		// ensure only inherited roles are found
		Assert.assertEquals(3, niece.getAllParentRoles().size());
		Assert.assertEquals(2, child1.getAllParentRoles().size());
		Assert.assertEquals(2, child2.getAllParentRoles().size());
		Assert.assertEquals(1, parent.getAllParentRoles().size());
		Assert.assertEquals(1, aunt.getAllParentRoles().size());
		Assert.assertEquals(1, uncle.getAllParentRoles().size());
		Assert.assertEquals(0, grandparent.getAllParentRoles().size());
	}