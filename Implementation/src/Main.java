import java.util.LinkedList;
import java.util.Scanner;

import controller.adv.*;
import controller.crud.CRUDOperation;
import model.Member;
import model.enums.PromptType;
import utils.Message;
import model.Professional;
import utils.FileManager;
import utils.Menu;
import view.Console;


public class Main {
	public static void main(String[] args) throws Exception {
		FileManager.initData(false);
		FileManager.readObjects(true);

		MemberOperation memberOperation = new MemberOperation();
		AccountingOperation accountingOperation = new AccountingOperation();
		SessionOperation sessionOperation = new SessionOperation();
		SubSessionOperation subSessionOperation = new SubSessionOperation();
		ConfirmPresenceOperation confirmPresenceOperation = new ConfirmPresenceOperation();
		ProOperation proOperation = new ProOperation();

		Console console = new Console(
			memberOperation,
			accountingOperation,
			sessionOperation,
			subSessionOperation,
			confirmPresenceOperation, 
			proOperation
		);

		LinkedList<Professional> proList = Professional.getProList();

		Scanner input = new Scanner(System.in); 

		loop:
		while(true) {
			Menu.printMainMenu();
			int optionMainMenu = console.promptGlobalChoice();

			switch(optionMainMenu) {
						
				// Menu for Reception
				case -1:
					System.exit(-1);
					break;
				case 1: 
					while(true) {
						Menu.printReceptionMenu();
						int optionReception = console.promptGlobalChoice();
						if (optionReception == -1) break;

						switch(optionReception) {

							// Add new client
							case 1:
								System.out.println(Message.ADD_CHOOSE);
								int op1 = console.promptGlobalChoice();
								switch(op1) {
									case 1: console.promptAddMember();
									break;
									case 2: console.promptAddPro();
									break;
									default: System.out.println(Message.WARNING_MESSAGE);
								}
								break;

							// Update client
							case 2:
								System.out.println(Message.UPDATE_CHOOSE);
								int op2 = input.nextInt();
								input.nextLine();
								int option;
								String newValue;
								switch(op2) {
									case 1:
										System.out.println(Message.ASK_MEMBER_EMAIL);
										Scanner inputUpdate = new Scanner(System.in);
										String memberEmail = inputUpdate.nextLine();
										option = console.promptCaseChoose(PromptType.MEMBER);
										System.out.println(Message.ASK_NEWVALUE);
										newValue = inputUpdate.nextLine();
										console.promptUpdateMember(memberEmail, option, newValue);
										break;
									case 2:
										System.out.println(Message.ASK_PRO_EMAIL);
										Scanner inputProUpdate = new Scanner(System.in);
										String proEmail = inputProUpdate.nextLine();
										option = console.promptCaseChoose(PromptType.MEMBER);
										System.out.println(Message.ASK_NEWVALUE);
										newValue = inputProUpdate.nextLine();
										console.promptUpdatePro(proEmail, option, newValue);
										break;
									default: System.out.println(Message.WARNING_MESSAGE);
								}
								break;

							// Delete client
							case 3:
								System.out.println(Message.DELETE_CHOOSE);
								int op3 = input.nextInt();
								input.nextLine();
								switch(op3) {
									case 1:
										System.out.println(Message.ASK_MEMBER_EMAIL);
										Scanner inputMemberEmail = new Scanner(System.in);
										String memberEmail = inputMemberEmail.nextLine();
										console.promptDeleteMember(memberEmail);
										break;
									case 2:
										System.out.println(Message.ASK_PRO_EMAIL);
										Scanner inputProEmail = new Scanner(System.in);
										String proEmail = inputProEmail.nextLine();
										console.promptDeletePro(proEmail);
										break;
									default: System.out.println(Message.WARNING_MESSAGE);
								}
								break;

							// Add new service
							case 4:
								console.promptAddService();
								break;

							// Update service or sessions
							case 5:
								System.out.println(Message.ASK_SERVICE_CODE);
								Scanner inputServiceUpdate = new Scanner(System.in);
								String serviceCodeForModif = inputServiceUpdate.nextLine();
								option = console.promptCaseChoose(PromptType.SERVICE);

								if (option != 4) {
									System.out.println(Message.ASK_NEWVALUE);
									newValue = inputServiceUpdate.nextLine();
									console.promptUpdateService(serviceCodeForModif.trim(), option, newValue);
								} else {
									option = console.promptCaseChoose(PromptType.SESSION_CRUD);
									Scanner inputSession;
									String sessionCodeForModif= "";

									if (option != 2) {
										System.out.println(Message.ASK_SESSION_ID);
										inputSession = new Scanner(System.in);
										sessionCodeForModif = inputSession.nextLine().trim();
									}

									switch (option) {
										case 1:
											console.promptDeleteSession(serviceCodeForModif, sessionCodeForModif);
											break;
										case 2:
											console.promptAddSession(serviceCodeForModif);
											break;
										case 3:
											option = console.promptCaseChoose(PromptType.SESSION_UPD);
											newValue = inputServiceUpdate.nextLine();
											console.promptUpdateSession(serviceCodeForModif.trim(), sessionCodeForModif, option, newValue);
											break;
										default: System.out.println(Message.WARNING_MESSAGE);
									}
								}
								break;

							// Delete service
							case 6:
								System.out.println(Message.ASK_SERVICE_CODE);
								Scanner inputServiceCodeForDel = new Scanner(System.in);
								String serviceCodeForDel = inputServiceCodeForDel.nextLine();
								console.promptDeleteService(serviceCodeForDel.trim());
								break;

							// Print TEF, bills for members and pros
							case 7:
								console.promptShowTEF();
								break;

							// Print report
							case 8:
								console.promptShowSynthesis();
								break;

							// Quit application
							case 9:
								FileManager.writeObjects(false);
								continue loop;

							default: System.out.println(Message.WARNING_MESSAGE);
						}
					}
					
				
				// Menu for member
				case 2: 					
					System.out.println(Message.ASK_MEMBER_EMAIL);
					Scanner inputMemberEmail = new Scanner(System.in);
					String memberEmail = inputMemberEmail.nextLine();	
					int indexMember = CRUDOperation.find(new Member(memberEmail));
					if (indexMember >= 0) {
						while(true) {
							Menu.printMemberMenu();
							int optionMember = input.nextInt();
							switch(optionMember) {
								case 1: // access 														
									console.promptMemberAccess(memberEmail);
									break;  								
								case 2: // subscription to a service 									
									console.promptSubSession(memberEmail);
									break;
								case 3: // show bills								
									console.promptShowBills(memberEmail);
									break;
								case 4:
									FileManager.writeObjects(false);
									continue loop;
								default: System.out.println(Message.WARNING_MESSAGE); 									
							}
						}
					} else {
						System.out.println(Message.NOT_EXISTE);
					}					
					break;
					
					
				// Menu for professional
				case 3:
					System.out.println(Message.ASK_PRO_EMAIL);
					Scanner inputProEmail = new Scanner(System.in);
					String proEmail = inputProEmail.nextLine();

					int indexPro = CRUDOperation.find(new Professional(proEmail));

					if (indexPro >= 0) {
						while(true) {
							Menu.printProMenu();
							int optionPro = input.nextInt();
							switch(optionPro) {
								case 1: // show subscription list 
									String proId = proList.get(indexPro).getId();
									console.promptShowSubSessionByProId(proId);
									break;
								
								case 2: // Confirm presence of session 
									System.out.println(Message.ASK_MEMBER_EMAIL);
									Scanner inputMemberEmailForOp2 = new Scanner(System.in); 
									String memberEmailForOp2 = inputMemberEmailForOp2.nextLine();
									
									System.out.println(Message.ASK_SESSION_ID);
									Scanner inputSessionIdForOp2 = new Scanner(System.in); 
									String sessionIdForOp2 = inputSessionIdForOp2.nextLine();
									String proIdForOp2 = proList.get(indexPro).getId();
									console.promptConfirmPresence(sessionIdForOp2, memberEmailForOp2, proIdForOp2);
									break;
								
								case 3: //  show payment notification								
									console.promptShowPayment(proEmail);
									break;
								case 4:
									FileManager.writeObjects(false);
									continue loop;									
								default: System.out.println(Message.WARNING_MESSAGE); 		
							}
						}
					} else {
						System.out.println(Message.NOT_EXISTE);
					}
					
				case 4: 
					FileManager.writeObjects(false);
					System.exit(0);
					
				default: System.out.println(Message.WARNING_MESSAGE); 								
			}						
		}
	}
}
