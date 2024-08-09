@Test
    @Transactional
    public void testRemoveFromGroup() {
        // Given
        assertFf4j.assertThatGroupHasSize(2, G1);
        // When
        testedStore.removeFromGroup(F3, G1);
        // Then
        assertFf4j.assertThatGroupHasSize(1, G1);
        // End, Return to initial state
        testedStore.addToGroup(F3, G1);
        assertFf4j.assertThatGroupHasSize(2, G1);
    }