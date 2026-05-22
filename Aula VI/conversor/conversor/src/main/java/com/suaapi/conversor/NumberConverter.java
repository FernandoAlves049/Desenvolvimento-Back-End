package com.suaapi.conversor;

import java.util.regex.Pattern;

public final class NumberConverter {

	private static final Pattern NUMERIC_PATTERN = Pattern.compile("[-+]?(?:\\d+(?:[\\.,]\\d+)?|[\\.,]\\d+)");

	private NumberConverter() {
	}

	public static boolean isNumeric(String value) {
		if (value == null) {
			return false;
		}

		return NUMERIC_PATTERN.matcher(value.trim()).matches();
	}
}