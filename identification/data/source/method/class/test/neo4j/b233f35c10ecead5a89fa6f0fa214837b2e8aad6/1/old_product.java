public static RecordFormats selectForVersion( String storeVersion )
    {
        Iterable<RecordFormats> currentFormats =
                map( RecordFormats.Factory::newInstance, Service.load( RecordFormats.Factory.class ) );
        for ( RecordFormats format : concat( KNOWN_FORMATS, currentFormats ) )
        {
            if ( format.storeVersion().equals( storeVersion ) )
            {
                return format;
            }
        }
        throw new IllegalArgumentException( "Unknown store version '" + storeVersion + "'" );
    }