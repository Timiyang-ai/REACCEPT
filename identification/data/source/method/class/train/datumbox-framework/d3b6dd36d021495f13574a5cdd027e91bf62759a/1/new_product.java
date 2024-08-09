public static <T> void shuffle(T[] array) {
        //Implementing Fisher-Yates shuffle
        T tmp;
        Random rnd = RandomGenerator.getThreadLocalRandom();
        for (int i = array.length - 1; i > 0; --i) {
            int index = rnd.nextInt(i + 1);
            
            tmp = array[index];
            array[index] = array[i];
            array[i] = tmp;
        }
    }