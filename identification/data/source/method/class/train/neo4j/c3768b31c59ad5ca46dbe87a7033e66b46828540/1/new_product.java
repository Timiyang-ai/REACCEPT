public Map<File, Directory> openIndexDirectories() throws IOException
    {
        Map<File, Directory> directories = new LinkedHashMap<>();
        try
        {
            File[] files = fileSystem.listFiles( getIndexFolder() );
            for ( File file : files )
            {
                if ( fileSystem.isDirectory( file ) )
                {
                    directories.put( file, directoryFactory.open( file ) );
                }
            }
        }
        catch ( IOException oe )
        {
            try
            {
                IOUtils.closeAll( directories.values() );
            }
            catch ( Exception ce )
            {
                oe.addSuppressed( ce );
            }
            throw oe;
        }
        return directories;
    }