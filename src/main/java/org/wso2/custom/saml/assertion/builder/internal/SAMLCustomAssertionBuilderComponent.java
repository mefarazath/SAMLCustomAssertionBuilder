package org.wso2.custom.saml.assertion.builder.internal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.service.component.ComponentContext;

/**
 * @scr.component name="SAMLCustomAssertionBuilderComponent" immediate="true"
 */
public class SAMLCustomAssertionBuilderComponent {

    private static final Log log = LogFactory.getLog(SAMLCustomAssertionBuilderComponent.class);

    protected void activate(ComponentContext context) {

        /**
         * Any logic which need to run during the bundle activation goes here.
         * Ex: Reading config file
         */

        log.info("SAMLCustomAssertionBuilderComponent bundle is activated");
    }

    protected void deactivate(ComponentContext context) {

        log.info("SAMLCustomAssertionBuilderComponent bundle is deactivated");
    }

}
