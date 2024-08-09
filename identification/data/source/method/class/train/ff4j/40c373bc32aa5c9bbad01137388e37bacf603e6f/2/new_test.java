@Test
    public void testExistGroup() {
        // Given
        assertFf4j.assertThatGroupExist(G1);
        assertFf4j.assertThatGroupDoesNotExist(G_DOESNOTEXIST);
        // Then
        Assert.assertTrue(testedStore.existGroup(G1));
        Assert.assertFalse(testedStore.existGroup(G_DOESNOTEXIST));
    }