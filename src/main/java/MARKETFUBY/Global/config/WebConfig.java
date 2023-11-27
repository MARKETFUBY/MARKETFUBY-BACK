package MARKETFUBY.Global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOrigins("https://www.market-fuby.com")
			.allowedOrigins("http://localhost:3000")
			.allowedMethods("*")
			.allowCredentials(true) // 쿠키 인증 요청 허용
			.maxAge(3600); // 원하는 시간만큼 pre-flight 리퀘스트를 캐싱
	}
}
