public void register(Object rule) {
        Objects.requireNonNull(rule);
        rules.add(RuleProxy.asRule(rule));
    }