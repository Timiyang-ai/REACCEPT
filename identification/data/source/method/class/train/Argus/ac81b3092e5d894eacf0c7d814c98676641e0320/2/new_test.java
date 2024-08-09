	@Test
	public void testfindAlertsByOwner() {
		String userName = TestUtils.createRandomName();
		int alertsCount = TestUtils.random.nextInt(20) + 1;
		PrincipalUser user = new PrincipalUser(admin ,userName, userName + "@testcompany.com");

		user = userService.updateUser(user);

		List<Alert> expectedAlerts = new ArrayList<>();

		for (int i = 0; i < alertsCount; i++) {
			expectedAlerts.add(alertService.updateAlert(new Alert(user, user, "alert_" + i, EXPRESSION, "* * * * *")));
		}

		List<Alert> actualAlerts = alertService.findAlertsByOwner(user, false);

		assertEquals(actualAlerts.size(), expectedAlerts.size());

		Set<Alert> actualSet = new HashSet<>();

		actualSet.addAll(actualAlerts);
		for (Alert alert : expectedAlerts) {
			assertTrue(actualSet.contains(alert));
		}
	}