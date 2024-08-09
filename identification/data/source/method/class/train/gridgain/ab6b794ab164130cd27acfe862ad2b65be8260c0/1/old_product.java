@Override public void aggregate(IgniteModel<Vector, Double> model, LabeledVector<Double> vector) {
        Double modelAns = model.predict(vector.features());
        Double realAns = vector.label();

        if (modelAns.equals(falseLabel) && realAns.equals(falseLabel))
            trueNegative += 1;
        else if (modelAns.equals(falseLabel) && realAns.equals(truthLabel))
            falseNegative += 1;
        else if (modelAns.equals(truthLabel) && realAns.equals(truthLabel))
            truePositive += 1;
        else if (modelAns.equals(truthLabel) && realAns.equals(falseLabel))
            falsePositive += 1;
    }