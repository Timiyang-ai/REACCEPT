@Test
    public void testGetPasswordRequirements() {
        System.out.println("getPasswordRequirements");
        int minLength = 6;
        int maxLength = 0;
        List<CharacterRule> characterRules = PasswordValidatorUtil.getCharacterRules4dot0();
        int numberOfCharacteristics = 2;
        int numberOfRepeatingCharactersAllowed = 4;
        int goodStrength = 21;
        boolean dictionaryEnabled = true;
        List<String> errors = new ArrayList<>();
        System.out.println("---Show all");
        String req1 = PasswordValidatorUtil.getPasswordRequirements(minLength, maxLength, characterRules, numberOfCharacteristics, numberOfRepeatingCharactersAllowed, goodStrength, dictionaryEnabled, errors);
        System.out.println(HtmlPrinter.prettyPrint(req1));
        System.out.println("---Hide all");
        String req2 = PasswordValidatorUtil.getPasswordRequirements(minLength, maxLength, characterRules, numberOfCharacteristics, 0, 0, false, errors);
        System.out.println(HtmlPrinter.prettyPrint(req2));
        System.out.println("---Show may not include sequence");
        String req3 = PasswordValidatorUtil.getPasswordRequirements(minLength, maxLength, characterRules, numberOfCharacteristics, numberOfRepeatingCharactersAllowed, goodStrength, false, errors);
        System.out.println(HtmlPrinter.prettyPrint(req3));
        System.out.println("---Show may not dictionary");
        String req4 = PasswordValidatorUtil.getPasswordRequirements(minLength, maxLength, characterRules, numberOfCharacteristics, 0, goodStrength, true, errors);
        System.out.println(HtmlPrinter.prettyPrint(req4));
    }