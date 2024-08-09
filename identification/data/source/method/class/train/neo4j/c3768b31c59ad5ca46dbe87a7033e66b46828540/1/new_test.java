    @Test
    public void openIndexDirectories() throws IOException
    {
        File indexFolder = storage.getIndexFolder();
        createRandomLuceneDir( indexFolder ).close();
        createRandomLuceneDir( indexFolder ).close();

        Map<File,Directory> directories = storage.openIndexDirectories();
        try
        {
            assertEquals( 2, directories.size() );
            for ( Directory dir : directories.values() )
            {
                assertFalse( ArrayUtil.isEmpty( dir.listAll() ) );
            }
        }
        finally
        {
            IOUtils.closeAll( directories.values() );
        }
    }