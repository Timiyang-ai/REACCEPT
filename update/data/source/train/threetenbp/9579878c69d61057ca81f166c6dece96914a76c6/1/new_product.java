public void checkIntValue() {
        DateTimeRuleRange range = getValueRange();
        if (range.isIntValue() == false) {
            throw new CalendricalRuleException("Rule does not specify an int value: " + getName(), this);
        }
    }