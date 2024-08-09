	@Test
	public void removeObs_shouldRemoveObsSuccessfully() {
		Obs obsToRemove = new Obs();
		
		Set<Obs> obsSet = new HashSet<>();
		obsSet.add(obsToRemove);
		
		// add the set of obs to the encounter and make sure its there
		Encounter encounter = new Encounter();
		encounter.setObs(obsSet);
		Assert.assertEquals(1, encounter.getAllObs(true).size());
		Assert.assertTrue(encounter.getAllObs(true).contains(obsToRemove));
		
		// remove the obs and make sure its gone from the encounter
		encounter.removeObs(obsToRemove);
		Assert.assertEquals(0, encounter.getAllObs(true).size());
	}