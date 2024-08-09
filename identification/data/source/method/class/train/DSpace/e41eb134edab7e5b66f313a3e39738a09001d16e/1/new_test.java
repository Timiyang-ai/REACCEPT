@Test
    public void testDelete_Context_DSpaceObject()
            throws Exception
    {
        System.out.println("delete 2");

        DataCiteIdentifierProvider instance = new DataCiteIdentifierProvider();

        DSpaceObject dso = newItem(context);

        // Ensure that it has multiple DOIs (ooo, bad boy!)
        String id1 = UUID.randomUUID().toString();
        String id2 = UUID.randomUUID().toString();
        instance.reserve(context, dso, id1);
        instance.reserve(context, dso, id2);

        // Test deletion
        try {
            instance.delete(context, dso);
        } catch (IdentifierException e) {
            // Creation of the Item registers a "public" identifier, which can't be deleted.
            assertEquals("Unexpected exception", "1 identifiers could not be deleted.", e.getMessage());
        }

        // See if those identifiers were really deleted.
        ItemIterator found;
        found = Item.findByMetadataField(context,
                DataCiteIdentifierProvider.MD_SCHEMA,
                DataCiteIdentifierProvider.DOI_ELEMENT,
                DataCiteIdentifierProvider.DOI_QUALIFIER, id1);
        assertFalse("A test identifier is still present", found.hasNext());

        found = Item.findByMetadataField(context,
                DataCiteIdentifierProvider.MD_SCHEMA,
                DataCiteIdentifierProvider.DOI_ELEMENT,
                DataCiteIdentifierProvider.DOI_QUALIFIER, id2);
        assertFalse("A test identifier is still present", found.hasNext());
    }