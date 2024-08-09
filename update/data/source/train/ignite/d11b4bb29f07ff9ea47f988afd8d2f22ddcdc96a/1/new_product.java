@Override public void aggregate(IgniteModel<Vector, L> model, LabeledVector<L> vector) {
        L modelAns = model.predict(vector.features());
        L truth = vector.label();
        if (modelAns.equals(truth))
            validAnswersCnt++;
        totalNumberOfExamples++;
    }