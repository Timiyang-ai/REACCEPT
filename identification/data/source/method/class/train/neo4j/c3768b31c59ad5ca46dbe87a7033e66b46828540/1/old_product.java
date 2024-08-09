public List<Directory> openIndexDirectories() throws IOException
    {
        List<Directory> directories = new ArrayList<>();
        try
        {
            File[] files = fileSystem.listFiles( getIndexFolder() );
            for ( File file : files )
            {
                if ( fileSystem.isDirectory( file ) )
                {
                    directories.add( directoryFactory.open( file ) );
                }
            }
        }
        catch ( IOException oe )
        {
            try
            {
                IOUtils.closeAll( directories );
            }
            catch ( Exception ce )
            {
                oe.addSuppressed( ce );
            }
            throw oe;
        }
        return directories;
    }