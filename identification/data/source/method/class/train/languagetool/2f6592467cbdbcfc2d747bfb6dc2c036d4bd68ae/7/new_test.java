  void runTestsV2() throws IOException, SAXException, ParserConfigurationException {
    // no error:
    String emptyResultPattern = ".*\"matches\":\\[\\].*";
    German german = new GermanyGerman();
    String result1 = checkV2(german, "");
    assertTrue("Got " + result1 + ", expected " + emptyResultPattern, result1.matches(emptyResultPattern));
    String result2 = checkV2(german, "Ein kleiner Test");
    assertTrue("Got " + result2 + ", expected " + emptyResultPattern, result2.matches(emptyResultPattern));
    // one error:
    assertTrue(checkV2(german, "ein kleiner test.").contains("UPPERCASE_SENTENCE_START"));
    // two errors:
    String result = checkV2(german, "ein kleiner test. Und wieder Erwarten noch was: \u00f6\u00e4\u00fc\u00df.");
    assertTrue("Got result without 'UPPERCASE_SENTENCE_START': " + result, result.contains("UPPERCASE_SENTENCE_START"));
    assertTrue("Got result without 'WIEDER_WILLEN': " + result, result.contains("WIEDER_WILLEN"));
    assertTrue("Expected special chars, got: '" + result + "'",
            result.contains("\u00f6\u00e4\u00fc\u00df"));   // special chars are intact
    assertTrue(checkV2(german, "bla <script>").contains("<script>"));  // no escaping of '<' and '>' needed, unlike in XML

    // other tests for special characters
    String germanSpecialChars = checkV2(german, "ein kleiner test. Und wieder Erwarten noch was: öäüß+ öäüß.");
    assertTrue("Expected special chars, got: '" + germanSpecialChars + "'", germanSpecialChars.contains("öäüß+"));
    String romanianSpecialChars = checkV2(new Romanian(), "bla bla șțîâă șțîâă și câteva caractere speciale");
    assertTrue("Expected special chars, got: '" + romanianSpecialChars + "'", romanianSpecialChars.contains("șțîâă"));
    Polish polish = new Polish();
    String polishSpecialChars = checkV2(polish, "Mówiła długo, żeby tylko mówić mówić długo.");
    assertTrue("Expected special chars, got: '" + polishSpecialChars+ "'", polishSpecialChars.contains("mówić"));
    // test http POST
    assertTrue(checkByPOST(new Romanian(), "greșit greșit").contains("greșit"));
    // test supported language listing
    URL url = new URL("http://localhost:" + HTTPTools.getDefaultPort() + "/v2/languages");
    String languagesJson = StringTools.streamToString((InputStream) url.getContent(), "UTF-8");
    if (!languagesJson.contains("Romanian") || !languagesJson.contains("English")) {
      fail("Error getting supported languages: " + languagesJson);
    }
    if (!languagesJson.contains("\"de\"") || !languagesJson.contains("\"de-DE\"")) {
      fail("Error getting supported languages: " + languagesJson);
    }
    // tests for "&" character
    English english = new English();
    assertTrue(checkV2(english, "Me & you you").contains("&"));
    // tests for mother tongue (copy from link {@link FalseFriendRuleTest})
    //assertTrue(checkV2(english, german, "My handy is broken.").contains("EN_FOR_DE_SPEAKERS_FALSE_FRIENDS"));  // only works with ngrams
    assertFalse(checkV2(english, german, "We will berate you").contains("BERATE"));  // not active anymore now that we have EN_FOR_DE_SPEAKERS_FALSE_FRIENDS
    assertTrue(checkV2(german, english, "Man sollte ihn nicht so beraten.").contains("BERATE"));
    assertTrue(checkV2(polish, english, "To jest frywolne.").contains("FRIVOLOUS"));
      
    //test for no changed if no options set
    String[] nothing = {};
    assertEquals(checkV2(english, german, "We will berate you"), 
        checkWithOptionsV2(english, german, "We will berate you", nothing, nothing, false));
    
    //disabling
    String[] disableAvsAn = {"EN_A_VS_AN"};
    assertTrue(!checkWithOptionsV2(
            english, german, "This is an test", nothing, disableAvsAn, false).contains("an test"));

    //enabling
    assertTrue(checkWithOptionsV2(
            english, german, "This is an test", disableAvsAn, nothing, false).contains("an test"));
    //should also mean _NOT_ disabling all other rules...
    assertTrue(checkWithOptionsV2(
            english, german, "We will will do so", disableAvsAn, nothing, false).contains("ENGLISH_WORD_REPEAT_RULE"));
    //..unless explicitly stated.
    assertTrue(!checkWithOptionsV2(
        english, german, "We will berate you", disableAvsAn, nothing, true).contains("BERATE"));
    
    
    //test if two rules get enabled as well
    String[] twoRules = {"EN_A_VS_AN", "ENGLISH_WORD_REPEAT_RULE"};
    
    String resultEn = checkWithOptionsV2(
            english, german, "This is an test. We will will do so.", twoRules, nothing, false);
    assertTrue("Result: " + resultEn, resultEn.contains("EN_A_VS_AN"));
    assertTrue("Result: " + resultEn, resultEn.contains("ENGLISH_WORD_REPEAT_RULE"));

    //check two disabled options
    String result3 = checkWithOptionsV2(
            english, german, "This is an test. We will will do so.", nothing, twoRules, false);
    assertFalse("Result: " + result3, result3.contains("EN_A_VS_AN"));
    assertFalse("Result: " + result3, result3.contains("ENGLISH_WORD_REPEAT_RULE"));
    
    //two disabled, one enabled, so enabled wins
    String result4 = checkWithOptionsV2(
            english, german, "This is an test. We will will do so.", disableAvsAn, twoRules, false);
    assertTrue("Result: " + result4, result4.contains("EN_A_VS_AN"));
    assertFalse("Result: " + result4, result4.contains("ENGLISH_WORD_REPEAT_RULE"));

    String result5 = checkV2(null, "This is a test of the language detection.");
    assertTrue("Result: " + result5, result5.contains("\"en-US\""));

    String result6 = checkV2(null, "This is a test of the language detection.", "&preferredVariants=de-DE,en-GB");
    assertTrue("Result: " + result6, result6.contains("\"en-GB\""));

    // fallback not working anymore, now giving confidence rating; tested in TextCheckerTest
    //String result7 = checkV2(null, "x");  // too short for auto-fallback, will use fallback
    //assertTrue("Result: " + result7, result7.contains("\"en-US\""));

    String res = check("text", "/v2/check", english, null, "A text.", "&sourceLanguage=de-DE&sourceText=Text");
    assertTrue(res.contains("DIFFERENT_PUNCTUATION"));   // bitext rule actually active
  }