@Test
	public void testConnect() throws SQLException {
		final Properties info = new Properties();
		try {
			driver.connect(null, info);
		} catch (final SQLException e) {
			// SQLException normale : The url cannot be null
			assertNotNull("connect", e);
		}
		driver.connect("jdbc:h2:mem:?driver=org.h2.Driver", info);
		info.put("driver", "org.h2.Driver");
		driver.connect("jdbc:h2:mem:", info);
		info.put("driver", "nimporte.quoi");
		try {
			driver.connect(null, info);
		} catch (final SQLException e) {
			// SQLException normale : class not found
			assertNotNull("connect", e);
		}
	}