package br.com.seguranca;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.Base64;

public class Gerencia {
	private ArrayList<Perfil> perfis;
	private KeyPair keyPair;
	
	public Gerencia() {
		this.perfis = new ArrayList<>();
		this.keyPair = generateKeyPair();
	}
	
	public void adicionaUsuario(String login, String password, Tipo tipo) {
		Perfil novoPerfil = new Perfil(login,password,tipo);
		perfis.add(novoPerfil);
		System.out.println("Perfil criado com sucesso");
		
	}
	
	public Perfil autenticarPerfil(String login, String password) {
		for(Perfil perfil : perfis) {
			if(perfil.getLogin().equals(login) && verificarSenha(password, perfil.getSenhaCriptada())) {
				return perfil;
			}
		}
		return null;
	}

	private boolean verificarSenha(String password, String senhaCriptada) {
		return senhaCriptada.equals(CriptarSenha(password));
		
	}
	
	private String CriptarSenha(String password) {
		return Base64.getEncoder().encodeToString(password.getBytes());
	}

	public byte[] entrarDados(String data, PrivateKey privateKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
		Signature signature = Signature.getInstance("SHA256withRSA");
		signature.initSign(privateKey);
		signature.update(data.getBytes());
		return signature.sign();
	}
	
	public boolean verificarAssinatura(String data, byte[] signature, PublicKey publicKey) throws InvalidKeyException, NoSuchAlgorithmException, SignatureException {
		Signature verificaAssinatura = Signature.getInstance("SHA256withRSA");
		verificaAssinatura.initVerify(publicKey);
		verificaAssinatura.update(data.getBytes());
		return verificaAssinatura.verify(signature);
	}

	private KeyPair generateKeyPair() {
		try {
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			keyPairGenerator.initialize(2048);
			return keyPairGenerator.generateKeyPair();
		}catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	
	
		
	}
	
	
	
}
