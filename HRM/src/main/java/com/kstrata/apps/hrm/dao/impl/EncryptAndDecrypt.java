package com.kstrata.apps.hrm.dao.impl;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.security.Key;

import javax.crypto.Cipher;

public class EncryptAndDecrypt implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 580804576474949589L;
	private static Key key = null;
	private static Cipher cipher = null;
	
	public EncryptAndDecrypt() {
	}
	
	public static void prepareKey(byte[] byteKey) {
		try {
			ByteArrayInputStream bais = null;
			try {
				bais = new ByteArrayInputStream(byteKey);
				ObjectInputStream ois = new ObjectInputStream(bais);
				key = (Key) ois.readObject();
				ois.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			cipher = Cipher.getInstance("AES");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * private void generateKey() throws NoSuchAlgorithmException { KeyGenerator
	 * keyGenerator = KeyGenerator.getInstance("AES"); keyGenerator.init(256);
	 * //128 default; 192 and 256 also possible key =
	 * keyGenerator.generateKey(); } private void saveKey(SecretKey key, File
	 * file) throws IOException { byte[] encoded = key.getEncoded(); String data
	 * = new BigInteger(1, encoded).toString(16);
	 * FileUtils.writeStringToFile(file, data, null); } private Key loadKey(File
	 * file) throws IOException { String hex = new
	 * String(FileUtils.readFileToByteArray(file)); byte[] encoded = new
	 * BigInteger(hex, 16).toByteArray(); key = new SecretKeySpec(encoded,
	 * "AES"); return key; }
	 */

	/*
	 * public EncryptAndDecrypt getInstance(){ return obj; }
	 */

	public byte[] encrypt(String input) throws Exception {
		if (key == null) {
			prepareKey(null);
		}
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] inputBytes = input.getBytes();
		// System.out.println(inputBytes);
		return cipher.doFinal(inputBytes);
	}

	/*
	 * public String getEncryptStringValue(String input) throws
	 * InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
	 * return new String(encrypt(input)); }
	 */

	public static String decrypt(byte[] encryptionBytes) throws Exception {
		if (key == null) {
			prepareKey(null);
		}
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] recoveredBytes = cipher.doFinal(encryptionBytes);
		String recovered = new String(recoveredBytes);
		// System.out.println(recovered);
		return recovered;
	}

	
	 /*public void prepareKey1() throws Exception{
		 KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		 key = keyGenerator.generateKey();
		 File file = new File("D:\\key");
		 file.createNewFile();
		 ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:\\key"));
		 oos.writeObject(key);
		 oos.close();
	}*/

	public static void main(String[] a) {
		try {
			/*EncryptAndDecrypt andDecrypt = new EncryptAndDecrypt();
			andDecrypt.prepareKey1();*/
			
			/*File file = new File("J:\\key");
			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file));
			key = (Key) inputStream.readObject();
			System.out.println(key);
			
			KeyDAO keyDAO = new KeyDAO();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream out = null;
			try {
				out = new ObjectOutputStream(baos);
				out.writeObject(key);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			byte[] byteObject = baos.toByteArray();
			com.kstrata.apps.tsm.business.dao.entity.Key key = new com.kstrata.apps.tsm.business.dao.entity.Key();
			key.setEncKey(byteObject);
			keyDAO.save(key);*/
			 

			/*EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
			Employee employee = new Employee();
			employee.setEmpPassword(EncryptAndDecrypt.encrypt("kstrata123"));
			employee.setActive("Y");
			employee.setEmpDesignation("Manager");
			employee.setEmpEmailid("admin@kstrata.com");
			employee.setEmpFirstName("Ravi Verma");
			employee.setEmpLastName("Kalidindi");
			employee.setEmpUsername("adminrk");
			employee.setPasswordChangeFlag("Y");
			employee.setEmpId(1);
			Role role = new Role();
			role.setRoleId(1);
			employee.setRole(role);
			employeeDAO.save(employee);*/
			// System.out.println((employee.getEmpPassword()));

			// System.out.println(demo.decrypt(employee.getEmpPassword()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
