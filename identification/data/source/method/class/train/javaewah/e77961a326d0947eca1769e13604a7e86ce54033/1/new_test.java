@Test
    public void testClear() {
        System.out.println("testing Clear");
        EWAHCompressedBitmap32 bitmap = new EWAHCompressedBitmap32();
        bitmap.set(5);
        bitmap.clear();
        bitmap.set(7);
        Assert.assertTrue(1 == bitmap.cardinality());
        Assert.assertTrue(1 == bitmap.toList().size());
        Assert.assertTrue(1 == bitmap.toArray().length);
        Assert.assertTrue(7 == bitmap.toList().get(0));
        Assert.assertTrue(7 == bitmap.toArray()[0]);
        bitmap.clear();
        bitmap.set(5000);
        Assert.assertTrue(1 == bitmap.cardinality());
        Assert.assertTrue(1 == bitmap.toList().size());
        Assert.assertTrue(1 == bitmap.toArray().length);
        Assert.assertTrue(5000 == bitmap.toList().get(0));
        bitmap.set(5001);
        bitmap.set(5005);
        bitmap.set(5100);
        bitmap.set(5500);
        bitmap.clear();
        bitmap.set(5);
        bitmap.set(7);
        bitmap.set(1000);
        bitmap.set(1001);
        Assert.assertTrue(4 == bitmap.cardinality());
        List<Integer> positions = bitmap.toList();
        Assert.assertTrue(4 == positions.size());
        Assert.assertTrue(5 == positions.get(0));
        Assert.assertTrue(7 == positions.get(1));
        Assert.assertTrue(1000 == positions.get(2));
        Assert.assertTrue(1001 == positions.get(3));
    }