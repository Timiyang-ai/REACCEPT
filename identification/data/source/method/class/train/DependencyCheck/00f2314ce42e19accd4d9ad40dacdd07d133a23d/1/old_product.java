public List<SuppressionRule> parseSuppressionRules(InputStream inputStream) throws SuppressionParseException, SAXException {
        return parseSuppressionRules(inputStream, SUPPRESSION_SCHEMA);
    }