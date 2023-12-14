//package kz.meirbek.apigateway.filter;
//
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.stereotype.Component;
//
//import java.util.function.Predicate;
//
//@Component
//public class RouteValidator {
//
//    private static final String[] WHITE_LIST_URL = {"/api/v1/auth/**",
//        "/api/v1/countries/all",
//        "/api/v1/countries/get",
//        "/api/v1/awards/all",
//        "/api/v1/awards/get",
//        "/api/v1/people/all",
//        "/api/v1/people/get"
//    };
//
//
//    public Predicate<ServerHttpRequest> isSecured = request -> {
//        String path = request.getPath().toString();
//        for (String endpoint : WHITE_LIST_URL) {
//            if (path.contains(endpoint)) {
//                return false;
//            }
//        }
//        return true;
//    };
//}
