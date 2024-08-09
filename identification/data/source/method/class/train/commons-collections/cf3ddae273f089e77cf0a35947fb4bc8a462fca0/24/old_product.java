public static Collection collect(Iterator inputIterator, Transformer transformer) {
        ArrayList answer = new ArrayList();
        collect(inputIterator, transformer, answer);
        return answer;
    }