@Test
	public void getAllFlattenObs_shouldGetAllFlattenedObs() {
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
		thirdObs.setVoided(true);
		enc.addObs(thirdObs);

		// add three-level obs
		Obs fourthObs = new Obs();
		fourthObs.setValueText("fourthObs");

		Obs fifthObs = new Obs();
		fifthObs.setValueText("fifthObs");
		Obs sixthObs = new Obs();
		sixthObs.setValueText("sixthObs");
		sixthObs.setVoided(true);
		Obs seventhObs = new Obs();
		seventhObs.setValueText("seventhObs");
		fifthObs.addGroupMember(sixthObs);
		fifthObs.addGroupMember(seventhObs);
		fourthObs.addGroupMember(fifthObs);

		enc.addObs(fourthObs);

		// do the check
		assertEquals(7, enc.getAllFlattenedObs(true).size());
		assertTrue(enc.getAllFlattenedObs(true).contains(thirdObs));
		assertTrue(enc.getAllFlattenedObs(true).contains(seventhObs));

		assertEquals(5, enc.getAllFlattenedObs(false).size());
		assertFalse(enc.getAllFlattenedObs(false).contains(sixthObs));
		assertTrue(enc.getAllFlattenedObs(false).contains(secondObs));
	}