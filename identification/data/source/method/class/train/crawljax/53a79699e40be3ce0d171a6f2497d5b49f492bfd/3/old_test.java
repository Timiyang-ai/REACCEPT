@Test
	public void testIsClone() {
		String x = "<form>BL</form>";
		String y = "<form>blabla</form>";

		LOG.debug(StringUtils.getLevenshteinDistance(x, y) + " Thesh: "
		        + comparator.getThreshold(x, y, 0.7));
		assertTrue(comparator.isClone(x, y, 0.0));
		assertTrue(comparator.isClone(x, y, 0.5));
		assertTrue(comparator.isClone(x, y, 0.7));
		assertTrue(comparator.isClone(x, y, 0.75));
		assertTrue(comparator.isClone(x, y, 0.84));
		assertFalse(comparator.isClone(x, y, 0.89));
		assertFalse(comparator.isClone(x, y, 0.893));
		assertFalse(comparator.isClone(x, y, 0.9));
		assertFalse(comparator.isClone(x, y, 1));

		boolean arg = false;

		try {
			comparator.isClone(x, y, -2);
		} catch (IllegalArgumentException e) {
			arg = true;
		}

		assertTrue(arg);

		arg = false;

		try {
			comparator.isClone(x, y, 2);
		} catch (IllegalArgumentException e) {
			arg = true;
		}

		assertTrue(arg);
	}