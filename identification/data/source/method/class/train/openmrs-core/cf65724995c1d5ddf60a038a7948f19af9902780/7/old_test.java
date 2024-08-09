	@Test
	public void saveEncounter_shouldUpdateExistingEncounterWhenAChildObsIsEdited() {
		executeDataSet(ENC_OBS_HIERARCHY_DATA_XML);
		EncounterService es = Context.getEncounterService();
		ObsService os = Context.getObsService();

		Encounter enc = es.getEncounter(100);

		Obs o = os.getObs(101);
		o.setValueText("Obs value updated");

		es.saveEncounter(enc);
		Context.flushSession();
		Context.clearSession();

		updateSearchIndex();

		enc = es.getEncounter(100);

		Set<Obs> obsAtTopLevelUpdated = enc.getObsAtTopLevel(true);
		Obs oParent = os.getObs(100);
		final Obs editedObs = os.getObs(101);
		Obs o2 = os.getObs(102);
		Obs o3 = getNewVersionOfEditedObs(oParent,editedObs);

		assertEquals(1,obsAtTopLevelUpdated.size());
		assertEquals(3, oParent.getGroupMembers(true).size());
		assertTrue(editedObs.getVoided());
		assertFalse(oParent.getVoided());
		assertFalse(o2.getVoided());

		assertNotNull(o3);
		assertFalse(o3.getVoided());
		assertEquals("Obs value updated", o3.getValueText());
	}