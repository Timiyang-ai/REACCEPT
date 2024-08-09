	@Test
	public void replaceAllTest() {
		//通过正则查找到字符串，然后把匹配到的字符串加入到replacementTemplate中，$1表示分组1的字符串
		//此处把1234替换为 ->1234<-
		String replaceAll = ReUtil.replaceAll(content, "(\\d+)", "->$1<-");
		Assert.assertEquals("ZZZaaabbbccc中文->1234<-", replaceAll);
	}