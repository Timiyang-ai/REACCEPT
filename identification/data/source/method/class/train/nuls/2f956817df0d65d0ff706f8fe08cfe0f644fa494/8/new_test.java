    public void saveBlockHeader() {
        Result result = service.saveBlockHeader(entity);
        assertTrue(result.isSuccess());
    }