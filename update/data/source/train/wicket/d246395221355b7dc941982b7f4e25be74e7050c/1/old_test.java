@Test
	public void crypt()
	{
		final ICrypt crypt = new SunJceCrypt();

		try
		{
			if (crypt.encryptUrlSafe("test") != null)
			{
				final String text = "abcdefghijkABC: A test which creates a '/' and/or a '+'";
				final String expectedUrlSafeEncrypted = "g-N_AGk2b3qe70kJ0we4Rsa8getbnPLm6NyE0BCd-go0P-0kuIe6UvAYP7dlzx-9mfmPaMQ5lCk";

				final String encrypted = crypt.encryptUrlSafe(text);
				assertEquals(expectedUrlSafeEncrypted, encrypted);
				assertEquals(text, crypt.decryptUrlSafe(expectedUrlSafeEncrypted));
				assertNull(crypt.decryptUrlSafe("style.css"));
			}
		}
		catch (Exception ex)
		{
			// fails on JVMs without security provider (e.g. seems to be on
			// MAC in US)
		}
	}