public List<OffsetPosition> inAbbrevJournalNames(String s) {
        if (abbrevJournalPattern == null) {
            initJournals();
        }
        List<OffsetPosition> results = abbrevJournalPattern.matcher(s);
        return results;
    }