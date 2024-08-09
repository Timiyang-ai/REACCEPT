public void unregister(final String ruleName){
        Objects.requireNonNull(ruleName);
        Rule rule = findRuleByName(ruleName);
        if(rule != null) {
            unregister(rule);
        }
    }