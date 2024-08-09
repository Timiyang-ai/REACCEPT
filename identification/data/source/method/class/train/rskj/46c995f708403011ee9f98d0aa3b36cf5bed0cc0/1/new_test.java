    @Test
    public void updateBatch() {
        Map<ByteArrayWrapper, byte[]> updatedValues = generateRandomValuesToUpdate(CACHE_SIZE);

        keyValueDataSource.updateBatch(updatedValues, Collections.emptySet());

        if (withFlush) {
            keyValueDataSource.flush();
        }
        for (Map.Entry<ByteArrayWrapper, byte[]> updatedValue : updatedValues.entrySet()) {
            assertThat(keyValueDataSource.get(updatedValue.getKey().getData()), is(updatedValue.getValue()));
        }
    }