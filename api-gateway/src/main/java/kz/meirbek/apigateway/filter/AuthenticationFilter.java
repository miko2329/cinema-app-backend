//package kz.meirbek.apigateway.filter;
//
//import com.google.common.net.HttpHeaders;
//import kz.meirbek.apigateway.service.JwtService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.gateway.filter.GatewayFilter;
//import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
//import org.springframework.stereotype.Component;
//
//import java.util.Arrays;
//import java.util.List;
//
//@Component
//public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
//
//    private static final List<String> ADMIN_LIST_URL = List.of("/api/v1/auth/**",
//            "/api/v1/countries/add",
//            "/api/v1/countries/update",
//            "/api/v1/awards/add",
//            "/api/v1/awards/update",
//            "/api/v1/people/add",
//            "/api/v1/people/update");
//
//    private RouteValidator validator;
//    private JwtService jwtService;
//
//    @Autowired
//    public AuthenticationFilter(RouteValidator validator, JwtService jwtService) {
//        this.validator = validator;
//        this.jwtService = jwtService;
//    }
//
//    public static class Config {
//
//    }
//
//    public AuthenticationFilter() {
//        super(Config.class);
//    }
//
//    public AuthenticationFilter(Class<Config> configClass) {
//        super(configClass);
//    }
//
//    @Override
//    public GatewayFilter apply(Config config) {
//        return ((exchange, chain) -> {
//            if (validator.isSecured.test(exchange.getRequest())) {
//                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
//                    throw new RuntimeException("Missing authorization header");
//                }
//
//                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
//                if (authHeader != null && authHeader.startsWith("Bearer ")) {
//                    authHeader = authHeader.substring(7);
//                }
//
//                try {
//                    String roles = jwtService.extractRoles(authHeader);
//                    String requestedPath = exchange.getRequest().getPath().toString();
//                    boolean authorized = false;
//
//                    if (ADMIN_LIST_URL.contains(requestedPath)) {
//                        authorized = roles.equalsIgnoreCase("ROLE_ADMIN");
//                    }
//                    else {
//                        authorized = roles.equalsIgnoreCase("ROLE_USER");
//                    }
//
//                    if (!authorized) {
//                        throw new RuntimeException("Unauthorized access to the application");
//                    }
//
//                } catch (Exception e) {
//                    throw new RuntimeException("Unauthorized access to the application");
//                }
//            }
//            return chain.filter(exchange);
//        });
//    }
//}
