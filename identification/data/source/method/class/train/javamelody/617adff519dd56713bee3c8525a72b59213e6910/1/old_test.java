@Test
	public void testConnect() throws SQLException {
		final Properties info = new Properties();
		assertNull("connect", driver.connect(null, info));
		info.put("driver", "sun.jdbc.odbc.JdbcOdbcDriver");
		try {
			driver.connect(null, info);
		} catch (final SQLException e) {
			// SQLException normale : The url cannot be null
			assertNotNull("connect", e);
		}
		info.put("driver", "nimporte.quoi");
		try {
			driver.connect(null, info);
		} catch (final SQLException e) {
			// SQLException normale : class not found
			assertNotNull("connect", e);
		}
	}