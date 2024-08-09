@Test
    public void testKnn() {
        System.out.println("knn");

        long time = System.currentTimeMillis();
        double recall = 0.0;
        for (int i = 0; i < testx.length; i++) {
            int k = 3;
            Neighbor[] n1 = lsh.knn(testx[i], k);
            Neighbor[] n2 = naive.knn(testx[i], k);
            int hit = 0;
            for (int m = 0; m < n1.length && n1[m] != null; m++) {
                for (int n = 0; n < n2.length && n2[n] != null; n++) {
                    if (n1[m].index == n2[n].index) {
                        hit++;
                        break;
                    }
                }
            }
            recall += 1.0 * hit / k;
        }

        recall /= testx.length;
        System.out.println("recall is " + recall);
        System.out.println("time is " + (System.currentTimeMillis() - time) / 1000.0);
    }