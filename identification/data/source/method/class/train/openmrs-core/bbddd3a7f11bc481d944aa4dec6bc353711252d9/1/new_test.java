@Test
	public void saveOrder_shouldPassForRevisionOrderIfAnActiveTestOrderForTheSameConceptAndCareSettingsExists()
	        throws Exception {
		final Patient patient = patientService.getPatient(2);
		final Concept cd4Count = conceptService.getConcept(5497);
		TestOrder activeOrder = new TestOrder();
		activeOrder.setPatient(patient);
		activeOrder.setConcept(cd4Count);
		activeOrder.setEncounter(encounterService.getEncounter(6));
		activeOrder.setOrderer(providerService.getProvider(1));
		activeOrder.setCareSetting(orderService.getCareSetting(2));
		activeOrder.setDateActivated(new Date());
		activeOrder.setAutoExpireDate(DateUtils.addDays(new Date(), 10));
		orderService.saveOrder(activeOrder, null);
		
		//New order in future for same concept
		TestOrder secondOrder = new TestOrder();
		secondOrder.setPatient(activeOrder.getPatient());
		secondOrder.setConcept(activeOrder.getConcept());
		secondOrder.setEncounter(encounterService.getEncounter(6));
		secondOrder.setOrderer(providerService.getProvider(1));
		secondOrder.setCareSetting(activeOrder.getCareSetting());
		secondOrder.setDateActivated(new Date());
		secondOrder.setScheduledDate(DateUtils.addDays(activeOrder.getEffectiveStopDate(), 1));
		secondOrder.setUrgency(Order.Urgency.ON_SCHEDULED_DATE);
		orderService.saveOrder(secondOrder, null);
		
		//Revise second order to have scheduled date overlapping with active order
		TestOrder revision = secondOrder.cloneForRevision();
		revision.setScheduledDate(DateUtils.addDays(activeOrder.getEffectiveStartDate(), 2));
		revision.setEncounter(encounterService.getEncounter(6));
		revision.setOrderer(providerService.getProvider(1));
		
		Order savedSecondOrder = orderService.saveOrder(revision, null);
		
		assertNotNull(orderService.getOrder(savedSecondOrder.getOrderId()));
	}