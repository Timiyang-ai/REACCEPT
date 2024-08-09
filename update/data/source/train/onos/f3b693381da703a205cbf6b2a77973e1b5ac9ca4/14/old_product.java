public static Criterion matchIcmpCode(Byte icmpCode) {
        return new IcmpCodeCriterion(icmpCode);
    }