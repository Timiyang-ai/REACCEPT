public void unregister(Object rule) {
        rules.remove(RuleProxy.asRule(rule));
    }