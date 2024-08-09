@Test
  public void testCreateJob() {
    final String[] seeds = new String[] {"motrin", "tylenol", "aspirin"};
    final String label = "medicine";
    service.setDataset(ConceptExpansionDataset.MT_SAMPLES);
    final Job job = service.createJob(label, seeds);
    Assert.assertNotNull(job);
  }