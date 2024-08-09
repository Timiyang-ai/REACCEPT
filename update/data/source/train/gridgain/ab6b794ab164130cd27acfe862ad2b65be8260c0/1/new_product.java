@Override public void aggregate(IgniteModel<Vector, L> model, LabeledVector<L> vector) {
        L modelAns = model.predict(vector.features());
        L realAns = vector.label();

        if (modelAns.equals(falseLabel) && realAns.equals(falseLabel))
            trueNegative += 1;
        else if (modelAns.equals(falseLabel) && realAns.equals(truthLabel))
            falseNegative += 1;
        else if (modelAns.equals(truthLabel) && realAns.equals(truthLabel))
            truePositive += 1;
        else if (modelAns.equals(truthLabel) && realAns.equals(falseLabel))
            falsePositive += 1;
    }