package com.project.fofo;

<<<<<<< HEAD
import jakarta.servlet.MultipartConfigElement;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;
=======
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
>>>>>>> e3765f37bea5b8b0f2881f46c8a2339086f83f14

@SpringBootApplication
public class FofoApplication {

	public static void main(String[] args) {
		SpringApplication.run(FofoApplication.class, args);
	}

<<<<<<< HEAD
	//정채빈 카메라용량 늘리기
	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize(DataSize.parse("50MB"));
		factory.setMaxRequestSize(DataSize.parse("50MB"));
		return factory.createMultipartConfig();
	}

=======
>>>>>>> e3765f37bea5b8b0f2881f46c8a2339086f83f14
	//김도연 풀 푸시 확인용
}
