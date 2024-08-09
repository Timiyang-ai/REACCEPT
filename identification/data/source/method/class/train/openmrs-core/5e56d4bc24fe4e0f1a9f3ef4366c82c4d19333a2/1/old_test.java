	@Test
	public void setGroupMembers_shouldNotMarkTheExistingObsAsDirtyWhenTheSetIsChangedFromNullToANonEmptyOne() throws Exception {
		Obs obs = new Obs(5);
		assertNull(Obs.class.getDeclaredField("groupMembers").get(obs));
		Set<Obs> members = new HashSet<>();
		members.add(new Obs());
		obs.setGroupMembers(members);
		assertFalse(obs.isDirty());
	}