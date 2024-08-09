@Test
    public void testParseCSVFile() {
        logger.info("parseCSVFile");
        
        Configuration conf = TestUtils.getConfig();
        
        
        LinkedHashMap<String, TypeInference.DataType> headerDataTypes = new LinkedHashMap<>(); 
        headerDataTypes.put("city", TypeInference.DataType.CATEGORICAL);
        headerDataTypes.put("temperature", TypeInference.DataType.NUMERICAL);
        headerDataTypes.put("is_sunny", TypeInference.DataType.BOOLEAN);
        headerDataTypes.put("traffic_rank", TypeInference.DataType.ORDINAL);
        headerDataTypes.put("is_capital", TypeInference.DataType.BOOLEAN);
        headerDataTypes.put("name_of_port", TypeInference.DataType.CATEGORICAL);
        headerDataTypes.put("metro_population", TypeInference.DataType.NUMERICAL);
        
        Dataframe dataset;
        try (Reader fileReader = new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("datasets/cities.csv"), "UTF-8")) {
            dataset = Dataframe.Builder.parseCSVFile(fileReader, "metro_population", headerDataTypes, ',', '"', "\r\n", null, null, conf);
        }
        catch(UncheckedIOException | IOException ex) {
            logger.warn("Unable to download datasets, skipping test.");
            throw new RuntimeException(ex);
        }
        
        Dataframe expResult = new Dataframe(conf);
        
        AssociativeArray xData1 = new AssociativeArray();
        xData1.put("city", "Athens");
        xData1.put("temperature", 30.0);
        xData1.put("is_sunny", true);
        xData1.put("traffic_rank", (short)3);
        xData1.put("is_capital", true);
        xData1.put("name_of_port", "Piraeus");
        expResult.add(new Record(xData1, 3753783.0));
        
        AssociativeArray xData2 = new AssociativeArray();
        xData2.put("city", "London");
        xData2.put("temperature", 14.0);
        xData2.put("is_sunny", false);
        xData2.put("traffic_rank", (short)2);
        xData2.put("is_capital", true);
        xData2.put("name_of_port", "Port of London");
        expResult.add(new Record(xData2, 13614409.0));
        
        AssociativeArray xData3 = new AssociativeArray();
        xData3.put("city", "New York");
        xData3.put("temperature", -12.0);
        xData3.put("is_sunny", true);
        xData3.put("traffic_rank", (short)1);
        xData3.put("is_capital", false);
        xData3.put("name_of_port", "New York's port");
        expResult.add(new Record(xData3, null));
        
        AssociativeArray xData4 = new AssociativeArray();
        xData4.put("city", "Atlantis,	\"the lost city\"");
        xData4.put("temperature", null);
        xData4.put("is_sunny", null);
        xData4.put("traffic_rank", (short)4);
        xData4.put("is_capital", null);
        xData4.put("name_of_port", null);
        expResult.add(new Record(xData4, null));
        
        Iterator<Record> it1 = expResult.iterator();
        Iterator<Record> it2 = dataset.iterator();
        
        while(it1.hasNext() && it2.hasNext()) {
            assertEquals(it1.next().equals(it2.next()),true);
        }
        
        assertEquals(it1.hasNext(),it2.hasNext()); //check that both finished
        
        assertEquals(expResult.getYDataType(),dataset.getYDataType());
        
        assertEquals(expResult.getXDataTypes().equals(dataset.getXDataTypes()),true);
        
        expResult.delete();
        dataset.delete();
    }