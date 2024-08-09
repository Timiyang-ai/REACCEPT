default List<ConfigOptions> getConfigOptions()
    {
        ArrayList<ConfigOptions> configOptions = new ArrayList<>();
        for ( Field f : getClass().getDeclaredFields() )
        {
            try
            {
                Object publicSetting = f.get( this );
                if ( publicSetting instanceof BaseSetting )
                {
                    BaseSetting setting = (BaseSetting) publicSetting;

                    final Description documentation = f.getAnnotation( Description.class );
                    if ( documentation != null )
                    {
                        setting.setDescription( documentation.value() );
                    }

                    final DocumentedDefaultValue defValue = f.getAnnotation( DocumentedDefaultValue.class );
                    if ( defValue != null )
                    {
                        setting.setDocumentedDefaultValue( defValue.value() );
                    }

                    final Deprecated deprecatedAnnotation = f.getAnnotation( Deprecated.class );
                    setting.setDeprecated( deprecatedAnnotation != null );

                    final ReplacedBy replacedByAnnotation = f.getAnnotation( ReplacedBy.class );
                    if ( replacedByAnnotation != null )
                    {
                        setting.setReplacement( replacedByAnnotation.value() );
                    }

                    final Internal internalAnnotation = f.getAnnotation( Internal.class );
                    setting.setInternal( internalAnnotation != null );

                    final Secret secretAnnotation = f.getAnnotation( Secret.class );
                    setting.makeSecret( secretAnnotation != null );

                    final Dynamic dynamicAnnotation = f.getAnnotation( Dynamic.class );
                    setting.setDynamic( dynamicAnnotation != null );
                }

                if ( publicSetting instanceof SettingGroup )
                {
                    SettingGroup setting = (SettingGroup) publicSetting;
                    configOptions.add( new ConfigOptions( setting ) );
                }
            }
            catch ( IllegalAccessException ignored )
            {
                // Field is private, ignore it
            }
        }
        return configOptions;
    }