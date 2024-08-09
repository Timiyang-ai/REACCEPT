private static List<String> parseExecParameters(String paramText)
    {
        final String SafeParamStringValuePattern = "#(SQL_PARSER_SAFE_PARAMSTRING)";
        // Find all quoted strings.
        // Mask out strings that contain whitespace or commas
        // that must not be confused with parameter separators.
        // "Safe" strings that don't contain these characters don't need to be masked
        // but they DO need to be found and explicitly skipped so that their closing
        // quotes don't trigger a false positive for the START of an unsafe string.
        // Skipping is accomplished by resetting paramText to an offset substring
        // after copying the skipped (or substituted) text to a string builder.
        ArrayList<String> originalString = new ArrayList<>();
        Matcher stringMatcher = SingleQuotedString.matcher(paramText);
        StringBuilder safeText = new StringBuilder();
        while (stringMatcher.find()) {
            // Save anything before the found string.
            safeText.append(paramText.substring(0, stringMatcher.start()));
            String asMatched = stringMatcher.group();
            if (SingleQuotedStringContainingParameterSeparators.matcher(asMatched).matches()) {
                // The matched string is unsafe, provide cover for it in safeText.
                originalString.add(asMatched);
                safeText.append(SafeParamStringValuePattern);
            } else {
                // The matched string is safe. Add it to safeText.
                safeText.append(asMatched);
            }
            paramText = paramText.substring(stringMatcher.end());
            stringMatcher = SingleQuotedString.matcher(paramText);
        }
        // Save anything after the last found string.
        safeText.append(paramText);

        ArrayList<String> params = new ArrayList<>();
        int subCount = 0;
        int neededSubs = originalString.size();
        // Split the params at the separators
        String[] split = safeText.toString().split("[\\s,]+");
        for (String fragment : split) {
            if (fragment.isEmpty()) {
                continue; // ignore effects of leading or trailing separators
            }
            // Replace each substitution in order exactly once.
            if (subCount < neededSubs) {
                // Substituted strings will normally take up an entire parameter,
                // but some cases like parameters containing escaped single quotes
                // may require multiple serial substitutions.
                while (fragment.indexOf(SafeParamStringValuePattern) > -1) {
                    fragment = fragment.replace(SafeParamStringValuePattern,
                            originalString.get(subCount));
                    ++subCount;
                }
            }
            params.add(fragment);
        }
        assert(subCount == neededSubs);
        return params;
    }