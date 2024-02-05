package br.com.seguranca;

import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		Gerencia gerenciar = new Gerencia();
		Scanner scanner = new Scanner(System.in);
		
		gerenciar.adicionaUsuario("admin", "admin123", Tipo.ADMIN);
		gerenciar.adicionaUsuario("user", "user123", Tipo.USUARIO);
		gerenciar.adicionaUsuario("visitant", "visitant123", Tipo.VISITANTE);
		
		System.out.println("Digite o nome do usuário");
		String usernameInput = scanner.nextLine();
		
		System.out.println("Digite a senha");
		String passwordInput =  scanner.nextLine();
		
		Perfil autenticarUsuário = gerenciar.autenticarPerfil(usernameInput, passwordInput);
		
		if (autenticarUsuário != null) {
			System.out.println("Autenticação bem sucedida! Perfil :" + autenticarUsuário.getTipo());
		}
		else {
			System.out.println("Falha na autenticação. Usuario ou senha incorretos!");
		}
		
		scanner.close();
	}
}
