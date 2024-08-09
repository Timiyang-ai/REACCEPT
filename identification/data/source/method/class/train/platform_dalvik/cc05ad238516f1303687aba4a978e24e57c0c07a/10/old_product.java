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
            // 'combiner' is not taken into account - see the test 
            // AccessControllerTest.testEqualsObject_01
            return true;
        }
        return false;
    }