	@Test
	public void toListOfResourcesOfTypeTest() {
		Bundle bundle = new Bundle();
		for (int i = 0; i < 5; i++) {
			bundle.addEntry(new Bundle.BundleEntryComponent().setResource(new Patient()));
		}
		List<Patient> list = BundleUtil.toListOfResourcesOfType(ourCtx, bundle, Patient.class);
		assertEquals(5, list.size());
	}