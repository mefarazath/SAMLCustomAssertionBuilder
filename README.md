# SAML Attribute Modifier
---
This component allows to modify attributes in the SAML authentication response.

## Building From Source

Clone this repository first (`https://github.com/mefarazath/SAMLCustomAssertionBuilder.git`) and use Maven install to build
`mvn clean install`.

## Deploying to IS 5.3.0

* Copy org.wso2.custom.extensions.saml.assertion.builder-1.0.0.jar file to wso2is-5.3.0/repository/components/dropins
 folder
* Update the <SAMLSSOAssertionBuilder> tag in wso2is-5.3.0/repository/conf/identity/identity.xml as follows,
````xml
<SAMLSSOAssertionBuilder>org.wso2.custom.saml.assertion.builder.SAMLCustomAssertionBuilder</SAMLSSOAssertionBuilder>
````
