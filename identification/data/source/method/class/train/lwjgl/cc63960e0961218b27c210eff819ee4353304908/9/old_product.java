public static DisplayMode setDisplayMode(DisplayMode[] dm, final String[] param) throws Exception {
		
		
		class Sorter implements Comparator {
			
			final Field[] field;
			final int[] order;
			final boolean[] usePreferred;
			final int[] preferred;
			
			Sorter() throws NoSuchFieldException {
				field = new Field[param.length];
				order = new int[param.length];
				preferred = new int[param.length];
				usePreferred = new boolean[param.length];
				for (int i = 0; i < field.length; i ++) {
					int idx = param[i].indexOf('=');
					if (idx > 0) {
						preferred[i] = Integer.parseInt(param[i].substring(idx + 1, param[i].length()));
						usePreferred[i] = true;
						param[i] = param[i].substring(0, idx);
						field[i] = DisplayMode.class.getDeclaredField(param[i]);
					} else if (param[i].charAt(0) == '-') {
						field[i] = DisplayMode.class.getDeclaredField(param[i].substring(1));
						order[i] = -1;
					} else {
						field[i] = DisplayMode.class.getDeclaredField(param[i]);
						order[i] = 1;
					}
					field[i].setAccessible(true);
				}
			}
			
			/**
			 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
			 */
			public int compare(Object o1, Object o2) {
				DisplayMode dm1 = (DisplayMode) o1;
				DisplayMode dm2 = (DisplayMode) o2;
				
				for (int i = 0; i < field.length; i ++) {
					try {
						int f1 = field[i].getInt(dm1);
						int f2 = field[i].getInt(dm2);
						
						if (usePreferred[i] && f1 != f2) {
							if (f1 == preferred[i])
								return -1;
							else if (f2 == preferred[i])
								return 1;
							else {
								// Score according to the difference between the values
								int absf1 = Math.abs(f1 - preferred[i]);
								int absf2 = Math.abs(f2 - preferred[i]);
								if (absf1 < absf2)
									return -1;
								else if (absf1 > absf2)
									return 1;
								else
									continue;
							}
						} else if (f1 < f2)
							return order[i];
						else if (f1 == f2)
							continue;
						else
							return -order[i];
					} catch (IllegalAccessException e) {
						throw new RuntimeException(e);
					}
				}
				
				return 0;
			}
		}
		
		// Sort the display modes
		Arrays.sort(dm, new Sorter());
		
		// Try them out in the appropriate order
		if (LWJGLUtil.DEBUG || DEBUG) {
			System.out.println("Sorted display modes:");
			for (int i = 0; i < dm.length; i ++) {
				System.out.println(dm[i]);
			}
		}
		for (int i = 0; i < dm.length; i ++) {
			try {
				if (LWJGLUtil.DEBUG || DEBUG)
					System.out.println("Attempting to set displaymode: "+dm[i]);
				org.lwjgl.opengl.Display.setDisplayMode(dm[i]);
				return dm[i];
			} catch (Exception e) {
				if (LWJGLUtil.DEBUG || DEBUG) {
					System.out.println("Failed to set display mode to "+dm[i]);
					e.printStackTrace();
				}
			}
		}
		
		throw new Exception("Failed to set display mode.");
	}