  private Rule createRule(
      RuleClass ruleClass, String name, Map<String, Object> attributeValues, Location location)
      throws LabelSyntaxException, InterruptedException, CannotPrecomputeDefaultsException {
    Package.Builder pkgBuilder = createDummyPackageBuilder();
    Label ruleLabel;
    try {
      ruleLabel = pkgBuilder.createLabel(name);
    } catch (LabelSyntaxException e) {
      throw new IllegalArgumentException("Rule has illegal label", e);
    }
    return ruleClass.createRule(
        pkgBuilder,
        ruleLabel,
        new BuildLangTypedAttributeValuesMap(attributeValues),
        reporter,
        location,
        new AttributeContainer(ruleClass),
        /*checkThirdPartyRulesHaveLicenses=*/ true);
  }