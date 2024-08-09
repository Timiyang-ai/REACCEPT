@ServiceMethod(returns = ReturnType.SINGLE)
    public ConfigurationSetting setReadOnly(String key, String label, boolean isReadOnly) {
        return setReadOnlyWithResponse(new ConfigurationSetting().setKey(key).setLabel(label), isReadOnly, Context.NONE)
            .getValue();
    }