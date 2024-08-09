	@Test
	public void getAllChildRoles_shouldOnlyReturnChildRoles() {
		Role grandparent = new Role("Grandparent");
		Role aunt = new Role("Aunt");
		Role uncle = new Role("Uncle");
		Role parent = new Role("Parent");
		Role child1 = new Role("Child 1");
		Role child2 = new Role("Child 2");
		Role niece = new Role("Niece");
		
		Set<Role> childRoles = new HashSet<>();
		
		// grandparent -> aunt, uncle, parent
		childRoles.add(aunt);
		childRoles.add(uncle);
		childRoles.add(parent);
		grandparent.setChildRoles(new HashSet<>(childRoles));
		
		// aunt, uncle -> niece
		childRoles.clear();
		childRoles.add(niece);
		aunt.setChildRoles(new HashSet<>(childRoles));
		uncle.setChildRoles(new HashSet<>(childRoles));
		
		// parent -> child1, child2
		childRoles.clear();
		childRoles.add(child1);
		childRoles.add(child2);
		parent.setChildRoles(new HashSet<>(childRoles));
		
		// ensure only child roles are found
		Assert.assertEquals(0, niece.getAllChildRoles().size());
		Assert.assertEquals(0, child1.getAllChildRoles().size());
		Assert.assertEquals(0, child2.getAllChildRoles().size());
		Assert.assertEquals(2, parent.getAllChildRoles().size());
		Assert.assertEquals(1, aunt.getAllChildRoles().size());
		Assert.assertEquals(1, uncle.getAllChildRoles().size());
		Assert.assertEquals(6, grandparent.getAllChildRoles().size());
	}