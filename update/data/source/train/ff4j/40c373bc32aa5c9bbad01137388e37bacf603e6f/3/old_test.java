@Test
    public void testDisableGroup() {
        // Given
        assertFf4j.assertThatFeatureIsEnabled(F4);
        // When
        testedStore.disableGroup(G1);
        // Then
        assertFf4j.assertThatFeatureIsDisabled(F4);
        // Cancel modifications
        testedStore.enable(F4);
        assertFf4j.assertThatFeatureIsEnabled(F4);
    }