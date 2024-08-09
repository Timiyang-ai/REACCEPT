public void unregister(Object rule) {
        Objects.requireNonNull(rule);
        rules.remove(RuleProxy.asRule(rule));
    }