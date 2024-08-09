    @Test
    public void getWhereStringForFilter_equals() {
        StatementHelper sh = mockedStatementHelper("Fido");
        Equal f = new Equal("NAME", "Fido");
        assertEquals("\"NAME\" = ?",
                QueryBuilder.getWhereStringForFilter(f, sh));
        EasyMock.verify(sh);
    }