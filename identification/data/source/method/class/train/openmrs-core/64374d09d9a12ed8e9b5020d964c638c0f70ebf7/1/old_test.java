@Test
	public void saveVisit_shouldBeAbleToAddAnAttributeToAVisit() throws Exception {
		Visit visit = service.getVisit(1);
		VisitAttributeType attrType = service.getVisitAttributeType(1);
		VisitAttribute attr = new VisitAttribute();
		attr.setAttributeType(attrType);
		attr.setValue(new Date());
		visit.addAttribute(attr);
		service.saveVisit(visit);
	}