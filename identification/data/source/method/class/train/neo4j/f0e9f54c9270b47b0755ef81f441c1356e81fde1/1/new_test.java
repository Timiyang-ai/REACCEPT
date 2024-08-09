    @Test
    public void listFolders() throws IOException
    {
        File indexFolder = storage.getIndexFolder();
        fs.mkdirs( indexFolder );

        createRandomFile( indexFolder );
        createRandomFile( indexFolder );
        File folder1 = createRandomFolder( indexFolder );
        File folder2 = createRandomFolder( indexFolder );

        List<File> folders = storage.listFolders();

        assertEquals( asSet( folder1, folder2 ), new HashSet<>( folders ) );
    }