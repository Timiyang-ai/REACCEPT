public List<OffsetPosition> tokenPositionsAbbrevJournalNames(List<LayoutToken> s) {
        if (abbrevJournalPattern == null) {
            initJournals();
        }
        List<OffsetPosition> results = abbrevJournalPattern.matchLayoutToken(s);
        return results;
    }