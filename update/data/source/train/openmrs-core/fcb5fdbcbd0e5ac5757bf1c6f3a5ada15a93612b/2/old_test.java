@Test
	public void getAllFlattenObs_shouldGetAllFlattenObs() {
		Encounter enc = new Encounter();

		//create and add an Obs
		Obs firstObs = new Obs();
		firstObs.setValueText("firstObs");
		enc.addObs(firstObs);

		//add a child to the obs and make sure that now that the Obs is an ObsGroup with one child:
		Obs secondObs = new Obs();
		secondObs.setValueText("secondObs");
		firstObs.addGroupMember(secondObs);

		// add second top level obs
		Obs thirdObs = new Obs();
		thirdObs.setValueText("thirdObs");
		enc.addObs(thirdObs);

		// do the check
		assertEquals(3, enc.getAllFlattenObs(true).size());
		assertEquals(3, enc.getAllFlattenObs(false).size());

	}