@Test
	@Verifies(value = "should reject a duplicate address", method = "validate(Object,Errors)")
	public void validate_shouldRejectADuplicateAddress() throws Exception {
		Patient patient = ps.getPatient(2);
		PersonAddress oldAddress = patient.getPersonAddress();
		Assert.assertEquals(1, patient.getAddresses().size());//sanity check
		//add a name for testing purposes
		PersonAddress address = (PersonAddress) oldAddress.clone();
		address.setPersonAddressId(null);
		address.setUuid(null);
		address.setAddress1("address1");
		address.setAddress2("address2");
		patient.addAddress(address);
		Context.getPatientService().savePatient(patient);
		Assert.assertNotNull(address.getId());//should have been added
		
		ShortPatientModel model = new ShortPatientModel(patient);
		//should still be the preferred address for the test to pass
		Assert.assertEquals(oldAddress.getId(), model.getPersonAddress().getId());
		//change to a duplicate name
		model.getPersonAddress().setAddress1("Address1");//should be case insensitive
		model.getPersonAddress().setAddress2("address2");
		
		Errors errors = new BindException(model, "patientModel");
		validator.validate(model, errors);
		Assert.assertEquals(true, errors.hasErrors());
	}