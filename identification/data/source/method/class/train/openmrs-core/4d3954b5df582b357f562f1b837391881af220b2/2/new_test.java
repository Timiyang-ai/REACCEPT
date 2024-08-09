	@Test
	public void getLocales_shouldShouldBeAbleToContainMultipleLocales() {
		CachedMessageSource cachedMessages = new CachedMessageSource();
		cachedMessages.addPresentation(new PresentationMessage("uuid.not.unique", Locale.ENGLISH, "the uuid must be unique",
		        "a uuid needs to be unique"));
		cachedMessages.addPresentation(new PresentationMessage("patient.name.required", Locale.GERMAN,
		        "der patientenname ist verpflichtend", "der patientenname ist ein verpflichtendes feld"));
		cachedMessages.addPresentation(new PresentationMessage("patient.address.required", Locale.FRENCH,
		        "l'adresse du patient est obligatoire", "l'adresse du patient est obligatoire"));
		
		assertEquals(3, cachedMessages.getLocales().size());
	}