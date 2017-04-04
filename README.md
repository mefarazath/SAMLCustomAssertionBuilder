# SAML Attribute Modifier
---
This component allows to retrieve claims from a custom attribute store when building the SAML authentication response.

## Building From Source

Clone this repository first (`https://github.com/mefarazath/SAMLCustomAssertionBuilder.git`) 

Use maven install to build
`mvn clean install`.

## Deploying to IS 5.3.0

* Copy **org.wso2.custom.extensions.saml.assertion.builder-1.0.0.jar** file to **wso2is-5.3.0/repository/components/dropins**
 folder
* Update the <SAMLSSOAssertionBuilder> tag in wso2is-5.3.0/repository/conf/identity/identity.xml as follows,
````xml
<SAMLSSOAssertionBuilder>org.wso2.custom.saml.assertion.builder.SAMLCustomAssertionBuilder</SAMLSSOAssertionBuilder>
````
