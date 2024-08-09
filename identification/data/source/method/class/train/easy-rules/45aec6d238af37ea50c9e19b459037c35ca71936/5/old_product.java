public void unregister(final String ruleName){
        Rule rule = findRuleByName(ruleName);
        if(rule != null) {
            unregister(rule);
        }
    }