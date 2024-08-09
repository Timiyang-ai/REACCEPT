@Test
	public void testAnnotateText() {
		Annotations annotations = service.annotateText(Graph.WIKIPEDIA,
				"Nizar Magboul Alseddeg is currently living in Austin Texas");
		Assert.assertNotNull(annotations);
	}