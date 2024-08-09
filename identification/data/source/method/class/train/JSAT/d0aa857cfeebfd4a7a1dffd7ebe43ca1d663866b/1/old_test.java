@Test
    public void testThreshholdSplit()
    {
        System.out.println("threshholdSplit");
        Distribution dist1 = new Normal(0, 1);
        Distribution dist2 = new Normal(3, 2);
        PairedReturn<Integer, Double> ret = DecisionStump.threshholdSplit(dist1, dist2);
        assertEquals(0, (int) ret.getFirstItem());
        assertEquals(1.418344988105127, ret.getSecondItem(), 1e-6);
        
        ret = DecisionStump.threshholdSplit(dist2, dist1);
        assertEquals(1, (int) ret.getFirstItem());
        assertEquals(1.418344988105127, ret.getSecondItem(), 1e-6);
        
        dist2 = new Normal(3, 1);
        ret = DecisionStump.threshholdSplit(dist2, dist1);
        assertEquals(1, (int) ret.getFirstItem());
        assertEquals(1.5, ret.getSecondItem(), 1e-6);
        
        try
        {
            dist1 = new Normal(0, 2);
            dist2 = new Normal(3, 10);
            ret = DecisionStump.threshholdSplit(dist2, dist1);
            assertEquals(1, (int) ret.getFirstItem());
            assertEquals(4.896411863121647, ret.getSecondItem(), 1e-6);//This is the spliting point, but we dont expect it to get it 
        }
        catch(ArithmeticException ex)
        {
            
        }
        
        dist1 = new Normal(0, 1);
        dist2 = new Normal(100, 1);
        ret = DecisionStump.threshholdSplit(dist1, dist2);
        assertEquals(0, (int) ret.getFirstItem());
        assertEquals(50, ret.getSecondItem(), 1e-6);
        
        dist1 = new Normal(0, 1);
        dist2 = new Normal(100, 1);
        ret = DecisionStump.threshholdSplit(dist2, dist1);
        assertEquals(1, (int) ret.getFirstItem());
        assertEquals(50, ret.getSecondItem(), 1e-6);
    }