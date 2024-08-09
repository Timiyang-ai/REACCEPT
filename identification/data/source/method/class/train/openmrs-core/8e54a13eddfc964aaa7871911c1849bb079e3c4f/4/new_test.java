	@Test
	public void before_shouldNotifyListenersAboutCheckedPrivileges() {
		
		listener1.hasPrivileges.clear();
		listener1.lacksPrivileges.clear();
		
		listener2.hasPrivileges.clear();
		listener2.lacksPrivileges.clear();
		
		Concept concept = Context.getConceptService().getConcept(3);
		
		assertThat("listener1", listener1.hasPrivileges, containsInAnyOrder(PrivilegeConstants.GET_CONCEPTS));
		assertThat("listener2", listener2.hasPrivileges, containsInAnyOrder(PrivilegeConstants.GET_CONCEPTS));
		assertThat(listener1.lacksPrivileges, empty());
		assertThat(listener2.lacksPrivileges, empty());
		
		listener1.hasPrivileges.clear();
		listener2.hasPrivileges.clear();
		
		Context.getConceptService().saveConcept(concept);
		
		String[] privileges = { PrivilegeConstants.MANAGE_CONCEPTS, PrivilegeConstants.GET_OBS,
		        PrivilegeConstants.GET_CONCEPT_ATTRIBUTE_TYPES };
		assertThat("listener1", listener1.hasPrivileges, containsInAnyOrder(privileges));
		assertThat("listener2", listener2.hasPrivileges, containsInAnyOrder(privileges));
		assertThat(listener1.lacksPrivileges, empty());
		assertThat(listener2.lacksPrivileges, empty());
	}