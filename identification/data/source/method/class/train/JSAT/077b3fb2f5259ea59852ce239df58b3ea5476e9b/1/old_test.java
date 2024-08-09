@Test
    public void testLoadR_File() throws Exception
    {
        System.out.println("loadR");
        
        List<String> testLines = new ArrayList<String>();
        List<Double> expetedLabel = new DoubleList();
        List<Vec> expectedVec = new ArrayList<Vec>();
        
        testLines.add("-1 2:3.0");//normal line
        expetedLabel.add(-1.0);
        expectedVec.add(DenseVector.toDenseVec( 0.0, 3.0, 0.0, 0.0, 0.0));
        
        testLines.add("1 1:3.0 "); //line ends in a space
        expetedLabel.add(1.0);
        expectedVec.add(DenseVector.toDenseVec( 3.0, 0.0, 0.0, 0.0, 0.0));
        
        testLines.add("-21 2:3.0 3:3.0 4:1.0");//normal line with many values
        expetedLabel.add(-21.0);
        expectedVec.add(DenseVector.toDenseVec( 0.0, 3.0, 3.0, 1.0, 0.0));
        
        testLines.add("-1 2:3.0     4:2.0");//extra spaces in between
        expetedLabel.add(-1.0);
        expectedVec.add(DenseVector.toDenseVec( 0.0, 3.0, 0.0, 2.0, 0.0));
        
        testLines.add("1");  ///empty line
        expetedLabel.add(1.0);
        expectedVec.add(DenseVector.toDenseVec( 0.0, 0.0, 0.0, 0.0, 0.0));
        
        testLines.add("2 "); // empty line with space 
        expetedLabel.add(2.0);
        expectedVec.add(DenseVector.toDenseVec( 0.0, 0.0, 0.0, 0.0, 0.0));
        
        testLines.add("3");
        expetedLabel.add(3.0);
        expectedVec.add(DenseVector.toDenseVec( 0.0, 0.0, 0.0, 0.0, 0.0));
        
        testLines.add("4");
        expetedLabel.add(4.0);
        expectedVec.add(DenseVector.toDenseVec( 0.0, 0.0, 0.0, 0.0, 0.0));
        
        testLines.add("-1 1:10 3:2.0   "); //extra spaces at the end
        expetedLabel.add(-1.0);
        expectedVec.add(DenseVector.toDenseVec( 10.0, 0.0, 2.0, 0.0, 0.0));
        
        testLines.add("2 2:3.0   3:3.0   5:1.0");//normal line with many values
        expetedLabel.add(2.0);
        expectedVec.add(DenseVector.toDenseVec( 0.0, 3.0, 3.0, 0.0, 1.0));
        
        
        String[] newLines = new String[]{"\n", "\n\r", "\r\n", "\n\r\n"};

        for (boolean endInNewLines : new boolean[]{true, false })
            for (String newLine : newLines)
                for (int i = 0; i < testLines.size(); i++)
                {
                    StringBuilder input = new StringBuilder();
                    for (int j = 0; j < i; j++)
                        input.append(testLines.get(j)).append(newLine);
                    input.append(testLines.get(i));
                    if (endInNewLines)
                        input.append(newLine);

                    RegressionDataSet dataSet = LIBSVMLoader.loadR(new StringReader(input.toString()), 0.5, 5);
                    
                    assertEquals(i + 1, dataSet.size());
                    for (int j = 0; j < i + 1; j++)
                    {
                        assertEquals(expetedLabel.get(j), dataSet.getTargetValue(j), 0.0);
                        assertTrue(expectedVec.get(j).equals(dataSet.getDataPoint(j).getNumericalValues()));
                    }
                    
                    //can I use the DataWriter to export and re-import the same data?
                    ByteArrayOutputStream out_tmp = new ByteArrayOutputStream();
                    DataWriter dw = LIBSVMLoader.getWriter(out_tmp, dataSet.getNumNumericalVars(), DataWriter.DataSetType.REGRESSION);
                    for(int k = 0; k < dataSet.size(); k++)
                        dw.writePoint(dataSet.getDataPoint(k), dataSet.getTargetValue(k));
                    dw.close();

                    RegressionDataSet dataSet2 = LIBSVMLoader.loadR(new StringReader(new String(out_tmp.toByteArray())), 0.5, 5);
                    
                    assertEquals(dataSet.size(), dataSet2.size());
                    for (int j = 0; j < i + 1; j++)
                    {
                        assertEquals(dataSet.getTargetValue(j), dataSet2.getTargetValue(j), 0.0);
                        assertTrue(dataSet2.getDataPoint(j).getNumericalValues().equals(dataSet.getDataPoint(j).getNumericalValues()));
                    }
                    
                }
    }