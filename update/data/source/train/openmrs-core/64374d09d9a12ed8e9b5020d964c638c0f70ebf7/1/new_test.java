@Test
	public void saveVisit_shouldBeAbleToAddAnAttributeToAVisit() throws Exception {
		Date now = new Date();
		Visit visit = service.getVisit(1);
		VisitAttributeType attrType = service.getVisitAttributeType(1);
		VisitAttribute attr = new VisitAttribute();
		attr.setAttributeType(attrType);
		attr.setValue(now);
		visit.addAttribute(attr);
		service.saveVisit(visit);
		Assert.assertEquals(new SimpleDateFormat("yyyy-MM-dd").format(now), attr.getValueReference());
	}