public static Collection select(Collection inputCollection, Predicate predicate) {
        ArrayList answer = new ArrayList(inputCollection.size());
        select(inputCollection, predicate, answer);
        return answer;
    }