@Test
    public void testEnableGroup() {
        // Given
        assertFf4j.assertThatFeatureIsDisabled(F2);
        assertFf4j.assertThatFeatureIsInGroup(F2, G0);
        // When
        testedStore.enableGroup(G0);
        // Then
        assertFf4j.assertThatFeatureIsEnabled(F2);
        // Reinit
        testedStore.disable(F2);
    }