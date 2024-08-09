public static boolean checkScheduleOverlap(Order order1, Order order2) {
		if (order2.getEffectiveStopDate() == null && order1.getEffectiveStopDate() == null) {
			return true;
		}
		
		if (order2.getEffectiveStopDate() == null) {
			return OpenmrsUtil.compare(order1.getEffectiveStopDate(), order2.getEffectiveStartDate()) > -1;
		}
		
		if (order1.getEffectiveStopDate() == null) {
			return (OpenmrsUtil.compare(order1.getEffectiveStartDate(), order2.getEffectiveStartDate()) > -1)
			        && (OpenmrsUtil.compare(order1.getEffectiveStartDate(), order2.getEffectiveStopDate()) < 1);
		}
		
		return (OpenmrsUtil.compare(order1.getEffectiveStartDate(), order2.getEffectiveStopDate()) < 1)
		        && (OpenmrsUtil.compare(order1.getEffectiveStopDate(), order2.getEffectiveStartDate()) > -1);
	}