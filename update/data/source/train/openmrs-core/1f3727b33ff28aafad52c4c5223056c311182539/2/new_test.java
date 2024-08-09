@Test
	public void addGroupMember_shouldReturnFalseWhenADuplicateObsIsAddedAsAMember() throws Exception {
		Obs obs = new Obs(2);
		Obs member = new Obs();
		obs.addGroupMember(member);
		assertFalse(obs.isDirty());
		resetObs(obs);
		obs.addGroupMember(member);
		assertFalse(obs.isDirty());
	}