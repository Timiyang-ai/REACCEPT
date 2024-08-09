	@Test
	public void builderForCandidate_fail()
	{
		assertThatThrownBy(() -> MaterialDescriptor.builder().build())
				.isInstanceOf(RuntimeException.class);
	}