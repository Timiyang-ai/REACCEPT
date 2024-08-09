public static long createRandomWithLength(int length){
        Validate.isTrue(length > 0, "length:[%s] must >0", length);
        long num = 1;
        for (int i = 0; i < length; ++i){
            num = num * 10;
        }

        // 该值大于等于 0.0 且小于 1.0 正号的 double 值
        double random = JVM_RANDOM.nextDouble();
        random = random < 0.1 ? random + 0.1 : random;// 可能出现 0.09346924349151808
        return (long) (random * num);
    }