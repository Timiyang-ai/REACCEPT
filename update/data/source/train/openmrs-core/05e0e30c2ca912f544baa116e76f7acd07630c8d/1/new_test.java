@Test
	@Verifies(value = "the order of the fetched Obs is proper according to the specified sort options", method = "getObservations(java.util.List, java.util.List, java.util.List, java.util.List, java.util.List, java.util.List, java.util.List, Integer, Integer, java.util.Date, java.util.Date, boolean)")
	public void getObservations_shouldBeOrderedCorrectly() throws Exception {
		Session session = sessionFactory.getCurrentSession();
		
		List<Obs> obsListActual;
		List<Obs> obsListExpected;
		
		//Order by id desc
		obsListExpected = session.createCriteria(Obs.class, "obs").addOrder(Order.desc("id")).list();
		
		obsListActual = dao.getObservations(null, null, null, null, null, null, Arrays.asList("id"), null, null, null, null,
		    false, null);
		Assert.assertArrayEquals(obsListExpected.toArray(), obsListActual.toArray());
		
		obsListActual = dao.getObservations(null, null, null, null, null, null, Arrays.asList("id desc"), null, null, null,
		    null, false, null);
		Assert.assertArrayEquals(obsListExpected.toArray(), obsListActual.toArray());
		
		//Order by id asc
		obsListExpected = session.createCriteria(Obs.class, "obs").addOrder(Order.asc("id")).list();
		obsListActual = dao.getObservations(null, null, null, null, null, null, Arrays.asList("id asc"), null, null, null,
		    null, false, null);
		Assert.assertArrayEquals(obsListExpected.toArray(), obsListActual.toArray());
		
		//Order by person_id asc and id desc
		obsListExpected = session.createCriteria(Obs.class, "obs").addOrder(Order.asc("person.id")).addOrder(
		    Order.desc("id")).list();
		
		obsListActual = dao.getObservations(null, null, null, null, null, null, Arrays.asList("person.id asc", "id"), null,
		    null, null, null, false, null);
		Assert.assertArrayEquals(obsListExpected.toArray(), obsListActual.toArray());
		
		//Order by person_id asc and id asc
		obsListExpected = session.createCriteria(Obs.class, "obs").addOrder(Order.asc("person.id"))
		        .addOrder(Order.asc("id")).list();
		
		obsListActual = dao.getObservations(null, null, null, null, null, null, Arrays.asList("person.id asc", "id asc"),
		    null, null, null, null, false, null);
		Assert.assertArrayEquals(obsListExpected.toArray(), obsListActual.toArray());
	}