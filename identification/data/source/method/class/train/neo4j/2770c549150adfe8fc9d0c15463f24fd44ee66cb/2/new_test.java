    @Test
    public void markAsOnline() throws IOException
    {
        index = createIndex();
        index.getIndexWriter().addDocument( newDocument() );
        index.markAsOnline();

        assertTrue( "Should have had online status set", index.isOnline() );
    }