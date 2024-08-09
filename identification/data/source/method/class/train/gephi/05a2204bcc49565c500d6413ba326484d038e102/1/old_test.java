@Test
    public void testAverage() {
        assertEquals(MathUtils.average(new Integer[]{1,2,3,4,5,6,7,8,9,10}), new BigDecimal("5.5"));
        assertEquals(MathUtils.average(new Short[]{33,null,67,null}), new BigDecimal("50"));
        assertEquals(MathUtils.average(new Long[]{10000000000000l,null,30000000000000l,null}), new BigDecimal("20000000000000"));
        BigInteger[] bigBigIntegerArrayTest=new BigInteger[420000];
        Arrays.fill(bigBigIntegerArrayTest, new BigInteger("42"));
        assertEquals(MathUtils.average(bigBigIntegerArrayTest), new BigDecimal("42"));
        ArrayList<BigDecimal> bigBigDecimalCollectionTest=new ArrayList<BigDecimal>();
        BigDecimal[] bigBigDecimalArrayTest=new BigDecimal[210000];
        Arrays.fill(bigBigDecimalArrayTest, new BigDecimal("2112"));
        bigBigDecimalCollectionTest.addAll(Arrays.asList(bigBigDecimalArrayTest));
        assertEquals(MathUtils.average(bigBigDecimalCollectionTest), new BigDecimal("2112"));
        assertEquals(MathUtils.average(new Number[]{Byte.valueOf((byte)33),Short.valueOf((short)6),67,45l,64466.1234d}), new BigDecimal("12923.42468"));
    }