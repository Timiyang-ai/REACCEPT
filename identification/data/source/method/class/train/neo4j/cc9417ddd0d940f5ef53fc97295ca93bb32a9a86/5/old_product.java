default List<ConfigOptions> getConfigOptions()
    {
        ArrayList<ConfigOptions> configOptions = new ArrayList<>();
        for ( Field f : getClass().getDeclaredFields() )
        {
            try
            {
                Object publicSetting = f.get( this );
                if ( publicSetting instanceof SettingGroup )
                {

                    final Description documentation = f.getAnnotation( Description.class );
                    final Optional<String> description;
                    if ( documentation == null )
                    {
                        description = Optional.empty();
                    }
                    else
                    {
                        description = Optional.of( documentation.value() );
                    }

                    final DocumentedDefaultValue defValue =
                            f.getAnnotation( DocumentedDefaultValue.class );
                    final Optional<String> documentedDefaultValue;
                    if ( defValue == null )
                    {
                        documentedDefaultValue = Optional.empty();
                    }
                    else
                    {
                        documentedDefaultValue = Optional.of( defValue.value() );
                    }

                    final Deprecated deprecatedAnnotation = f.getAnnotation( Deprecated.class );
                    final boolean deprecated = deprecatedAnnotation != null;

                    final ReplacedBy replacedByAnnotation = f.getAnnotation( ReplacedBy.class );
                    final Optional<String> replacement;
                    if (replacedByAnnotation == null )
                    {
                        replacement = Optional.empty();
                    }
                    else
                    {
                        replacement = Optional.of( replacedByAnnotation.value() );
                    }

                    configOptions.add( new ConfigOptions( (SettingGroup) publicSetting, description,
                            documentedDefaultValue, deprecated, replacement ) );
                }
            }
            catch ( IllegalAccessException ignored )
            {
                // Field is private, ignore it
                continue;
            }
        }
        return configOptions;
    }