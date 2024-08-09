    @Test
    public void wasDeleted() {
        final Map<String, DeleteResult> results = new HashMap<String, DeleteResult>();
        results.put("testString", DeleteResult.newInstance(1, "test_table"));

        final DeleteResults<String> deleteResults = DeleteResults.newInstance(results);

        assertThat(deleteResults.wasDeleted("testString")).isTrue();
        assertThat(deleteResults.wasDeleted("should not be deleted")).isFalse();

        assertThat(deleteResults.wasNotDeleted("testString")).isFalse();
        assertThat(deleteResults.wasNotDeleted("should not be deleted")).isTrue();
    }