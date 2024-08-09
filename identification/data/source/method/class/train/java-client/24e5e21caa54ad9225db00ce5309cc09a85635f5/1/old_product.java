@SuppressWarnings({"rawtypes", "unchecked"}) public void replaceValue(String value) {
        ImmutableMap.Builder builder = ImmutableMap.builder();
        builder.put("id", getId()).put("value", new String[] {value});
        execute(MobileCommand.REPLACE_VALUE, builder.build());
    }