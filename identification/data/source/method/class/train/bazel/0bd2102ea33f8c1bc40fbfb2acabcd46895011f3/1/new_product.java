@Nullable
  public static Rule getRuleByName(final String ruleName, Environment env)
      throws ExternalPackageException, InterruptedException {

    List<Rule> rules =
        getRules(
            env,
            true,
            new Function<Package, Iterable<Rule>>() {
              @Nullable
              @Override
              public Iterable<Rule> apply(Package externalPackage) {
                Rule rule = externalPackage.getRule(ruleName);
                if (rule == null) {
                  return null;
                }
                return ImmutableList.of(rule);
              }
            });

    if (env.valuesMissing()) {
      return null;
    }
    if (rules == null || rules.isEmpty()) {
      throw new ExternalRuleNotFoundException(ruleName);
    }
    return Iterables.getFirst(rules, null);
  }