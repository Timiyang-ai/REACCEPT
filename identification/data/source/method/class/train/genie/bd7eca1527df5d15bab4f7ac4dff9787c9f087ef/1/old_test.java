@Test
    public void testGetJobStatus() {
        final String id = UUID.randomUUID().toString();
        final JobStatus status = JobStatus.RUNNING;

        Mockito
            .when(this.jobRepository.findByUniqueId(id, JobStatusProjection.class))
            .thenReturn(Optional.empty())
            .thenReturn(Optional.of(() -> status));
        Assert.assertFalse(this.jobPersistenceService.getJobStatus(id).isPresent());
        Assert.assertThat(
            this.jobPersistenceService.getJobStatus(id).orElseThrow(IllegalArgumentException::new),
            Matchers.is(status)
        );
    }