	@Test
	public void acceptable(){
		double precision = 0.001;
		double zeroThreshold = (precision * precision);

		assertTrue(VerificationUtil.acceptable(1.0, 1.0, precision, zeroThreshold));

		assertTrue(VerificationUtil.acceptable(1.0, 0.999, precision, zeroThreshold));
		assertFalse(VerificationUtil.acceptable(1.0, 0.99895, precision, zeroThreshold));

		assertTrue(VerificationUtil.acceptable(1.0, 1.001, precision, zeroThreshold));
		assertFalse(VerificationUtil.acceptable(1.0, 1.00105, precision, zeroThreshold));

		assertTrue(VerificationUtil.acceptable(-1.0, -1.0, precision, zeroThreshold));

		assertTrue(VerificationUtil.acceptable(-1.0, -1.001, precision, zeroThreshold));
		assertFalse(VerificationUtil.acceptable(-1.0, -1.00105, precision, zeroThreshold));

		assertTrue(VerificationUtil.acceptable(-1.0, -0.999, precision, zeroThreshold));
		assertFalse(VerificationUtil.acceptable(-1.0, -0.99895, precision, zeroThreshold));
	}