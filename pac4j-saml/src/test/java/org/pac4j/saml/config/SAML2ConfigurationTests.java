package org.pac4j.saml.config;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

/**
 * This is {@link SAML2ConfigurationTests}.
 *
 * @author Misagh Moayyed
 */
public class SAML2ConfigurationTests {
    @Test
    public void verifySigningCertExported() {
        final var configuration = new SAML2Configuration();
        configuration.setForceKeystoreGeneration(true);
        configuration.setKeystorePath("target/keystore.jks");
        configuration.setKeystorePassword("pac4j");
        configuration.setPrivateKeyPassword("pac4j");
        configuration.setServiceProviderMetadataResource(new FileSystemResource("target/out.xml"));
        configuration.setIdentityProviderMetadataResource(new ClassPathResource("idp-metadata.xml"));
        configuration.init();
        final var signingCertPem = new File("target/saml-signing-cert.pem");
        assertTrue(signingCertPem.exists());
        final var signingCert = new File("target/saml-signing-cert.crt");
        assertTrue(signingCert.exists());
        final var signingCertKey = new File("target/saml-signing-cert.key");
        assertTrue(signingCertKey.exists());
    }

    @Test
    public void verifySigningCertNamedExported() {
        final var certNamePart = "id-09 _*#AD";
        final var certNameResult = "id-09_AD";
        final var configuration = new SAML2Configuration();
        configuration.setForceKeystoreGeneration(true);
        configuration.setKeystorePath("target/keystore.jks");
        configuration.setCertificateNameToAppend(certNamePart);
        configuration.setKeystorePassword("pac4j");
        configuration.setPrivateKeyPassword("pac4j");
        configuration.setServiceProviderMetadataResource(new FileSystemResource("target/out.xml"));
        configuration.setIdentityProviderMetadataResource(new ClassPathResource("idp-metadata.xml"));
        configuration.init();
        final var signingCertPem = new File("target/saml-signing-cert-" + certNameResult + ".pem");
        assertTrue(signingCertPem.exists());
        final var signingCert = new File("target/saml-signing-cert-" + certNameResult + ".crt");
        assertTrue(signingCert.exists());
        final var signingCertKey = new File("target/saml-signing-cert-" + certNameResult + ".key");
        assertTrue(signingCertKey.exists());
    }
}
