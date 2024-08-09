@Test
    @Transactional
    public void testAddToGroup() {
        // Given
        assertFf4j.assertThatGroupHasSize(1, G0);
        // When
        testedStore.addToGroup(F1, G0);
        // Then
        assertFf4j.assertThatGroupHasSize(2, G0);
        // End, Return to initial state
        testedStore.removeFromGroup(F1, G0);
        assertFf4j.assertThatGroupHasSize(1, G0);
    }