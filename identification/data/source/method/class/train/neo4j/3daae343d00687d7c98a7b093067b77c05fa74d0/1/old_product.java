public Config augmentDefaults( Map<String,String> additionalDefaults ) throws InvalidSettingException
    {
        Map<String,String> params = new HashMap<>( this.params );
        additionalDefaults.forEach( params::putIfAbsent );
        Map<String,String> validSettings = migrateAndValidateSettings( params, false );
        replaceSettings( validSettings );
        return this;
    }