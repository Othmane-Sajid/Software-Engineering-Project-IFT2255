package utils;

public class Menu {

	public static void printReceptionMenu() {
		System.out.println();
		System.out.println(Message.ADD_CLIENT);
		System.out.println(Message.MODIFY_CLIENT);
		System.out.println(Message.DELETE_CLIENT );
		System.out.println(Message.ADD_SERVICE);
		System.out.println(Message.MODIFY_SERVICE);
		System.out.println(Message.DELETE_SERVICE);		
		System.out.println(Message.PRINT_TEF);
		System.out.println(Message.PRINT_PAYMENT_REPORT);
		System.out.print("9 -  ");
		System.out.println(Message.BACK_TO_MAIN);
		System.out.print(Message.CHOOSE);
	}
	
	public static void printMainMenu() {
		System.out.println();
		System.out.println(Message.CHOOSE_UI);
		System.out.print("4 -  ");
		System.out.println(Message.EXIT_MESSAGE);
		System.out.print(Message.CHOOSE);
	}
	
	public static void printMemberMenu() {
		System.out.println();
		System.out.println(Message.ACCESS);
		System.out.println(Message.INSCRIPTION_SEANCE);
		System.out.println(Message.SHOW_BILL);
		System.out.print("4 -  ");
		System.out.println(Message.BACK_TO_MAIN);
		System.out.print(Message.CHOOSE);
	}
	
	public static void printProMenu() {
		System.out.println();
		System.out.println(Message.SHOW_INSCRIPTION_SERSSIONS);
		System.out.println(Message.CONFIRME);
		System.out.println(Message.SHOW_PAY);
		System.out.print("4 -  ");
		System.out.println(Message.BACK_TO_MAIN);
		System.out.print(Message.CHOOSE);
	}
}
