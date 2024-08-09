    @Test
    public void moveFile() throws Exception
    {
        File file = touchFile( "source" );
        File targetDir = directory( "dir" );

        File newLocationOfFile = new File( targetDir, "new-name" );
        FileUtils.moveFile( file, newLocationOfFile );
        assertTrue( newLocationOfFile.exists() );
        assertFalse( file.exists() );
        assertEquals( newLocationOfFile, targetDir.listFiles()[0] );
    }