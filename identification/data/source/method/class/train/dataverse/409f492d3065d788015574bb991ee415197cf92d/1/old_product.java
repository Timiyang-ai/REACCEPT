public static String getPasswordRequirements(int minLength, int maxLength, List<CharacterRule> characterRules, int numberOfCharacteristics, int numberOfRepeatingCharactersAllowed, int goodStrength, boolean dictionaryEnabled) {
        String message = "Your password must contain:";
        message += "<ul>";
        String optionalGoodStrengthNote = "";
        if (goodStrength > 0) {
            optionalGoodStrengthNote = "( passwords of at least " + goodStrength + " characters are exempt from all other requirements)";
        }
        message += "<li>At least " + minLength + " characters" + optionalGoodStrengthNote + "</li>";
        message += "<li>At least " + numberOfCharacteristics + " of the following: " + getRequiredCharacters(characterRules) + "</li>";
        message += "</ul>";
        boolean repeatingDigitRuleEnabled = numberOfRepeatingCharactersAllowed > 0;
        boolean showMayNotBlock = repeatingDigitRuleEnabled || dictionaryEnabled;
        if (showMayNotBlock) {
            message += "It may not include:";
            message += "<ul>";
        }
        if (repeatingDigitRuleEnabled) {
            message += "<li>Number sequences of " + numberOfRepeatingCharactersAllowed + " or more numbers in a row</li>";
        }
        if (dictionaryEnabled) {
            message += "<li>Dictionary words or common acronyms of 5 or more letters</li>";
        }
        if (showMayNotBlock) {
            message += "</ul>";
        }
        return message;
    }