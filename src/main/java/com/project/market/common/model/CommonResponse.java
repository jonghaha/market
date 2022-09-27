package com.project.market.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse<T> {
	// 메시지
	private String message;
	// 성공 true, 실패 false
	private Boolean result;
	// data
	private T data;

	public static <T> CommonResponse<T> ok() {
		return CommonResponse.<T>builder().result(true).build();
	}

	public static <T> CommonResponse<T> ok(T data) {
		return CommonResponse.<T>builder().result(true).data(data).build();
	}

	public static <T> CommonResponse<T> fail(String message) {
		return CommonResponse.<T>builder().result(false).message(message).build();
	}
}
