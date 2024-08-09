public UpdateRequest script(String script, ScriptService.ScriptType scriptType, @Nullable Map<String, Object> scriptParams) {
        this.script = script;
        this.scriptType = scriptType;
        if (this.scriptParams != null) {
            this.scriptParams.putAll(scriptParams);
        } else {
            this.scriptParams = scriptParams;
        }
        return this;
    }