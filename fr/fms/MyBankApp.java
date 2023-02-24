/**
 * Version 1.0 d'une appli bancaire simplifiée offrant la possibilitée de créer des clients, des comptes bancaires associés et des opérations ou
 * transactions bancaires sur ceux-ci telles que : versement, retrait ou virement 
 * + permet d'afficher l'historique des transactions sur un compte
 * + la gestion des cas particuliers est rudimentaire ici puisque la notion d'exception n'a pas encore été abordée
 * 
 * @author El babili - 2022
 * 
 */

package fr.fms;

import java.util.Date;
import java.util.Scanner;

import fr.fms.business.IBankImpl;
import fr.fms.entities.Account;
import fr.fms.entities.Current;
import fr.fms.entities.Customer;
import fr.fms.entities.Saving;
import fr.fms.entities.Transaction;
import fr.fms.entities.withdrawal;

public class MyBankApp {	
	public static void main(String[] args) {
		
		/**
		 * menu principal myBank
		 * 
		 * @author Samuel_LePorcher
		 * @author Camille_Gaschet
		 * 
		 */
		Scanner scan = new Scanner(System.in);
		String inputLine="";
		Long currentaAccId=(long) 0;
		Long withdrawalAccount=(long) 0;
		int choice=0;
		Double amout=(double) 0;
		
		IBankImpl bankJob = new IBankImpl();
		
		System.out.println("création puis affichage de 2 comptes bancaires");
		Customer robert = new Customer(1, "dupont", "robert", "robert.dupont@xmail.com");
		Customer julie = new Customer(2, "jolie", "julie", "julie.jolie@xmail.com");		
		Current firstAccount = new Current(100200300, new Date(), 1500, 200 , robert);
		Saving secondAccount = new Saving(200300400, new Date(), 2000, 5.5, julie);
		
		System.out.println(firstAccount);
		System.out.println(secondAccount);		
		
		bankJob.addAccount(firstAccount);
		bankJob.addAccount(secondAccount);
		System.out.println("Bienvenue chez myBank. Que souhaitez-vous faire ?");


		//check numero de compte
		while(true) {
		try {
			System.out.println("saisissez un numéro de comte bancaire valide");
			currentaAccId=Long.valueOf(scan.nextLine());
			bankJob.consultAccount(currentaAccId);
			break;
		}

	catch(NullPointerException exception) {
		System.out.println("Vous demandez un compte inexistant !");
		
	}
		}
		
		
		// Affichage du menu principal
		while (choice < 6) {
			System.out.println();
			System.out.printf("Bienvenue %s, que souhaitez vous faire ? taper le numéro correspondant\n",bankJob.consultAccount(currentaAccId).getCustomer().getName() );
			System.out.println("1:versement - 2:retrait - 3:virement - 4:information sur ce compte - 5:liste des opérations - 6:sortir");

			choice=Integer.parseInt(scan.nextLine());

try{ //TODO
			switch (choice) {
			 
			case 1:
				System.out.println("versement");
				
				
					
						System.out.printf("saisissez un montant à verser sur le compte");
						amout=Double.parseDouble(scan.nextLine());
						bankJob.pay(currentaAccId, amout);//IllegalArgumentException si amount saisi != chiffre
						
					
					
				
				break;
			case 2:
				System.out.println("retrait");
				
					
						System.out.printf("saisissez un montant à retirer sur le compte");
						amout=Double.parseDouble(scan.nextLine());
						if (bankJob.withdraw(currentaAccId, amout)==false) {
							
							throw new Exception("trow1");
							
						}
						
						
					
				
				break;
			case 3:
				System.out.println("virement");
				
					
						System.out.printf("saisissez un montant à retirer sur le compte");
						amout=Double.parseDouble(scan.nextLine());
						System.out.printf("saisissez le numero de compte destinataire");
						withdrawalAccount=Long.parseLong(scan.nextLine());
						bankJob.transfert(currentaAccId, withdrawalAccount, amout);
						
					
//					throws BankException;
					
				
				break;
			case 4:
				System.out.println("information sur ce compte");
				break;
			case 5:
				System.out.println("liste des opérations");
				break;
			case 6:
				System.out.println("sortir");
				break;
			}

			}catch (IllegalArgumentException e) {//
				System.out.println("entrée non correcte");
				
				}
		
			catch (Exception e) {//
			System.out.println("Vous avez depassé vos capacité de retrait !");
			
			}
//			catch (Exception e) {//
//				System.out.println("vous ne pouvez retirer et verser sur le même compte !");
//				exception=false;
//			}
		}
			
		scan.close();
		
		
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		//représente l'activité de notre banque
//		IBankImpl bankJob = new IBankImpl();
//		
//		System.out.println("création puis affichage de 2 comptes bancaires");
//		Customer robert = new Customer(1, "dupont", "robert", "robert.dupont@xmail.com");
//		Customer julie = new Customer(2, "jolie", "julie", "julie.jolie@xmail.com");		
//		Current firstAccount = new Current(100200300, new Date(), 1500, 200 , robert);
//		Saving secondAccount = new Saving(200300400, new Date(), 2000, 5.5, julie);
//		
//		System.out.println(firstAccount);
//		System.out.println(secondAccount);		
//		
//		bankJob.addAccount(firstAccount);
//		bankJob.addAccount(secondAccount);
//		
//		//banquier ou client
//		bankJob.pay(firstAccount.getAccountId(),500);		// versement de 500 euros sur le compte de robert
//		bankJob.pay(secondAccount.getAccountId(), 1000);	// versement de 1000 euros sur le compte de julie
//		
//		//banquier ou client
//		bankJob.withdraw(100200300, 250);			// retrait de 250 euros sur le compte de robert
//		bankJob.withdraw(200300400, 400);			// retrait de 400 euros sur le compte de julie
//		
//		//banquier ou client
//		bankJob.transfert(firstAccount.getAccountId(), 200300400, 200);		// virement de robert chez julie de 200
//		System.out.println("----------------------------------------------------------");
//		System.out.println("solde de "+ firstAccount.getCustomer().getName() + " : " + bankJob.consultAccount(firstAccount.getAccountId()).getBalance());
//		System.out.println("solde de "+ secondAccount.getCustomer().getName() + " : "+ bankJob.consultAccount(secondAccount.getAccountId()).getBalance());
//		System.out.println("----------------------------------------------------------");
//		bankJob.consultAccount(111111);		//test du compte inexistant
//		bankJob.withdraw(100200300, 10000);	//test capacité retrait dépassée
//		bankJob.transfert(100200300, 100200300, 50000);		//test virement sur le même compte
//		
//		//banquier
//		bankJob.addAccount(firstAccount);	//test rajout du même compte au même client
//		bankJob.addAccount(new Current(300400500, new Date(), 750, 150 , julie));	//ajout nouveau compte à Julie		
//		System.out.println("\n-----------------------Liste des comptes de ma banque-----------------------------------");
//		for(Account acc : bankJob.listAccounts())
//			System.out.println(acc);
//		System.out.println("\n-----------------------Liste des comptes de julie-----------------------------------");
//		for(Account acc : julie.getListAccounts()) {
//			System.out.println(acc);
//		}
//		
//		//banquier ou client
//		System.out.println("\n-------------------liste des transactions de l'unique compte de robert------------------------");
//		for(Transaction trans : bankJob.listTransactions(100200300))
//			System.out.println(trans);
//		System.out.println("-------------------liste des transactions du compte N° 200300400 de Julie------------------------");
//		for(Transaction trans : bankJob.listTransactions(200300400))
//			System.out.println(trans);
	}

