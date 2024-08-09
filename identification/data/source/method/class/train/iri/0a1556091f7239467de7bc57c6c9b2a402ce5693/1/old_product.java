private void checkReference(Hash reference, Map<Hash, Integer> rating)
            throws InvalidAlgorithmParameterException {
        if (!rating.containsKey(reference)) {
            throw new InvalidAlgorithmParameterException(REFERENCE_TRANSACTION_TOO_OLD);
        }
    }