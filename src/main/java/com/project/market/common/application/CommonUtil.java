package com.project.market.common.application;

public class CommonUtil {
	// 기존값 value1과 새로운 값 value2 를 비교하여 달라졌는지 비교
	public static boolean isDifferent(Object value1, Object value2) {
		if(value1 == null) {
			return value2 != null;
		} else {
			return !value1.equals(value2);
		}
	}
}
