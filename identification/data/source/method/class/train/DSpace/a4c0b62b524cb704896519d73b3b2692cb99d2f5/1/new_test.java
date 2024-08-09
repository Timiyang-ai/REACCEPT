@Test
    public void testFindByTypesAndLabels() throws Exception {
        assertEquals("TestFindbyTypesAndLabels 0", relationshipType, relationshipTypeService
                .findbyTypesAndTypeName(context, entityTypeTwo, entityTypeOne, "isAuthorOfPublication",
                        "isPublicationOfAuthor"));
    }