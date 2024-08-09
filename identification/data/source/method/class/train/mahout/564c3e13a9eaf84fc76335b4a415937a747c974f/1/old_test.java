@Test
  public void testGetNumberOfClusters() throws Exception {
    List<VectorWritable> points = getPointsWritable(REFERENCE);
    
    Path pointsPath = getTestTempDirPath("points");
    conf = new Configuration();
    ClusteringTestUtils.writePointsToFile(points, new Path(pointsPath, "file1"), fs, conf);
    ClusteringTestUtils.writePointsToFile(points, new Path(pointsPath, "file2"), fs, conf);
    
    outputPathForCanopy = getTestTempDirPath("canopy");
    outputPathForKMeans = getTestTempDirPath("kmeans");
    
    topLevelClustering(pointsPath, conf);
    
    int numberOfClusters = ClusterCountReader.getNumberOfClusters(outputPathForKMeans, conf);
    Assert.assertEquals(2, numberOfClusters);
    verifyThatNumberOfClustersIsCorrect(conf, new Path(outputPathForKMeans, new Path("clusteredPoints")));
    
  }