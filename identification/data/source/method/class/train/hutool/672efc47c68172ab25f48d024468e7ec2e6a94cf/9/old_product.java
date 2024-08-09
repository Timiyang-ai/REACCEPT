public static String format(String template, Object... value) {
		if(value == null || value.length == 0 || isBlank(template)) {
			return template;
		}
		
		final StringBuilder sb = new StringBuilder();
		final int length = template.length();
		
		int valueIndex = 0;
		char currentChar;
		for(int i = 0; i < length; i++) {
			if(valueIndex >= value.length) {
				sb.append(sub(template, i, length));
				break;
			}
			
			currentChar = template.charAt(i);
			if(currentChar == '{') {
				final char nextChar = template.charAt(++i);
				if(nextChar == '}') {
					sb.append(value[valueIndex ++]);
				}else {
					sb.append('{').append(nextChar);
				}
			}else {
				sb.append(currentChar);
			}
			
		}
		
		return sb.toString();
	}