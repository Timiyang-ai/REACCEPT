@Test
	public void testlike() {
		String sqlPart = Condition.instance().like("default", "default", SQLlikeType.DEFAULT).like("left","left", SQLlikeType.LEFT).like("right","right", SQLlikeType.RIGHT).toString();
		System.out.println("sql ==> " + sqlPart);
		Assert.assertEquals("WHERE (default LIKE '%default%' AND left LIKE '%left' AND right LIKE 'right%')", sqlPart);
	}