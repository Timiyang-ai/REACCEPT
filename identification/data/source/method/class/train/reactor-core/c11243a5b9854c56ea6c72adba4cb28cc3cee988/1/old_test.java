	@Test
	public void isBubbling() {
		Throwable notBubbling = new ReactiveException("foo");
		Throwable bubbling = new BubblingException("foo");

		assertThat(Exceptions.isBubbling(notBubbling)).as("not bubbling").isFalse();
		assertThat(Exceptions.isBubbling(bubbling)).as("bubbling").isTrue();
	}