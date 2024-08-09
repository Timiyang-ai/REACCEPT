    @Test
    public void isOnlyManager() throws Exception {
        // Given
        // When
        boolean yobiIsOnlyManager = Project.isOnlyManager(2l);
        boolean EungjunIsOnlyManager = Project.isOnlyManager(5l);
        // Then
        assertTrue(yobiIsOnlyManager);
        assertFalse(EungjunIsOnlyManager);
    }