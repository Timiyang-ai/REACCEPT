@Nonnull
    public static RecordFormats selectForVersion( String storeVersion )
    {
        for ( RecordFormats format : allFormats() )
        {
            if ( format.storeVersion().equals( storeVersion ) )
            {
                return format;
            }
        }
        throw new IllegalArgumentException( "Unknown store version '" + storeVersion + "'" );
    }