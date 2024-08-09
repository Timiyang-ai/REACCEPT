public static Criterion matchIcmpType(Byte icmpType) {
        return new IcmpTypeCriterion(icmpType);
    }