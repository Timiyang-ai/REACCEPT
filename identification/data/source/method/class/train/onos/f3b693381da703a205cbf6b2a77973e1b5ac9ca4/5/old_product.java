public static Criterion matchLambda(Short lambda) {
        return new LambdaCriterion(lambda, Type.OCH_SIGID);
    }