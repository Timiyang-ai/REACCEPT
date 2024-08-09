	@Test
	public void newInstance_shouldCopyMostFields() throws Exception {
		Obs obs = new Obs();
		obs.setStatus(Obs.Status.PRELIMINARY);
		obs.setInterpretation(Obs.Interpretation.LOW);
		obs.setConcept(new Concept());
		obs.setValueNumeric(1.2);
		obs.setObsGroup(new  Obs());        
        obs.setAccessionNumber("4849RDD");          
        obs.setValueCoded(new Concept());                  
        obs.setValueDrug(new Drug());                      
        obs.setValueGroupId(new  Integer(23));
        obs.setValueDatetime(new Date());
        obs.setValueModifier("djfsihdihd");              
        obs.setValueText("xyzABC");
        obs.setComment("This is an example");
        obs.setEncounter(new Encounter(3));  
        obs.setCreator( new  User(1));                    
        obs.setDateCreated(new Date());        
        obs.setVoided(false);            
        obs.setVoidedBy(new  User(2));      
        obs.setDateVoided(new Date());                                
        obs.setVoidReason("Some reason");    
        obs.setValueComplex("SDIODF7980IDSISF");
        obs.setComplexData(new ComplexData("Complex data", "Complex data".getBytes()));
		
		Obs copy = Obs.newInstance(obs);
		
		// these fields are not copied
		assertThat(copy.getObsId(), nullValue());
		assertThat(copy.getUuid(), not(obs.getUuid()));
		
		// other fields are copied
		assertThat(copy.getConcept(), is(obs.getConcept()));
		assertThat(copy.getValueNumeric(), is(obs.getValueNumeric()));
		assertThat(copy.getStatus(), is(obs.getStatus()));
		assertThat(copy.getInterpretation(), is(obs.getInterpretation()));
		assertThat(copy.getConcept(), is(obs.getConcept()));
		assertThat(copy.getAccessionNumber(),  is(obs.getAccessionNumber()));                          
		assertThat(copy.getValueDrug(),  is(obs.getValueDrug()));                      
		assertThat(copy.getValueGroupId(),  is(obs.getValueGroupId()));
		assertThat(copy.getValueDatetime(),  is(obs.getValueDatetime()));
		assertThat(copy.getValueNumeric(),  is(obs.getValueNumeric()));      
		assertThat(copy.getValueModifier(),  is(obs.getValueModifier()));              
		assertThat(copy.getValueText(),  is(obs.getValueText()));
		assertThat(copy.getComment(),  is(obs.getComment()));
		assertThat(copy.getEncounter(),  is(obs.getEncounter()));
		assertThat(copy.getCreator(),  is(obs.getCreator()));                  
		assertThat(copy.getDateCreated(),  is(obs.getDateCreated()));        
		assertThat(copy.getVoided(),  is(obs.getVoided()));          
		assertThat(copy.getVoidedBy(),  is(obs.getVoidedBy()));    
		assertThat(copy.getDateVoided(),  is(obs.getDateVoided()));                              
		assertThat(copy.getVoidReason(),  is(obs.getVoidReason()));    
		assertThat(copy.getValueComplex(),  is(obs.getValueComplex()));
		assertThat(copy.getComplexData(),  is(obs.getComplexData()));

	}