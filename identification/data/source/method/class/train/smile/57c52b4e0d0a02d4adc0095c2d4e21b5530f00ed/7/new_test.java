@Test
    public void testF() {
        System.out.println("f");
        try {
            ArffParser parser = new ArffParser();
            AttributeDataset data = parser.parse(smile.data.parser.IOUtils.getTestDataFile("weka/regression/abalone.arff"));
            double[][] x = data.toArray(new double[data.size()][]);
            
            FeatureSet<double[]> features = new FeatureSet<>();
            features.add(new Nominal2Binary(data.attributes()));
            features.add(new NumericAttributeFeature(data.attributes(), 0.05, 0.95, x));
            
            AttributeDataset dataset = features.f(data);
            assertEquals(data.size(), dataset.size());
            assertEquals(data.getName(), dataset.getName());
            assertEquals(data.getDescription(), dataset.getDescription());
            
            Attribute[] attributes = features.attributes();
            for (int i = 0; i < attributes.length; i++) {
                assertEquals(attributes[i].getName(), dataset.attributes()[i].getName());
                assertEquals(attributes[i].getType(), dataset.attributes()[i].getType());
            }
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }