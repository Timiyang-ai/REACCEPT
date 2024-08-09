public HashCodeBuilder append(final double[] array) {
        if (array == null) {
            iTotal = iTotal * iConstant;
        } else {
            for (final double element : array) {
                append(element);
            }
        }
        return this;
    }