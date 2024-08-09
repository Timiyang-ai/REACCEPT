public String format(){
		final StringBuilder sb = new StringBuilder();
		if(betweenMs > 0){
			long day = betweenMs / DateUnit.DAY.getMillis();
			long hour = betweenMs / DateUnit.HOUR.getMillis() - day * 24;
			long minute = betweenMs / DateUnit.MINUTE.getMillis() - day * 24 * 60 - hour * 60;
			long second = betweenMs / DateUnit.SECOND.getMillis() - ((day * 24 + hour) * 60 + minute) * 60;
			long millisecond = betweenMs - (((day * 24 + hour) * 60 + minute) * 60 + second) * 1000;
			
			final int level = this.level.ordinal();
			int levelCount = 0;
			
			if(isLevelCountValid(levelCount) && 0 != day && level >= Level.DAY.ordinal()){
				sb.append(day).append(Level.DAY.name);
				levelCount++;
			}
			if(isLevelCountValid(levelCount) && 0 != hour && level >= Level.HOUR.ordinal()){
				sb.append(hour).append(Level.HOUR.name);
				levelCount++;
			}
			if(isLevelCountValid(levelCount) && 0 != minute && level >= Level.MINUTE.ordinal()){
				sb.append(minute).append(Level.MINUTE.name);
				levelCount++;
			}
			if(isLevelCountValid(levelCount) && 0 != second && level >= Level.SECOND.ordinal()){
				sb.append(second).append(Level.SECOND.name);
				levelCount++;
			}
			if(isLevelCountValid(levelCount) && 0 != millisecond && level >= Level.MILLSECOND.ordinal()){
				sb.append(millisecond).append(Level.MILLSECOND.name);
				// levelCount++;
			}
		}
		
		if(StrUtil.isEmpty(sb)) {
			sb.append(0).append(this.level.name);
		}
		
		return sb.toString();
	}