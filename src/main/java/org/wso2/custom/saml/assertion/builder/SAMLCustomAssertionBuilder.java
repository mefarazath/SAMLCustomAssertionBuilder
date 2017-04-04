package org.wso2.custom.saml.assertion.builder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.opensaml.saml2.core.Assertion;
import org.wso2.carbon.context.PrivilegedCarbonContext;
import org.wso2.carbon.identity.application.authentication.framework.model.AuthenticatedUser;
import org.wso2.carbon.identity.application.common.IdentityApplicationManagementException;
import org.wso2.carbon.identity.application.common.model.ServiceProvider;
import org.wso2.carbon.identity.application.mgt.ApplicationManagementService;
import org.wso2.carbon.identity.base.IdentityException;
import org.wso2.carbon.identity.sso.saml.builders.assertion.DefaultSAMLAssertionBuilder;
import org.wso2.carbon.identity.sso.saml.dto.SAMLSSOAuthnReqDTO;

import java.util.HashMap;
import java.util.Map;

/**
 * This class builds a SAML Assertion getting the claims from a custom attribute store/API
 */
public class SAMLCustomAssertionBuilder extends DefaultSAMLAssertionBuilder {

    private static final Log log = LogFactory.getLog(SAMLCustomAssertionBuilder.class);

    private static final String INBOUND_AUTH_TYPE_SAML = "samlsso";


    @Override
    protected void addAttributeStatements(SAMLSSOAuthnReqDTO authReqDTO, Assertion samlAssertion) throws IdentityException {

        // get the application or SP name from SAML Issuer value
        String spName = getSpNameFromIssuer(authReqDTO.getIssuer());
        // Get the authenticated user
        AuthenticatedUser user = authReqDTO.getUser();

        // get claims from the external attribute store
        Map<String, String> claims = getClaimsFromExternalAttributeStore(user, spName);
        // build an attribute statement and add it to the assertion
        samlAssertion.getAttributeStatements().add(buildAttributeStatement(claims));
    }


    /**
     * Get claims from an external attribute store
     *
     * @param user                Authenticated User
     * @param serviceProviderName Application name
     * @return
     */
    protected Map<String, String> getClaimsFromExternalAttributeStore(AuthenticatedUser user,
                                                                      String serviceProviderName) {
        // We can call an external API and get the claims
        Map<String, String> claims = new HashMap<>();
        claims.put("https://custom.claim.org/rewards", "none");
        claims.put("https://custom.claim.org/status", "inactive");
        return claims;
    }

    /**
     * Get the Service Provider name from the SAML issuer value.
     *
     * @return
     */
    private String getSpNameFromIssuer(String samlIssuer) throws IdentityException {

        String tenantDomain = PrivilegedCarbonContext.getThreadLocalCarbonContext().getTenantDomain();
        ServiceProvider sp;
        try {
            sp = ApplicationManagementService.getInstance().getServiceProviderByClientId(samlIssuer,
                    INBOUND_AUTH_TYPE_SAML, tenantDomain);
            return sp.getApplicationName();
        } catch (IdentityApplicationManagementException e) {
            throw new IdentityException("Error retrieving SP Name for SAML Issuer value : " + samlIssuer, e);
        }


    }
}
