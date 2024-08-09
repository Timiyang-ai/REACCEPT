@Test
    @Transactional
    public void testDisableGroup() {
        // Given
        assertFf4j.assertEnable(F4);
        // When
        testedStore.disableGroup(G1);
        // Then
        assertFf4j.assertDisable(F4);
        // Cancel modifications
        testedStore.enable(F4);
        assertFf4j.assertEnable(F4);
    }