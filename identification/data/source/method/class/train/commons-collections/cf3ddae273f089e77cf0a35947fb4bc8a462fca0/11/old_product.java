public static Collection selectRejected(Collection inputCollection, Predicate predicate) {
        ArrayList answer = new ArrayList(inputCollection.size());
        selectRejected(inputCollection, predicate, answer);
        return answer;
    }