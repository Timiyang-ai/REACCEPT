  private boolean match(BitextPatternRule rule, String src, String trg,
      JLanguageTool srcLanguageTool,
      JLanguageTool trgLanguageTool) throws IOException {
    AnalyzedSentence srcText = srcLanguageTool.getAnalyzedSentence(src);
    AnalyzedSentence trgText = trgLanguageTool.getAnalyzedSentence(trg);
    RuleMatch[] matches = rule.match(srcText, trgText);
    return matches.length > 0;
  }