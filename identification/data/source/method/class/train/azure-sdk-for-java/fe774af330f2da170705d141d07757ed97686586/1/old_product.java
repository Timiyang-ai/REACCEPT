@ServiceMethod(returns = ReturnType.SINGLE)
    public ConfigurationSetting setReadOnly(String key, String label) {
        return setReadOnlyWithResponse(new ConfigurationSetting().setKey(key).setLabel(label), Context.NONE).getValue();
    }