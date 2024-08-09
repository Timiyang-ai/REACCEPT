public static Criterion matchIcmpType(short icmpType) {
        return new IcmpTypeCriterion(icmpType);
    }