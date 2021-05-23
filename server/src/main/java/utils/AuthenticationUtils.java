package utils;

import faults.PersonServiceException;
import faults.PersonServiceFault;
import model.BasicAuth;

import javax.xml.ws.handler.MessageContext;
import java.util.*;

public class AuthenticationUtils {
    private static final String AUTHORIZATION_PROPERTY = "Authorization";
    private static final String AUTHENTICATION_SCHEME = "Basic";

    private static final PersonServiceException UnauthorisedException =
            new PersonServiceException("Unauthorised. Wrong username or password", PersonServiceFault.unauthorised());

    public static void authenticateOrFail(MessageContext context) throws PersonServiceException{
        BasicAuth auth = extractAuthDetails(context);
        if(auth == null) throw UnauthorisedException;
        else if (!isUserAllowed(auth.login, auth.password)) throw UnauthorisedException;
    }

    private static BasicAuth extractAuthDetails(MessageContext context) {
        final Map headers = (Map) context.get(MessageContext.HTTP_REQUEST_HEADERS);
        final List list = (List) headers.get(AUTHORIZATION_PROPERTY);

        if (list == null || list.isEmpty()) return null;

        final String authorization = (String) list.get(0);

        final String encodedUserPassword = authorization.replaceFirst(AUTHENTICATION_SCHEME + " ", "");

        String usernameAndPassword = new String(Base64.getDecoder().decode(encodedUserPassword.getBytes()));

        final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
        final String username = tokenizer.nextToken();
        final String password = tokenizer.nextToken();

        return new BasicAuth(username, password);
    }

    private static boolean isUserAllowed(String username, String password) {
        return username.equals("admin") && password.equals("admin");
    }
}
