@Test
	public void testConformance() {
		final Conformance conf = client.fetchConformance().ofType(Conformance.class).execute();
		assertEquals(conf.getRest().get(0).getResource().get(0).getType(), "Patient");
	}