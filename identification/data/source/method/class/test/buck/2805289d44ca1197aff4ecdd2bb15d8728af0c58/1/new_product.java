public static <T> T replace(
      ImmutableMap<String, MacroReplacer<T>> replacers,
      String blob,
      boolean resolveEscaping,
      MacroCombiner<T> combiner)
      throws MacroException {
    // Iterate over all macros found in the string, expanding each found macro.
    int lastEnd = 0;
    MacroFinderAutomaton matcher = new MacroFinderAutomaton(blob);
    while (matcher.hasNext()) {
      MacroMatchResult matchResult = matcher.next();
      // Add everything from the original string since the last match to this one.
      combiner.addString(blob.substring(lastEnd, matchResult.getStartIndex()));

      // If the macro is escaped, add the macro text (but omit the escaping backslash)
      if (matchResult.isEscaped()) {
        combiner.addString(
            blob.substring(
                matchResult.getStartIndex() + (resolveEscaping ? 1 : 0),
                matchResult.getEndIndex()));
      } else {
        // Call the relevant expander and add the expanded value to the string.
        MacroReplacer<T> replacer = replacers.get(matchResult.getMacroType());
        if (replacer == null) {
          throw new MacroException(
              String.format(
                  "expanding %s: no such macro \"%s\"",
                  blob.substring(matchResult.getStartIndex(), matchResult.getEndIndex()),
                  matchResult.getMacroType()));
        }
        try {
          combiner.add(replacer.replace(matchResult));
        } catch (MacroException e) {
          throw new MacroException(
              String.format(
                  "expanding %s: %s",
                  blob.substring(matchResult.getStartIndex(), matchResult.getEndIndex()),
                  e.getMessage()),
              e);
        }
      }
      lastEnd = matchResult.getEndIndex();
    }
    // Append the remaining part of the original string after the last match.
    combiner.addString(blob.substring(lastEnd, blob.length()));
    return combiner.build();
  }