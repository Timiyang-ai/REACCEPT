	@Test
	public void removeGroupMember_shouldReturnFalseWhenANonExistentObsIsRemoved() throws Exception {
		Obs obs = new Obs();
		obs.removeGroupMember(new Obs());
		assertFalse(obs.isDirty());
	}