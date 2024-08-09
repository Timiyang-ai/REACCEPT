	@Test
	public void getObservations_shouldBeOrderedCorrectly() {
		Session session = sessionFactory.getCurrentSession();
		
		List<Obs> obsListActual;
		List<Obs> obsListExpected;
		
		//Order by id desc
		obsListExpected = session.createCriteria(Obs.class, "obs").addOrder(Order.desc("id")).list();
		
		obsListActual = dao.getObservations(null, null, null, null, null, null, Collections.singletonList("id"), null, null, null, null,
		    false, null);
		Assert.assertArrayEquals(obsListExpected.toArray(), obsListActual.toArray());
		
		obsListActual = dao.getObservations(null, null, null, null, null, null, Collections.singletonList("id desc"), null, null, null,
		    null, false, null);
		Assert.assertArrayEquals(obsListExpected.toArray(), obsListActual.toArray());
		
		//Order by id asc
		obsListExpected = session.createCriteria(Obs.class, "obs").addOrder(Order.asc("id")).list();
		obsListActual = dao.getObservations(null, null, null, null, null, null, Collections.singletonList("id asc"), null, null, null,
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