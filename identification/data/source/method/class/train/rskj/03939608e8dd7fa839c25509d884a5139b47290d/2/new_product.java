public static Sha256Hash combineLeftRight(Sha256Hash left, Sha256Hash right) {
        byte[] leftBytes = left.getBytes();
        byte[] rightBytes = right.getBytes();
        checkNotAValid64ByteTransaction(leftBytes, rightBytes);
        return Sha256Hash.wrapReversed(
                Sha256Hash.hashTwice(
                    reverseBytes(leftBytes), 0, 32,
                    reverseBytes(rightBytes), 0, 32
                )
        );
    }