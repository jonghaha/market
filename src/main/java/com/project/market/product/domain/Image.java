package com.project.market.product.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "image")
public class Image {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "image_seq")
	private Long seq;

	@Column(name = "image_url")
	private String url;

	@Builder.Default
	@Column(name = "upload_datetime")
	private LocalDateTime uploadDatetime = LocalDateTime.now();

	public static Image of(String url) {
		return Image.builder().url(url).build();
	}
}
