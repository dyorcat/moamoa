package com.teamsparta.moamoa.infra.security

import com.teamsparta.moamoa.domain.socialUser.service.OAuth2LoginSuccessHandler
import com.teamsparta.moamoa.domain.socialUser.service.OAuth2UserService
import com.teamsparta.moamoa.infra.security.jwt.JwtAuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtAuthenticationFilter: JwtAuthenticationFilter,
    private val authenticationEntryPoint: AuthenticationEntryPoint,
    private val accessDeniedHandler: AccessDeniedHandler,
    private val oAuth2UserService: OAuth2UserService,
    private val oAuth2LoginSuccessHandler: OAuth2LoginSuccessHandler,
) {

    @Bean
    fun corsConfigSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = listOf("*")
        configuration.allowedMethods = listOf("GET", "POST", "PUT", "DELETE")
        configuration.allowedHeaders = listOf("*")
        configuration.exposedHeaders = listOf("*")
        configuration.allowCredentials = true
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**",configuration)
        return source
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .httpBasic { it.disable() }
            .formLogin { it.disable() }
            .cors{it.configurationSource(corsConfigSource())}
            .csrf { it.disable() }
            .headers { it.frameOptions { options -> options.sameOrigin() } }
            .authorizeHttpRequests {
                it.requestMatchers(
                    "/test",
                    "/sellers",
                    "/sellers/signup",
                    "/sellers/signin",
                    "/swagger-ui/**",
                    "/v3/api-docs/**",
                    "/oauth2/login",
                    "/oauth2/callback/**",
                    "/h2-console/**",
                    "/api/orders/**",
                    "/api",
                    "/api/orders/create",
                    "/payment",
                    "/payment/**",
                    "/",
                    "/success-payment",
                    "/fail-payment",
                    "/order/**",
                    "/order",
                    "/**",
                ).permitAll()
                    .anyRequest().authenticated()
            }
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
            .exceptionHandling {
                it.authenticationEntryPoint(authenticationEntryPoint)
                it.accessDeniedHandler(accessDeniedHandler)
            }
            .oauth2Login { oauthConfig ->
                oauthConfig.authorizationEndpoint {
                    it.baseUri("/oauth2/login")
                }.redirectionEndpoint {
                    it.baseUri("/oauth2/callback/*")
                }.userInfoEndpoint {
                    it.userService(oAuth2UserService)
                }.successHandler(oAuth2LoginSuccessHandler)
            }
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .build()
    }
}