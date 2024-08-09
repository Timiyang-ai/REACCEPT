@Override
    public int compareTo(VulnerableSoftware vs) {
        int result = 0;
        final String[] left = this.getName().split(":");
        final String[] right = vs.getName().split(":");
        final int max = (left.length <= right.length) ? left.length : right.length;
        if (max > 0) {
            for (int i = 0; result == 0 && i < max; i++) {
                final String[] subLeft = left[i].split("\\.");
                final String[] subRight = right[i].split("\\.");
                final int subMax = (subLeft.length <= subRight.length) ? subLeft.length : subRight.length;
                if (subMax > 0) {
                    for (int x = 0; result == 0 && x < subMax; x++) {
                        if (isPositiveInteger(subLeft[x]) && isPositiveInteger(subRight[x])) {
                            final int iLeft = Integer.parseInt(subLeft[x]);
                            final int iRight = Integer.parseInt(subRight[x]);
                            if (iLeft != iRight) {
                                if (iLeft > iRight) {
                                    result = 2;
                                } else {
                                    result = -2;
                                }
                            }
                        } else {
                            result = subLeft[x].compareToIgnoreCase(subRight[x]);
                        }
                    }
                    if (result == 0) {
                        if (subLeft.length > subRight.length) {
                            result = 2;
                        }
                        if (subRight.length > subLeft.length) {
                            result = -2;
                        }
                    }
                } else {
                    result = left[i].compareToIgnoreCase(right[i]);
                }
            }
            if (result == 0) {
                if (left.length > right.length) {
                    result = 2;
                }
                if (right.length > left.length) {
                    result = -2;
                }
            }
        } else {
            result = this.getName().compareToIgnoreCase(vs.getName());
        }
        return result;
    }