public static Collection collect(Collection inputCollection, Transformer transformer) {
        ArrayList answer = new ArrayList(inputCollection.size());
        collect(inputCollection, transformer, answer);
        return answer;
    }