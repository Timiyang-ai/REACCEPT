    @Test
    public void delete()
    {
        IdGeneratorImpl.createGenerator( fs, idGeneratorFile(), 0, false );
        IdGeneratorImpl idGenerator = new IdGeneratorImpl( fs, idGeneratorFile(), 10, 1000, false, IdType.NODE, () -> 0L );
        long id = idGenerator.nextId();
        idGenerator.nextId();
        idGenerator.freeId( id );
        idGenerator.close();
        idGenerator.delete();
        assertFalse( idGeneratorFile().exists() );
        IdGeneratorImpl.createGenerator( fs, idGeneratorFile(), 0, false );
        idGenerator = new IdGeneratorImpl( fs, idGeneratorFile(), 10, 1000, false, IdType.NODE, () -> 0L );
        assertEquals( id, idGenerator.nextId() );
        idGenerator.close();
    }