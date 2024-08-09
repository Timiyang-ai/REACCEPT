@Test
	public void testlike() {
		String sqlPart = Condition.instance().like("default", "default", SqlLike.DEFAULT).like("left","left", SqlLike.LEFT).like("right","right", SqlLike.RIGHT).toString();
		System.out.println("sql ==> " + sqlPart);
		Assert.assertEquals("WHERE (default LIKE '%default%' AND left LIKE '%left' AND right LIKE 'right%')", sqlPart);
	}