@Test
    @Transactional
    public void testEnableGroup() {
        // Given
        assertFf4j.assertDisable(F2);
        // When
        testedStore.enableGroup(G0);
        // Then
        assertFf4j.assertEnable(F2);
        // Reinit
        testedStore.disable(F2);
    }