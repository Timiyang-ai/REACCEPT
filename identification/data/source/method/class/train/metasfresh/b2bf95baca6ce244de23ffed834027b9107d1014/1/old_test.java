	@Test
	public void test_weightedAverage()
	{
		final int stdPrecision = 2;
		final BigDecimal comparationError = new BigDecimal("0.01");

		//
		// UOM
		final I_C_UOM uom = uomHelper.createUOM("UOM", stdPrecision);

		//
		// Random number generator
		// final Random random = new Random(System.currentTimeMillis());

		//
		//
		BigDecimal currentQtySum = new BigDecimal(0);
		Quantity quantity = new Quantity(BigDecimal.ZERO, uom);

		for (int count = 1; count <= 100; count++)
		{
			// System.out.println("Iteration " + count + " ---------------------------------------------------------");

			// Generate next Qty value (randomly)
			final BigDecimal currentQtyValue = new BigDecimal(count);
			// new BigDecimal(random.nextDouble() * 10000000).setScale(stdPrecision, RoundingMode.HALF_UP);

			// System.out.println("currentQtyValue=" + currentQtyValue);

			//
			// Calculate current average quantity => our expectation
			currentQtySum = currentQtySum.add(currentQtyValue);
			// System.out.println("currentQtySum=" + currentQtySum);
			final BigDecimal currentQtyAvg = currentQtySum.divide(BigDecimal.valueOf(count), stdPrecision, RoundingMode.HALF_UP);
			// System.out.println("Expected quantity avg=" + currentQtyAvg);

			//
			// Calculate average quantity by using "weightedAverage" method
			final int previousAverageWeight = count - 1;
			quantity = new Quantity(currentQtyValue, uom)
					.weightedAverage(quantity.toBigDecimal(), previousAverageWeight);
			// System.out.println("Actual quantity avg=" + quantity.getQty());

			//
			// Assume their are equal
			Assert.assertThat("Invalid Quantity.weightedAverage result (count=" + count + ")",
					quantity.toBigDecimal(), // Actual
					BigDecimalCloseTo.closeTo(currentQtyAvg, comparationError) // expectation
			);
		}
	}