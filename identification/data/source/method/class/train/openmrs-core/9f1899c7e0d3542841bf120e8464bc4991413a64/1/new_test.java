	@Test
	public void encrypt_shouldEncryptShortAndLongText() {
		// small text
		String expected = "a";
		String encrypted = Security.encrypt(expected);
		Assert.assertTrue(StringUtils.hasText(encrypted));
		String actual = Security.decrypt(encrypted);
		Assert.assertTrue(OpenmrsUtil.nullSafeEquals(expected, actual));
		
		// long text
		expected = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus porta sapien ac nisi imperdiet posuere. Maecenas nec felis ac enim posuere semper. In arcu turpis, elementum nec auctor id, pretium sed tortor. Quisque sit amet erat ante. Praesent metus dui, porttitor non volutpat eu, porta sed ante. Fusce quis dignissim nisl. Vivamus id massa in nisl sollicitudin iaculis ac ut odio. Morbi et sapien non massa ultricies commodo. Nunc semper, nulla a pellentesque adipiscing, urna nisl vulputate lacus, non rutrum nulla mauris at tortor. Quisque molestie, velit nec vehicula tempor, mi eros fermentum ipsum, ut ullamcorper nisl sem at risus. Nam varius nunc sit amet velit blandit gravida sed vel purus. Nam ac justo ut metus elementum vehicula ac non ante. Aliquam pellentesque semper mauris ut pulvinar."
		        + "Duis et orci nisi. Mauris tempor consequat felis, vel consequat diam consequat vitae. Donec eget dolor quis nulla lobortis vestibulum. Quisque vel ipsum in sapien egestas blandit. Praesent malesuada tellus nec sapien blandit sit amet molestie magna consequat. Pellentesque quis tempus urna. Quisque ut nibh ut tellus hendrerit rhoncus. Aenean ultricies lorem eu sem condimentum at consectetur magna dignissim. Nam porta lobortis consequat. Suspendisse congue, tellus quis sodales blandit, augue massa interdum sem, vel suscipit ipsum risus vitae massa. Quisque ipsum tellus, gravida sed suscipit non, ultricies eu augue. Etiam consequat consequat massa a accumsan. Quisque rhoncus nisi lectus, vel ultrices sapien. Aenean a felis felis, sit amet vestibulum lorem. Cras ut fermentum magna."
		        + "Quisque vel erat eget eros bibendum convallis vitae a augue. Maecenas posuere ullamcorper quam, ac ullamcorper eros egestas at. Nulla ipsum purus, venenatis ac dignissim in, bibendum eget enim. Nulla rhoncus dui eu augue egestas in tempus augue congue. Suspendisse potenti. Aenean faucibus felis ut leo venenatis congue lacinia felis tempor. Phasellus fermentum nisl venenatis quam molestie fermentum euismod metus pretium. Duis facilisis pharetra vehicula. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Donec aliquet feugiat sapien, vitae tristique nisl lacinia non. Fusce eros dolor, egestas et auctor vel, aliquam ac lorem. In lacinia cursus pretium."
		        + "Nulla vitae nisi vitae magna varius posuere. Curabitur non dui at odio scelerisque mattis a a risus. Suspendisse augue lacus, pulvinar vitae fringilla tempor, adipiscing vel velit. Suspendisse lorem dui, eleifend vel rhoncus ac, porta sed odio. Maecenas eget pellentesque ligula. Cras vitae auctor justo. Duis at massa vitae risus semper elementum. Proin at magna et augue volutpat tincidunt nec sed erat. Quisque id sapien tortor, ut gravida erat. Vivamus dictum, enim non sodales laoreet, ante libero suscipit erat, ac tristique purus eros sed augue. Quisque magna mi, varius ac accumsan aliquam, aliquam id risus. Phasellus dignissim dictum massa, ac consequat risus venenatis in. Morbi imperdiet bibendum sem, eu mollis urna aliquet a. In ac augue vitae ante ultrices sollicitudin vel sed elit. Nunc fringilla vestibulum egestas. Duis risus lorem, varius a vulputate at, blandit vel lectus. Sed mollis, ipsum nec fringilla accumsan, risus nibh iaculis ligula, non tristique nibh tortor vitae sem. Nulla facilisi. In id lectus vitae felis elementum lobortis. Aenean et nisi orci."
		        + "Nam mi lorem, posuere non auctor sed, accumsan eu magna. Fusce sit amet tellus augue. Nunc eleifend, justo id pharetra hendrerit, urna augue ultricies mi, sed fringilla arcu libero quis nulla. Maecenas tristique auctor cursus. Curabitur venenatis lacus non leo aliquet ornare. Praesent justo turpis, dictum eu dictum convallis, faucibus sit amet erat. Praesent sed dui id enim euismod interdum. Integer sed fermentum neque. Curabitur enim nunc, euismod adipiscing iaculis eget, tincidunt vel nunc. Nullam at neque sem, rutrum aliquet elit. In et velit enim, tempus mollis nunc. Sed sit amet quam justo. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur convallis dolor non ligula fermentum imperdiet.";
		encrypted = Security.encrypt(expected);
		Assert.assertTrue(StringUtils.hasText(encrypted));
		actual = Security.decrypt(encrypted);
		Assert.assertTrue(OpenmrsUtil.nullSafeEquals(expected, actual));
		
		// foreign text
		expected = "傑里米 (Jeremy), 潔儀 (Kitty) and 贏 (Win) like encryption :-D";
		encrypted = Security.encrypt(expected);
		Assert.assertTrue(StringUtils.hasText(encrypted));
		actual = Security.decrypt(encrypted);
		Assert.assertTrue(OpenmrsUtil.nullSafeEquals(expected, actual));
	}