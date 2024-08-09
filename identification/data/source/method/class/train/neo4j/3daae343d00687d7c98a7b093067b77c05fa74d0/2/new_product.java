public void augmentDefaults( Setting<?> setting, String value ) throws InvalidSettingException
    {
        nullSafePut( overriddenDefaults, setting.name(), value );
        if ( value != null )
        {
            params.putIfAbsent( setting.name(), value );
        }
        parsedParams.remove( setting.name() );
    }