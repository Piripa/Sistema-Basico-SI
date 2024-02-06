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
			System.out.println("Autenticação bem sucedida! Perfil: " + autenticarUsuário.getTipo());
			System.out.println("A senha criptada é igual a " + autenticarUsuário.getSenhaCriptada());
			String dadosToSing = usernameInput;
			switch (autenticarUsuário.getTipo()) {
			case ADMIN:
				System.out.println("Bem vindo, administrador. Você tem a opção de cadastrar e listar os usuários");
				try {
					
					byte[] signature = gerenciar.entrarDados(dadosToSing, gerenciar.getKeyPair().getPrivate());
					if(gerenciar.verificarAssinatura(dadosToSing, signature, gerenciar.getKeyPair().getPublic())) {
						System.out.println("Assinatura válida. Você deseja 1 - cadastrar novos usuários ou 2 - listar os usuários? ");
						int respostaInput = scanner.nextInt();
						switch (respostaInput) {
						case 1:
							System.out.println("Digite o nome do usuário ");
							String usernameInputNew = scanner.next();
							
							System.out.println("Digite a senha ");
							String passwordInputNew =  scanner.next();
							
							gerenciar.adicionaUsuario(usernameInputNew, passwordInputNew, Tipo.USUARIO);
							break;
						case 2:
							gerenciar.getArrayPerfil();
						
						default:
							System.out.println("Obrigado por usar nossos serviços");
						}
						
						
					}
					else {
						System.out.println("Assinatura inválida");
					}
					
					
				}catch (Exception e) {
					e.printStackTrace();
				}	
				break;
			case USUARIO:
				System.out.println("Bem vindo, usuário. Você irá receber a lista de usuários, participantes, que são usuários junto com você");
				gerenciar.getArrayPerfil();
				break;
			case VISITANTE:
				System.out.println("Bem vindo, visitante");
				break;
					
			}
		}
		else {
			System.out.println("Falha na autenticação. Usuario ou senha incorretos!");
		}
		
		scanner.close();
	}
}
