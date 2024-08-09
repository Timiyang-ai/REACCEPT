@Test
	@Verifies(value = "should not allow the returned SimpleDateFormat to be modified", method = "getDateFormat(Locale)")
	public void getDateFormat_shouldNotAllowTheReturnedSimpleDateFormatToBeModified() throws Exception {
		// start with a locale that is not currently cached by getDateFormat()
		Locale locale = new Locale("hk");
		Assert.assertTrue("default locale is potentially already cached", !Context.getLocale().equals(locale));
		
		// get the initially built dateformat from getDateFormat()
		SimpleDateFormat sdf = OpenmrsUtil.getDateFormat(locale);
		Assert.assertNotSame("initial dateFormatCache entry is modifiable", OpenmrsUtil.getDateFormat(locale), sdf);
		
		// verify changing the pattern on our variable does not affect the cache
		sdf.applyPattern("yyyymmdd");
		Assert.assertTrue("initial dateFormatCache pattern is modifiable", !OpenmrsUtil.getDateFormat(locale).toPattern()
		        .equals(sdf.toPattern()));

		// the dateformat cache now contains the format for this locale; checking
		// a second time will guarantee we are looking at cached data and not the
		// initially built dateformat
		sdf = OpenmrsUtil.getDateFormat(locale);
		Assert.assertNotSame("cached dateFormatCache entry is modifiable", OpenmrsUtil.getDateFormat(locale), sdf);
		
		// verify changing the pattern on our variable does not affect the cache
		sdf.applyPattern("yyyymmdd");
		Assert.assertTrue("cached dateFormatCache pattern is modifiable", !OpenmrsUtil.getDateFormat(locale).toPattern()
		        .equals(sdf.toPattern()));
	}