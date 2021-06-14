/*
 * Copyright 2001-2010 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

import org.apache.juddi.api_v3.AccessPointType;
import org.apache.juddi.v3.client.config.UDDIClerk;
import org.apache.juddi.v3.client.config.UDDIClient;
import org.uddi.api_v3.*;

public class ServicePublisher {

        private static UDDIClerk clerk = null;

        public ServicePublisher() {
                try {
                        UDDIClient uddiClient = new UDDIClient("META-INF/uddi.xml");
                        clerk = uddiClient.getClerk("default");
                        if (clerk==null)
                                throw new Exception("the clerk wasn't found, check the config file!");
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }

        public void publish() {
                try {
                        BusinessEntity myBusEntity = new BusinessEntity();
                        Name myBusName = new Name();
                        myBusName.setValue("ITMO Business");
                        myBusEntity.getName().add(myBusName);

                        BusinessEntity register = clerk.register(myBusEntity);
                        if (register == null) {
                                System.out.println("Save failed!");
                                System.exit(1);
                        }
                        String myBusKey = register.getBusinessKey();
                        System.out.println("myBusiness key:  " + myBusKey);

                        BusinessService myService = new BusinessService();
                        myService.setBusinessKey(myBusKey);
                        Name myServName = new Name();
                        myServName.setValue("Person Service");
                        myService.getName().add(myServName);

                        BindingTemplate myBindingTemplate = new BindingTemplate();
                        AccessPoint accessPoint = new AccessPoint();
                        accessPoint.setUseType(AccessPointType.WSDL_DEPLOYMENT.toString());
                        accessPoint.setValue("http://localhost:8081/PersonService?wsdl");
                        myBindingTemplate.setAccessPoint(accessPoint);
                        BindingTemplates myBindingTemplates = new BindingTemplates();

                        myBindingTemplate = UDDIClient.addSOAPtModels(myBindingTemplate);
                        myBindingTemplates.getBindingTemplate().add(myBindingTemplate);
                        myService.setBindingTemplates(myBindingTemplates);

                        BusinessService svc = clerk.register(myService);
                        if (svc==null){
                                System.out.println("Save failed!");
                                System.exit(1);
                        }
                        
                        String myServKey = svc.getServiceKey();
                        System.out.println("myService key:  " + myServKey);

                        clerk.discardAuthToken();

                        System.out.println("Success!");

                } catch (Exception e) {
                        e.printStackTrace();
                }
        }

        public static void main(String[] args) {
                ServicePublisher sp = new ServicePublisher();
                sp.publish();
        }
}
