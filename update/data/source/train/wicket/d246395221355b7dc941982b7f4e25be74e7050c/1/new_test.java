@Test
	public void crypt()
	{
		final ICrypt crypt = new SunJceCrypt();
		crypt.setKey("someStableKey");

		try
		{
			if (crypt.encryptUrlSafe("test") != null)
			{
				final String text = "abcdefghijkABC: A test which creates a '/' and/or a '+'";
				final String expectedUrlSafeEncrypted = "xXMS3UMELV--qVINGVFaYaiqUPOtryc_E4x0MyMFgYl-TgTGKxczTzPvwJrE-4YEVMpl-F3eDAg";

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