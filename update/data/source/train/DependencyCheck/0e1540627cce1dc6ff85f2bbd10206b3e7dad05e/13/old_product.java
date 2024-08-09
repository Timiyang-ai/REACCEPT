@Override
    public int compareTo(VulnerableSoftware vs) {
        int result = 0;
        final String[] left = StringUtils.split(this.name, ':');
        final String[] right = StringUtils.split(vs.getName(), ':');
        final int max = (left.length <= right.length) ? left.length : right.length;
        if (max > 0) {
            for (int i = 0; result == 0 && i < max; i++) {
                final String[] subLeft = split(left[i]);
                final String[] subRight = split(right[i]);
                final int subMax = (subLeft.length <= subRight.length) ? subLeft.length : subRight.length;
                if (subMax > 0) {
                    for (int x = 0; result == 0 && x < subMax; x++) {
                        if (isPositiveInteger(subLeft[x]) && isPositiveInteger(subRight[x])) {
                            try {
                                result = Long.valueOf(subLeft[x]).compareTo(Long.valueOf(subRight[x]));
                            } catch (NumberFormatException ex) {
                                //ignore the exception - they obviously aren't numbers
                                if (!subLeft[x].equalsIgnoreCase(subRight[x])) {
                                    result = subLeft[x].compareToIgnoreCase(subRight[x]);
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