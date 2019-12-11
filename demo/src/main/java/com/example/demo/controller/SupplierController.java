package com.example.demo.controller;


import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.SupplierDTO;
import com.example.demo.model.Supplier;
import com.example.demo.repository.SupplierDTORepository;
import com.example.demo.repository.SupplierRepository;

@RestController
@RequestMapping("/api/v1/user")
public class SupplierController {

	@Autowired
	private SupplierRepository supplierRepository;
	@Autowired
	private SupplierDTORepository supplierDTORepository;
	
	static String IV = "THISISMAIDHWIYRY";
	static String encryptionKey = "Th1515M4FYEOWUTS";
	
	@PostMapping("/login")
	public ResponseEntity < SupplierDTO > login(@RequestParam(name="username") String userName, @RequestParam(name="password") String pwd) {
		
		Supplier user = supplierRepository.findByUsername(userName);
		try {
			if(check(user.getPassword(),pwd)){
				String token = UUID.randomUUID().toString();
				user.setToken(token);
				supplierRepository.save(user);
			}else{
				return null;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.ok((new SupplierDTO(user)));
	}
	
	@PostMapping("/supplier")
    public Supplier createSupplier(@Valid @RequestBody Supplier user) {
		String password = user.getPassword();
    	try {
			user.setPassword(salt(password));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	SupplierDTO dtoS = new SupplierDTO();
    	dtoS.setUsername(user.getUsername());
    	dtoS.setName(user.getName());
    	dtoS.setCountry(user.getCountry());
    	supplierDTORepository.save(dtoS);
        return supplierRepository.save(user);
    }
	
    private static boolean check(String salted, String password) throws Exception {
    	String pass = salt(password);
        return pass.equals(salted);
    }
     
    private static String salt(String password) throws Exception {
    	byte[] cipher = encrypt(password, encryptionKey);
    	String crypted = "";
    	for (int i=0; i<cipher.length; i++)
    		crypted += new Integer(cipher[i]);
    	
    	return crypted;
    }
    
    private static byte[] encrypt(String plainText, String encryptionKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
        return cipher.doFinal(plainText.getBytes("UTF-8"));
      }
}
