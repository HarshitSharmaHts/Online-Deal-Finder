package com.onlinedealfinder.model;

import com.mongodb.MongoException;
import org.bson.Document;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;

public class AuthenticationUtil {

	private static DB db = new DB();
	
	public static String generateHash(String input) {
		
		input = C.PASSWORD.SALT + input;
		
		StringBuilder hash = new StringBuilder();

		try {
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			byte[] hashedBytes = sha.digest(input.getBytes());
			char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
					'a', 'b', 'c', 'd', 'e', 'f' };
			for (int idx = 0; idx < hashedBytes.length; ++idx) {
				byte b = hashedBytes[idx];
				hash.append(digits[(b & 0xf0) >> 4]);
				hash.append(digits[b & 0x0f]);
			}
		} catch (NoSuchAlgorithmException e) {
			// handle error here.
		}

		return hash.toString();
	}

	public static String login(String email, String password) {

		password = generateHash(password);
		Document document = new Document(C.FIELD.EMAIL,email).append(C.FIELD.PASSWORD,password);
		Iterator<Document> iterator = MMongo.find(db.getCollection(),document);

		Document user_doc = null;

		if(iterator.hasNext()) {
			user_doc = iterator.next();
			return user_doc.toJson();
		}

		return C.ERROR.CODE_1;
	}

	public static  String getDetailsByEmail(String email) {
		Iterator<Document> iterator = MMongo.find(db.getCollection(),new Document(C.FIELD.EMAIL,email));

		if(iterator.hasNext()) {
			return iterator.next().toJson();
		}
		return C.ERROR.USER_DOES_NOT_EXIST;
	}

	public static String loginOAuth(String email) {
		Document document = new Document(C.FIELD.EMAIL,email);
		Iterator<Document> iterator = MMongo.find(db.getCollection(),document);

		if (iterator.hasNext()) {
			return iterator.next().toJson();
		}

		return C.ERROR.CODE_1;
	}

	public static boolean signUp(String name, String email, String imgurl, String password) {


		try
		{
			Document document = new Document(C.FIELD.NAME,name)
					.append(C.FIELD.EMAIL,email)
					.append(C.FIELD.PASSWORD,password);

			if(!imgurl.equals("")) {
				document = document.append(C.FIELD.IMGURL,imgurl);
			} else  {
				document = document.append(C.FIELD.IMGURL,"images/default.jpeg");
			}

			MMongo.insert(db.getCollection(),document);

			return true;
		}
		catch (MongoException me) {

		}
		return false;
	}
}
