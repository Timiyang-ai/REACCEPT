@Test
	public void testCreateJob() {
		String []seeds = new String[]{"motrin","tylenol","aspirin"};
		String label = "medicine";
		service.setDataset(ConceptExpansionDataset.MT_SAMPLES);
		Job job = service.createJob(label, seeds);
		Assert.assertNotNull(job);
	}