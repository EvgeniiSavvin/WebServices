package uddi;

import org.apache.juddi.v3.client.UDDIConstants;
import org.apache.juddi.v3.client.config.UDDIClient;
import org.apache.juddi.v3.client.transport.Transport;
import org.uddi.api_v3.*;
import org.uddi.v3_service.UDDIInquiryPortType;
import org.uddi.v3_service.UDDISecurityPortType;

public class SimpleBrowse {

    private static UDDISecurityPortType security = null;
    private static UDDIInquiryPortType inquiry = null;

    private static final String ServiceName = "Person Service";

    public SimpleBrowse() {
        try {
            UDDIClient client = new UDDIClient("META-INF/uddi.xml");
            Transport transport = client.getTransport("default");

            security = transport.getUDDISecurityService();
            inquiry = transport.getUDDIInquiryService();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String findWsdl() {
        try {

            String token = getAuthToken("root", "root");
            ServiceList services = findServices(token, ServiceName);
            String serviceKey = services.getServiceInfos().getServiceInfo().get(0).getServiceKey();
            GetServiceDetail gsd = new GetServiceDetail();
            gsd.setAuthInfo(token);
            gsd.getServiceKey().add(serviceKey);
            ServiceDetail sd = inquiry.getServiceDetail(gsd);
            String wsdl = sd.getBusinessService().get(0).getBindingTemplates().getBindingTemplate().get(0).getAccessPoint().getValue();

            discardAuthKey(token);
            return wsdl;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private ServiceList findServices(String token, String search) throws Exception {
        FindService fs = new FindService();
        fs.setAuthInfo(token);
        org.uddi.api_v3.FindQualifiers fq = new org.uddi.api_v3.FindQualifiers();
        fq.getFindQualifier().add(UDDIConstants.APPROXIMATE_MATCH);

        fs.setFindQualifiers(fq);
        Name searchname = new Name();
        searchname.setValue(search);
        fs.getName().add(searchname);
        return inquiry.findService(fs);
    }

    private String getAuthToken(String username, String password) {
        try {

            GetAuthToken getAuthTokenRoot = new GetAuthToken();
            getAuthTokenRoot.setUserID(username);
            getAuthTokenRoot.setCred(password);

            AuthToken rootAuthToken = security.getAuthToken(getAuthTokenRoot);
            return rootAuthToken.getAuthInfo();
        } catch (Exception ex) {
            System.err.println("Could not authenticate with the provided credentials " + ex.getMessage());
        }
        return null;
    }

    private void discardAuthKey(String token){
        try {
            security.discardAuthToken(new DiscardAuthToken(token));
        } catch (Exception ex) {
            System.err.println("Could not authenticate with the provided credentials " + ex.getMessage());
        }
    }

}
