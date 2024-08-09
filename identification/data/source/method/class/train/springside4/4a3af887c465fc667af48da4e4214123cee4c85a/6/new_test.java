@Test
	@Category(Smoke.class)
	public void getTeamDetail() {
		AccountWebService accountWebService = creatClient();

		GetTeamDetailResult result = accountWebService.getTeamDetail(1L);
		assertEquals("Dolphin", result.getTeam().getName());
		assertEquals("Admin", result.getTeam().getMaster().getName());
	}