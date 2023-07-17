package com.example.einvoice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EInvoiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EInvoiceApplication.class, args);
	}


	// TODO ingilizce ve türkçe dil desteği desteği.
	// TODO frontend tarafından kullanıcı header'da lang:tr parametre gönderirse tr diline geçecektir.
}
