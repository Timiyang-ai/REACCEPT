	@Test(dataProvider = "arraySize")
	public void revert(final Integer size) {
		final double[] probabilities = array(size, new Random());
		final double[] reverted = ProbabilitySelector.sortAndRevert(probabilities);

		//System.out.println(Arrays.toString(probabilities));
		//System.out.println(Arrays.toString(reverted));

		for (int i = 0; i < size; ++i) {
			Assert.assertEquals(
				probabilities[i] + reverted[i],
				size - 1.0
			);
		}
	}