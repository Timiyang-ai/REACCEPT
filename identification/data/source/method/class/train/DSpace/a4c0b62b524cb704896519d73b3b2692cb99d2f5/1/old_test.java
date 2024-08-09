@Test
    public void testFindByTypesAndLabels() throws Exception {
        assertEquals("TestFindbyTypesAndLabels 0", relationshipType, relationshipTypeService
                .findbyTypesAndLabels(context, entityTypeTwo, entityTypeOne, "isAuthorOfPublication",
                        "isPublicationOfAuthor"));
    }