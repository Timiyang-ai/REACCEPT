	@Test
	public void toString_shouldNotFailWithEmptyObject() {
		Encounter encounter = new Encounter();
		@SuppressWarnings("unused")
		String toStringOutput = encounter.toString();
	}