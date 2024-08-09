	@Test
	public void deregister() {
		Instance instance1 = getInstance("App1");
		InstanceId instance1Id = instance1.getId();

		when(this.instanceRegistry.getInstances("App1")).thenReturn(Flux.just(instance1));
		when(this.instanceRegistry.deregister(instance1Id)).thenReturn(Mono.just(instance1Id));

		StepVerifier.create(this.applicationRegistry.deregister("App1"))
				.assertNext((instanceId) -> assertThat(instanceId).isEqualTo(instance1Id)).verifyComplete();

		verify(this.instanceRegistry).deregister(instance1Id);
	}