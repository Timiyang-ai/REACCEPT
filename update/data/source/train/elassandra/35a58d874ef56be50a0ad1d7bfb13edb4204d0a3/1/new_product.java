@Deprecated
    public UpdateRequest script(String script, ScriptService.ScriptType scriptType, @Nullable Map<String, Object> scriptParams) {
        this.script = new Script(script, scriptType, null, scriptParams);
        return this;
    }