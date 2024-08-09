@Test
    public void testClearAssociation() {
        IsisContext.getTransactionManager().startTransaction();
        final ClearValueRequest request = new ClearValueRequest(session, "name", movieData);
        final ClearValueResponse response = server.clearValue(request);
        final ObjectData[] updatesData = response.getUpdates();
        IsisContext.getTransactionManager().endTransaction();

        nameField.assertFieldEmpty(object);
        assertEquals(0, updatesData.length);
    }