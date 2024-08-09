@Test
    public void testFindByLeftOrRightLabel() throws Exception {
        assertEquals("TestFindByLeftOrRightLabel 0", relationshipTypeList, relationshipTypeService.
                findByLeftOrRightLabel(context, "isAuthorOfPublication", -1, -1));
    }