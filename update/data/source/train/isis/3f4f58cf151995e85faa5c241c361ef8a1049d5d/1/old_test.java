@Test
    public void testClearAssociation() {
        IsisContext.getTransactionManager().startTransaction();
        ClearValueRequest request = new ClearValueRequest(session, "name", movieData);
		ClearValueResponse response = server.clearValue(request );
		final ObjectData[] updatesData = response.getUpdates();
        IsisContext.getTransactionManager().endTransaction();

        nameField.assertFieldEmpty(object);
        assertEquals(0, updatesData.length);
    }