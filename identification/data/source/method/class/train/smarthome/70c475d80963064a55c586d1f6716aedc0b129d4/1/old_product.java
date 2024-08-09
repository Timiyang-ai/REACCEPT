public static ValueSet fromString(String valueSetConfig) {
        Matcher valueSetMatch = VALUESET_PATTERN.matcher(valueSetConfig);
        if (valueSetMatch.matches()) {
            ValueSet step = new ValueSet(Integer.valueOf(valueSetMatch.group(1)),
                    Integer.valueOf(valueSetMatch.group(3)));
            for (String value : valueSetMatch.group(2).split(",")) {
                step.addValue(Integer.valueOf(value));
            }
            return step;
        } else {
            return null;
        }
    }