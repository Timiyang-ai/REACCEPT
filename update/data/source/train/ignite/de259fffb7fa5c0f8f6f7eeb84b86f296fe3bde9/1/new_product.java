@Override public double[] data() {
        return MatrixUtil.flatten(data, StorageConstants.ROW_STORAGE_MODE);
    }