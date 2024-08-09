@Test
	@Category(Smoke.class)
	public void getTeamDetail() {
		AccountWebService accountWebService = creatClient();

		GetTeamDetailResponse response = accountWebService.getTeamDetail(1L);
		assertEquals("Dolphin", response.getTeam().getName());
		assertEquals("Admin", response.getTeam().getMaster().getName());
	}