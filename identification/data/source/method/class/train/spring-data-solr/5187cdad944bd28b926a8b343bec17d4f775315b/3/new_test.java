@Test
	public void testCirteriasJoindWithOrShouldBeSiblingsOfCreatedCrotch() {

		Criteria c1 = new Criteria("field_1").startsWith("start").endsWith("end");
		Criteria c2 = new Criteria("field_2").startsWith("2start");
		Crotch crotch = c1.or(c2);

		Assert.assertThat(crotch.getSiblings(), IsIterableContainingInOrder.<Node> contains(c1, c2));
	}