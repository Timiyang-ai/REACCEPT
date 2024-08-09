@Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AccessControlContext) {
            AccessControlContext that = (AccessControlContext) obj;
            if (!(PolicyUtils.matchSubset(context, that.context) && PolicyUtils
                    .matchSubset(that.context, context))) {
                return false;
            }
            // BEGIN android-changed
            if(combiner != null) {
                return combiner.equals(that.combiner);
            }
            return that.combiner == null;
            // END android-changed
        }
        return false;
    }