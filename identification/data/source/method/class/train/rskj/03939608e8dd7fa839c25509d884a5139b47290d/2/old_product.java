public static Sha256Hash combineLeftRight(Sha256Hash left, Sha256Hash right) {
        return Sha256Hash.wrapReversed(
                Sha256Hash.hashTwice(
                    reverseBytes(left.getBytes()), 0, 32,
                    reverseBytes(right.getBytes()), 0, 32
                )
        );
    }