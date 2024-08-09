	@Test
	public void savePersonAttributeType_shouldSetTheDateCreatedAndCreatorOnNew() throws Exception {
		PersonService service = Context.getPersonService();
		
		PersonAttributeType pat = new PersonAttributeType();
		pat.setName("attr type name");
		pat.setDescription("attr type desc");
		pat.setFormat("java.lang.String");
		
		service.savePersonAttributeType(pat);
		
		assertEquals(1, pat.getCreator().getId().intValue());
		assertNotNull(pat.getDateCreated());
	}