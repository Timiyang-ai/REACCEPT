	@Test
	public void purgePersonAttributeType_shouldDeletePersonAttributeTypeFromDatabase() throws Exception {
		PersonService service = Context.getPersonService();
		
		PersonAttributeType pat = new PersonAttributeType();
		pat.setName("attr type name");
		pat.setDescription("attr type desc");
		pat.setFormat("java.lang.String");
		
		service.savePersonAttributeType(pat);
		
		assertNotNull(pat.getId());
		
		service.purgePersonAttributeType(pat);
		
		PersonAttributeType deletedPersonAttributeType = service.getPersonAttributeType(pat.getId());
		Assert.assertNull(deletedPersonAttributeType);
	}