	@Test
	public void remove() {

		ArrayList<String> compareValues = new ArrayList<String>();

		compareValues.add("Gladiator 1");
		compareValues.add("Gladiator 2");
		compareValues.add("Gladiator 3");
		compareValues.add("Gladiator 4");
		compareValues.add("Gladiator 5");

		List<String> prefs = PreferencesList.map(temp);
		prefs.addAll(compareValues);

		for (int index : new int[] { 4, 0, 1 }) {
			prefs.remove(index);
			compareValues.remove(index);

			assertArrayEquals(compareValues.toArray(), prefs.toArray());
		}

	}