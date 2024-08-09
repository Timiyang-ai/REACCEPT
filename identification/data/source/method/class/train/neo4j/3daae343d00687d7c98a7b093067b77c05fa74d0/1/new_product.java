public void augmentDefaults( Map<String,String> additionalDefaults ) throws InvalidSettingException
    {
        additionalDefaults.forEach( this::augmentDefaults );
    }