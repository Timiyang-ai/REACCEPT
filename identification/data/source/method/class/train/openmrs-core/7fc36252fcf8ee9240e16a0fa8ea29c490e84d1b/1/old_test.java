@Test
	@Verifies(value = "should serialize correctly if given object has variants", method = "serialize(Object)")
	public void serialize_shouldSerializeCorrectlyIfGivenObjectHasVariants() throws Exception {
		LocalizedString ls = new LocalizedString();
		ls.setUnlocalizedValue("Favorite Color");
		Map<Locale, String> variants = new LinkedHashMap<Locale, String>();
		variants.put(new Locale("en", "UK"), "Favourite Colour");
		variants.put(new Locale("fr"), "Couleur pr��f��r��e");
		ls.setVariants(variants);
		String expected = "Favorite Color^v1^en_UK:Favourite Colour;fr:Couleur pr��f��r��e";
		OpenmrsSerializer serializer = new LocalizedStringSerializer();
		String actual = serializer.serialize(ls);
		assertEquals(expected, actual);
	}