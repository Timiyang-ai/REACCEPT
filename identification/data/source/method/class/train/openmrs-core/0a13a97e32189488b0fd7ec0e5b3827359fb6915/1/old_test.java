@Test
	@Verifies(value = "return distinct drugs", method = "getDrugs(String,Concept,boolean,boolean,boolean,Integer,Integer)")
	public void getDrugs_shouldReturnDistinctDrugs() throws Exception {
		Session session2 = sessionFactory.getCurrentSession();
		session2.beginTransaction();
		Concept concept1 = (Concept) session2.get(Concept.class, 14);
		
		List<Drug> drugList = dao.getDrugs("TEST_DRUG", concept1, true, true, false, 0, 10);
		Assert.assertEquals(1, drugList.size());
		
	}