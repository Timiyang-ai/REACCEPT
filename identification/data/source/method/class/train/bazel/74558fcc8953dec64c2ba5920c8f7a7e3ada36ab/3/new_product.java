<T> Rule createRule(
      Package.Builder pkgBuilder,
      Label ruleLabel,
      AttributeValues<T> attributeValues,
      EventHandler eventHandler,
      @Nullable FuncallExpression ast,
      Location location,
      AttributeContainer attributeContainer,
      boolean checkThirdPartyRulesHaveLicenses)
      throws LabelSyntaxException, InterruptedException, CannotPrecomputeDefaultsException {
    Rule rule = pkgBuilder.createRule(ruleLabel, this, location, attributeContainer);
    populateRuleAttributeValues(rule, pkgBuilder, attributeValues, eventHandler);
    checkAspectAllowedValues(rule, eventHandler);
    rule.populateOutputFiles(eventHandler, pkgBuilder);
    if (ast != null) {
      populateAttributeLocations(rule, ast);
    }
    checkForDuplicateLabels(rule, eventHandler);
    if (checkThirdPartyRulesHaveLicenses) {
      checkThirdPartyRuleHasLicense(rule, pkgBuilder, eventHandler);
    }
    checkForValidSizeAndTimeoutValues(rule, eventHandler);
    rule.checkValidityPredicate(eventHandler);
    rule.checkForNullLabels();
    return rule;
  }