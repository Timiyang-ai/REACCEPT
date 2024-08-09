public HashCodeBuilder append(final double[] array) {
        if (array == null) {
            iTotal = iTotal * iConstant;
        } else {
            for (double element : array) {
                append(element);
            }
        }
        return this;
    }