	@Test
	public void toArray() {
		final Buffer<Integer> buffer = Buffer.ofCapacity(5);
		final Object[] array = buffer.toArray(Integer[]::new);
		Assert.assertEquals(array.length, 0);
	}