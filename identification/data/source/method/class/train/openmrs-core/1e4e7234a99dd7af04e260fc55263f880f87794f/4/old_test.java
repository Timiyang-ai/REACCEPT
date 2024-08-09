	@Test
	public void validate_shouldAcceptAVisitThatHasTheRightNumberOfAttributeOccurrences() {
		Visit visit = makeVisit();
		visit.addAttribute(makeAttribute("one"));
		visit.addAttribute(makeAttribute("two"));
		ValidateUtil.validate(visit);
	}