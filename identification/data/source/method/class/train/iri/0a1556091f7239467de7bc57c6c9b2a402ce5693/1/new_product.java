@VisibleForTesting
    void checkReference(Hash reference, Map<Hash, Integer> rating, WalkValidator walkValidator)
            throws Exception {
        if (config.getAlpha() != 0 && !rating.containsKey(reference)) {
            throw new InvalidAlgorithmParameterException(REFERENCE_TRANSACTION_TOO_OLD);
        }
        else if (config.getAlpha() == 0 && !walkValidator.isValid(reference)) {
            throw new InvalidAlgorithmParameterException(REFERENCE_TRANSACTION_IS_INVALID);
        }
    }