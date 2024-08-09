  private boolean match(Rule rule, String sentence, JLanguageTool lt) throws IOException {
    AnalyzedSentence analyzedSentence = lt.getAnalyzedSentence(sentence);
    RuleMatch[] matches = rule.match(analyzedSentence);
    return matches.length > 0;
  }