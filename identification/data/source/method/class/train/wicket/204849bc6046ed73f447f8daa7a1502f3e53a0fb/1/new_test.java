@Test
	public void testGetCompatibilityScore()
	{
	    Url url = Url.parse(MOUNT_PATH + '/' + "MyPage");
	    MockWebRequest request = new MockWebRequest(url);
	    int score = encoder.getCompatibilityScore(request);
	    
	    assertEquals(4, score);
	    
	    url = Url.parse(MOUNT_PATH + "/foo/bar/" + "MyPage");
	    request = new MockWebRequest(url);
	    score = namedParametersEncoder.getCompatibilityScore(request);
	    
	    assertEquals(6, score);
	}