	@Test
	public void purgeForm_shouldDeleteFormResourcesForDeletedForm() throws ParseException {
		// create a new form
		Form form = new Form();
		form.setName("form resource test form");
		form.setVersion("42");
		form.setDescription("bleh");
		form = Context.getFormService().saveForm(form);
		
		// save a resource
		String name = "Start Date";
		FormResource resource = new FormResource();
		resource.setForm(form);
		resource.setName(name);
		resource.setDatatypeClassname("org.openmrs.customdatatype.datatype.DateDatatype");
		Date expected = new SimpleDateFormat("yyyy-MM-dd").parse("2011-10-16");
		resource.setValue(expected);
		
		Context.getFormService().saveFormResource(resource);
		
		// make sure the resource is saved
		FormResource actual = Context.getFormService().getFormResource(form, name);
		assertEquals(expected, actual.getValue());
		
		// retain the resource id
		Integer savedId = actual.getFormResourceId();
		
		// delete the form
		Context.getFormService().purgeForm(form);
		
		// check for the resource
		Assert.assertNull(Context.getFormService().getFormResource(savedId));
	}