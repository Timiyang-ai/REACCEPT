public void augmentDefaults( Setting<?> setting, String value ) throws InvalidSettingException
    {
        overriddenDefaults.put( setting.name(), value );
        params.putIfAbsent( setting.name(), value );
        parsedParams.remove( setting.name() );
    }