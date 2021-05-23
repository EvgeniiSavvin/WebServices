package resources.auth;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.Base64;
import java.util.StringTokenizer;

public class AuthenticationFilter implements ContainerRequestFilter {

    private static final String AUTHORIZATION_PROPERTY = "Authorization";
    private static final String AUTHENTICATION_SCHEME = "Basic";

    static class BasicAuth {
        public String login;
        public String password;

        public BasicAuth(String login, String password) {
            this.login = login;
            this.password = password;
        }
    }

    @Override
    public ContainerRequest filter(ContainerRequest requestContext) {
        String httpMethod = requestContext.getMethod();

        if (httpMethod.equals("GET")) return requestContext;

        BasicAuth auth = extractAuthDetailsFromRequest(requestContext);
        if (auth == null) throw new WebApplicationException(Response.Status.UNAUTHORIZED.getStatusCode());
        else if (!isUserAllowed(auth.login, auth.password))
            throw new WebApplicationException(Response.Status.UNAUTHORIZED.getStatusCode());

        return requestContext;
    }

    private BasicAuth extractAuthDetailsFromRequest(ContainerRequest requestContext) {
        final String authorization = requestContext.getHeaderValue(AUTHORIZATION_PROPERTY);

        if (authorization == null || authorization.isEmpty()) {
            return null;
        }

        final String encodedUserPassword = authorization.replaceFirst(AUTHENTICATION_SCHEME + " ", "");

        String usernameAndPassword = new String(Base64.getDecoder().decode(encodedUserPassword.getBytes()));

        final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
        final String username = tokenizer.nextToken();
        final String password = tokenizer.nextToken();

        return new BasicAuth(username, password);
    }

    private boolean isUserAllowed(String username, String password) {
        return username.equals("admin") && password.equals("admin");
    }
}
