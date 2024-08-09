public static Criterion matchOpticalSignalType(short sigType) {
        return new OpticalSignalTypeCriterion(sigType, Type.OCH_SIGTYPE);
    }