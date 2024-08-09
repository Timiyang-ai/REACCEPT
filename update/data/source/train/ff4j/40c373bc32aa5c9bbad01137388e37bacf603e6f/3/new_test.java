@Test
    public void testDisableGroup() {
        // Given
        assertFf4j.assertThatFeatureIsEnabled(F4);
        assertFf4j.assertThatFeatureIsInGroup(F4, G1);
        // When
        testedStore.disableGroup(G1);
        // Then
        assertFf4j.assertThatFeatureIsDisabled(F4);
        // Rollback modifications
        testedStore.enable(F4);
        assertFf4j.assertThatFeatureIsEnabled(F4);
    }