	@Test
	public void getFullVersionTest() {
		for (int i=0; i!= ddrTestObjects.size(); i++) {
			Object ddrObject = ddrTestObjects.get(i);
			Object jextractObject = jextractTestObjects.get(i);
			
			(new ManagedRuntimeComparator()).testEquals(ddrObject, jextractObject, ManagedRuntimeComparator.FULL_VERSION);
		}
	}