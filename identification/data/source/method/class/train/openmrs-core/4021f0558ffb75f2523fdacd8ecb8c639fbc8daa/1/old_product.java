public static boolean checkScheduleOverlap(Order order1, Order order2) {
		if (order1.getVoided() == true || order2.getVoided() == true) {
			return false;
		}
		if (order2.getEffectiveStopDate() == null && order1.getEffectiveStopDate() == null) {
			return true;
		}
		
		if (order2.getEffectiveStopDate() == null) {
			return order1.getEffectiveStopDate().after(order2.getEffectiveStartDate());
		}
		
		if (order1.getEffectiveStopDate() == null) {
			return order1.getEffectiveStartDate().after(order2.getEffectiveStartDate())
			        && order1.getEffectiveStartDate().before(order2.getEffectiveStopDate());
		}
		
		return order1.getEffectiveStartDate().before(order2.getEffectiveStopDate())
		        && order1.getEffectiveStopDate().after(order2.getEffectiveStartDate());
	}