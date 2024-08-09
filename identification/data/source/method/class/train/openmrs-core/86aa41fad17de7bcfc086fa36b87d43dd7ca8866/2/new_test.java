	@Test
	public void isObsGrouping_shouldIncludeVoidedObs() throws Exception {
		Obs parent = new Obs(5);
		Obs child = new Obs(33);
		child.setVoided(true);
		parent.addGroupMember(child);
		assertTrue("When checking for Obs grouping, should include voided Obs", parent.isObsGrouping());
	}