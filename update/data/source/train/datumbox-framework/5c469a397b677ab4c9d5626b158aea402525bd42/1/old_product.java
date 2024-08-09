public static <T> void shuffle(T[] array) {
        //Implementing Fisher–Yates shuffle
        Random rnd = RandomValue.getRandomGenerator();
        T tmp;
        for (int i = array.length - 1; i > 0; --i) {
            int index = rnd.nextInt(i + 1);
            
            // Simple swap
            tmp = array[index];
            array[index] = array[i];
            array[i] = tmp;
        }
    }