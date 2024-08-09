Rule createRule(
      Package.Builder pkgBuilder,
      Label ruleLabel,
      AttributeValuesMap attributeValues,
      EventHandler eventHandler,
      @Nullable FuncallExpression ast,
      Location location,
      AttributeContainer attributeContainer)
      throws LabelSyntaxException, InterruptedException {
    Rule rule = createRuleUnchecked(
        pkgBuilder, ruleLabel, attributeValues, eventHandler, ast, location, attributeContainer);
    rule.checkForNullLabels();
    return rule;
  }