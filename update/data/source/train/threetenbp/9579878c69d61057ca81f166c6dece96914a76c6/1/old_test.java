@Test(expectedExceptions=CalendricalRuleException.class)
    public void test_checkIntValue_valid() {
        if (rule().getRange().isIntValue()) {
            rule().checkIntValue();
            throw new CalendricalRuleException("Dummy", rule());
        } else {
            rule().checkIntValue();
        }
    }