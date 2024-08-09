@Test
	public void removeGroupMember_shouldReturnDirtyFalseWhenAnObsIsRemoved() throws Exception {
		Obs obs = new Obs();
		Obs member = new Obs();
		obs.addGroupMember(member);
		assertFalse(obs.isDirty());
		resetObs(obs);
		obs.removeGroupMember(member);
		assertFalse(obs.isDirty());
	}