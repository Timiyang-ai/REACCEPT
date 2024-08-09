public String format(){
		if(betweenMs == 0){
			return "0";
		}
		
		long day = betweenMs / DateUnit.DAY.getMillis();
		long hour = betweenMs / DateUnit.HOUR.getMillis() - day * 24;
		long minute = betweenMs / DateUnit.MINUTE.getMillis() - day * 24 * 60 - hour * 60;
		long second = betweenMs / DateUnit.SECOND.getMillis() - ((day * 24 + hour) * 60 + minute) * 60;
		long millisecond = betweenMs - (((day * 24 + hour) * 60 + minute) * 60 + second) * 1000;
		
		StringBuilder sb = new StringBuilder();
		final int level = this.level.value;
		int levelCount = 0;
		
		if(isLevelCountValid(levelCount) && 0 != day && level > 0){
			sb.append(day).append("天");
			levelCount++;
		}
		if(isLevelCountValid(levelCount) && 0 != hour && level > 1){
			sb.append(hour).append("小时");
			levelCount++;
		}
		if(isLevelCountValid(levelCount) && 0 != minute && level > 2){
			sb.append(minute).append("分");
			levelCount++;
		}
		if(isLevelCountValid(levelCount) && 0 != second && level > 3){
			sb.append(second).append("秒");
			levelCount++;
		}
		if(isLevelCountValid(levelCount) && 0 != millisecond && level > 4){
			sb.append(millisecond).append("毫秒");
			levelCount++;
		}
		
		return sb.toString();
	}