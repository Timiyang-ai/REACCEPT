public static Criterion matchIcmpCode(short icmpCode) {
        return new IcmpCodeCriterion(icmpCode);
    }