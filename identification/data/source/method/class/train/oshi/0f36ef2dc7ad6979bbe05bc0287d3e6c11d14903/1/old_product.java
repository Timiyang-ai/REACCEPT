public static String formatBytes(long bytes) {
		if (bytes == 1) { // bytes
			return String.format("%d byte", bytes);
		} else if (bytes < kibiByte) { // bytes
			return String.format("%d bytes", bytes);
		} else if (bytes < mebiByte && bytes % kibiByte == 0) { // KB
			return String.format("%.0f KB", (double) bytes / kibiByte);
		} else if (bytes < mebiByte) { // KB
			return String.format("%.1f KB", (double) bytes / kibiByte);
		} else if (bytes < gibiByte && bytes % mebiByte == 0) { // MB
			return String.format("%.0f MB", (double) bytes / mebiByte);
		} else if (bytes < gibiByte) { // MB
			return String.format("%.1f MB", (double) bytes / mebiByte);
		} else if (bytes % gibiByte == 0 && bytes < tebiByte) { // GB
			return String.format("%.0f GB", (double) bytes / gibiByte);
		} else if (bytes < tebiByte) { // GB
			return String.format("%.1f GB", (double) bytes / gibiByte);
		} else if (bytes % tebiByte == 0 && bytes < pebiByte) { // TiB
			return String.format("%.0f TiB", (double) bytes / tebiByte);
		} else if (bytes < pebiByte) { // TiB
			return String.format("%.1f TiB", (double) bytes / tebiByte);
		} else if (bytes % pebiByte == 0 && bytes < exbiByte) { // PiB
			return String.format("%.0f PiB", (double) bytes / pebiByte);
		} else if (bytes < exbiByte) { // PiB
			return String.format("%.1f PiB", (double) bytes / pebiByte);
		} else {
			return String.format("%d bytes", bytes);
		}
	}