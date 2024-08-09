	@Test
	public void getPresentation_shouldMatchGetMessageWithPresentationMessage() {
		CachedMessageSource cachedMessages = new CachedMessageSource();
		
		PresentationMessage message = new PresentationMessage("uuid.not.unique", Locale.ENGLISH, "the uuid must be unique",
		        "a uuid needs to be unique");
		cachedMessages.addPresentation(message);
		
		String valueAsString = cachedMessages.getMessage(message.getCode(), null, message.getLocale());
		PresentationMessage valueAsPM = cachedMessages.getPresentation(message.getCode(), message.getLocale());
		
		assertEquals(valueAsString, valueAsPM.getMessage());
	}