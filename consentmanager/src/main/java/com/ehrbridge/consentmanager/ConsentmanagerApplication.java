package com.ehrbridge.consentmanager;

import com.ehrbridge.consentmanager.helpers.RSAHelper;
import com.ehrbridge.consentmanager.models.Constants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.ehrbridge.consentmanager.helpers.RSAHelper.*;

@SpringBootApplication
public class ConsentmanagerApplication {

	public static void main(String[] args) {
		RSAHelper.generateAndSetKeys();
//		String pem = rsaPrivateKeyObjectToPEM(Constants.RSA_PRIV);
//		System.out.println(pem);
//		System.out.println(rsaPEMToPrivateKeyObject(pem));
//		System.out.println(Constants.RSA_PRIV);
		SpringApplication.run(ConsentmanagerApplication.class, args);
	}



}
