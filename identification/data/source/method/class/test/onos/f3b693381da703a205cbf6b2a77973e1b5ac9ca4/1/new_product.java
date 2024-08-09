public static Criterion matchIPProtocol(short proto) {
        return new IPProtocolCriterion(proto);
    }