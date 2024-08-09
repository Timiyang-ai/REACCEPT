	@Test
	public void add() {
		final Buffer<Integer> buffer = Buffer.ofCapacity(10);
		buffer.add(1);
		buffer.add(2);
		buffer.add(3);

		Assert.assertEquals(buffer.toSeq(), ISeq.of(1, 2, 3));
		Assert.assertEquals(
			ISeq.of(buffer.toArray(Integer[]::new)),
			ISeq.of(1, 2, 3)
		);
	}