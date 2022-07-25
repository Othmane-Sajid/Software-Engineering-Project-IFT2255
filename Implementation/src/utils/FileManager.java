package utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;

import model.enums.AccountState;
import model.ConfirmPresence;
import model.Member;
import model.Professional;
import model.Service;
import model.Session;
import model.SubSession;

public class FileManager {
	private final static String PATH_LIST_SERVICE = "data/ServiceList.txt";
	private final static String PATH_LIST_MEMBER = "data/MemberList.txt";
	private final static String PATH_LIST_PRO = "data/ProList.txt";
	private final static String PATH_LIST_SUBSESSION = "data/SubSessionList.txt";
	private final static String PATH_LIST_CONFIRMPRESENCE = "data/ConfirmPresenceList.txt";

	static LinkedList<Service> serviceList = Service.getServiceList();
	static LinkedList<Member> memberList = Member.getMemberList();
	static LinkedList<Professional> proList = Professional.getProList();
	static LinkedList<SubSession> subSessionList = SubSession.getListSubSession();
	static LinkedList<ConfirmPresence> confirmPresenceList = ConfirmPresence.getConfirmPresenceList();


	/**
	 *
	 * Creates the data and populate the text files.
	 *
	 * When all files and the data repository exists, does not recreate.
	 *
	 * If none exists, it will create the /data repertory and the text files in it.
	 *
	 * @param verbose
	 * @throws Exception
	 */
	public static void initData(boolean verbose) throws Exception {
		File memberFile = new File(PATH_LIST_MEMBER);

		if (memberFile.length() == 0) {
			Files.createDirectories(Paths.get("./data"));

			ObjectOutputStream oosService = new ObjectOutputStream(new FileOutputStream(new File(PATH_LIST_SERVICE)));
			ObjectOutputStream oosMember = new ObjectOutputStream(new FileOutputStream(new File(PATH_LIST_MEMBER)));
			ObjectOutputStream oosPro = new ObjectOutputStream(new FileOutputStream(new File(PATH_LIST_PRO)));
			ObjectOutputStream oosSubSession = new ObjectOutputStream(new FileOutputStream(new File(PATH_LIST_SUBSESSION)));
			ObjectOutputStream oosConfirmPresence = new ObjectOutputStream(new FileOutputStream(new File(PATH_LIST_CONFIRMPRESENCE)));

			// ---------- Member ----------
			Member member1 = new Member("123456789", "member1", "member1@gym.com", "Quebec", "Montreal", "H3T 1J4", "2900 Edouard-Montpetit", AccountState.valid);
			oosMember.writeObject(member1);
			oosMember.close();
			if (verbose)
				System.out.println("Initial member written into text file!");


			// -------------Pro-----------------------------
			Professional pro1 = new Professional("987654321", "pro1", "pro1@gym.com", "Quebec", "Montreal", "H3A 0G4", "845 Sherbrooke O.");
			oosPro.writeObject(pro1);
			oosPro.close();
			if (verbose)
				System.out.println("Initial pro written into text file!");


			// ------ Service ---------
			Session sess1 = new Session("000","987654321","0001111","12:00","01-01-2021","01-01-2022", 1);
			Session sess2 = new Session("000","987654321","0002222","12:00","01-05-2021","01-01-2022", 2);
			sess1.getParticipantsId().add("123456789");
			sess2.getParticipantsId().add("123456789");
			Service service1 = new Service("tennis", "000", "01-01-2021","01-01-2022","12:00","4,5", 30, "987654321", 10.0, "comment1");

			service1.getListSessionsAffiliated().add(sess1);
			service1.getListSessionsAffiliated().add(sess2);

			oosService.writeObject(service1);
			oosService.close();
			if (verbose)
				System.out.println("Initial service data written into text file!");


			// ------ SubSession ------
			SubSession subSession1 = new SubSession("01-05-2021 13:00:00", "01-05-2021","01-01-2022",10.0,"commentSS", "000", "0001111", "123456789", "987654321");
			SubSession subSession2 = new SubSession("03-05-2021 13:00:00", "03-05-2021","01-01-2022",10.0,"commentSS", "000", "0002222", "123456789", "987654321");
			oosSubSession.writeObject(subSession1);
			oosSubSession.writeObject(subSession2);
			oosSubSession.close();
			if (verbose)
				System.out.println("Initial sub session data written into text file!");


			// ------ Confirm presence -----
			ConfirmPresence confirmPresence1 = new ConfirmPresence("01-06-2021 13:00:00", "987654321", "123456789", "0001111", "comentCP", "tennis", 10.0);
			ConfirmPresence confirmPresence2 = new ConfirmPresence("10-06-2021 13:00:00", "987654321", "123456789", "0002222", "comentCP", "tennis", 10.0);
			oosConfirmPresence.writeObject(confirmPresence1);
			oosConfirmPresence.writeObject(confirmPresence2);
			oosConfirmPresence.close();
			if (verbose)
				System.out.println("Initial confirm presence data written into text file!");
		}
		else
			System.out.println("File is not empty, seeding aborded.");
	}

	/**
	 *
	 * Deletes all the files in /data.
	 *
	 * Useful for unit testing.
	 *
	 * @param verbose
	 * @throws Exception
	 */
	public static void deleteData(boolean verbose) throws Exception {
		File serviceFile = new File(PATH_LIST_SERVICE);
		File memberFile = new File(PATH_LIST_MEMBER);
		File proFile = new File(PATH_LIST_PRO);
		File subServiceFile = new File(PATH_LIST_SUBSESSION);
		File confirmPresenceFile = new File(PATH_LIST_CONFIRMPRESENCE);

		if (serviceFile.delete() &&
			memberFile.delete() &&
			proFile.delete() &&
			subServiceFile.delete() &&
			confirmPresenceFile.delete() &&
			verbose) {
			System.out.println("Files deleted.");

			serviceList.clear();
			memberList.clear();
			proList.clear();
			subSessionList.clear();
			confirmPresenceList.clear();
		}
	}

	/**
	 *
	 * Read all data from the text files and populates them in the data structure.
	 *
	 * Useful on application's start.
	 *
	 * @param verbose
	 * @throws Exception
	 */
	public static void readObjects(boolean verbose) throws Exception {
		FileInputStream fisService = new FileInputStream("data/ServiceList.txt");
		FileInputStream fisMember = new FileInputStream("data/MemberList.txt");
		FileInputStream fisPro = new FileInputStream("data/ProList.txt");
		FileInputStream fisSubSession = new FileInputStream("data/SubSessionList.txt");
		FileInputStream fisConfirmPresence = new FileInputStream("data/ConfirmPresenceList.txt");

		ObjectInputStream oisService = new ObjectInputStream(fisService);
		ObjectInputStream oisMember = new ObjectInputStream(fisMember);
		ObjectInputStream oisPro = new ObjectInputStream(fisPro);
		ObjectInputStream oisSubSession = new ObjectInputStream(fisSubSession);
		ObjectInputStream oisConfirmPresence = new ObjectInputStream(fisConfirmPresence);


		// ---------- Service --------
		if (verbose) {
			System.out.println("Reading service data into system...");
			System.out.println();
		}
		try {
			while(fisService.available() > 0) {
		       	 Service serviceOut = (Service) oisService.readObject();
		       	 if (serviceOut != null) {serviceList.add(serviceOut);}
			}
		} catch (EOFException exception){
			System.out.println("End of ServiceList file");
		}

		oisService.close();


		if (verbose) {
			if (serviceList.size() != 0) {
				for(Service service : serviceList) {
					System.out.print(service);
				}
			}
			System.out.println("Done!");
			System.out.println();
		}



		// ------------ Member --------
		if (verbose) {
			System.out.println("Reading member data into system...");
			System.out.println();
		}
		try {
			while(fisMember.available() > 0) {
		       	 Member memberOut = (Member) oisMember.readObject();
		       	 if (memberOut != null) { memberList.add(memberOut);}
			}
		} catch(EOFException exception) {
			System.out.println("End of MemberList file");
		}

		oisMember.close();


		if (verbose) {
			if (memberList.size() != 0) {
				for(Member member: memberList) {
					System.out.print(member);
				}
			}
			System.out.println("Done!");
			System.out.println();
		}


		// ----------- Pro -------------
		if (verbose) {
			System.out.println("Reading pro data into system...");
			System.out.println();
		}
		try {
			while(fisPro.available() > 0) {
		       	 Professional proOut = (Professional) oisPro.readObject();
		       	 if(proOut != null) {proList.add(proOut);}
			}
		} catch (EOFException exception){
			System.out.println("End of ProList file");
		}

		oisPro.close();


		if (verbose) {
			if (proList.size() != 0) {
				for(Professional pro: proList) {
					System.out.print(pro);
				}
			}
			System.out.println("Done!");
			System.out.println();
		}


		// ----------- Subscribe Session -------------
		if (verbose) {
			System.out.println("Reading sub session data into system...");
			System.out.println();
		}
		try {
			while(fisSubSession.available() > 0) {
		       	 SubSession subSessionOut = (SubSession) oisSubSession.readObject();
		       	 if(subSessionOut != null) {subSessionList.add(subSessionOut);}
			}
		} catch(EOFException exception) {
			System.out.println("End of SubSessionList file");
		}

		oisSubSession.close();


		if (verbose) {
			if (subSessionList.size() != 0) {
				for(SubSession subSession: subSessionList) {
					System.out.print(subSession);
				}
			}
			System.out.println("Done!");
			System.out.println();
		}



		// ----------- Confirm Presence  -------------
		if (verbose) {
			System.out.println("Reading confirme presence data into system...");
			System.out.println();
		}
		try {
			while(fisConfirmPresence.available() > 0) {
				ConfirmPresence confirmPresenceOut = (ConfirmPresence) oisConfirmPresence.readObject();
		       	if(confirmPresenceOut != null) {confirmPresenceList.add(confirmPresenceOut);}
			}
		} catch (EOFException exception) {
			System.out.println("End of ConfirmPresenceList file");
		}


		oisConfirmPresence.close();


		if (verbose) {
			if (confirmPresenceList.size() != 0) {
				for(ConfirmPresence confirmPresence: confirmPresenceList) {
					System.out.print(confirmPresence);
				}
			}
			System.out.println("Done!");
			System.out.println();
		}

	}

	/**
	 *
	 * Writes all data from the structure into the text files.
	 *
	 * Useful on application's end.
	 *
	 * @param verbose
	 * @throws Exception
	 */
	public static void writeObjects(boolean verbose) throws Exception {
		ObjectOutputStream oosService = new ObjectOutputStream(new FileOutputStream(new File("data/ServiceList.txt")));
		ObjectOutputStream oosMember = new ObjectOutputStream(new FileOutputStream(new File("data/MemberList.txt")));
		ObjectOutputStream oosPro = new ObjectOutputStream(new FileOutputStream(new File("data/ProList.txt")));
		ObjectOutputStream oosSubSession = new ObjectOutputStream(new FileOutputStream(new File("data/SubSessionList.txt")));
		ObjectOutputStream oosConfirmPresence = new ObjectOutputStream(new FileOutputStream(new File("data/ConfirmPresenceList.txt")));
		
		
		// ----------- services written into ServiceList.txt --------------//
		for(Service service : serviceList) {
			oosService.writeObject(service);
		}
		
		oosService.writeObject(null);
	    oosService.close();
	    if (verbose) {
			System.out.println("All services written into ServiceList.txt file!");
			System.out.print(serviceList);
			System.out.println();
			System.out.println();
		}

	    
	    
	    // ----------- members written into MemberList.txt --------------//
	    for(Member member : memberList) {
			oosMember.writeObject(member);
		}
		
		oosMember.writeObject(null);
	    oosMember.close();
		if (verbose) {
			System.out.println("All members written into MemberList.txt file!");
			System.out.print(memberList);
			System.out.println();
			System.out.println();
		}
	    
	    // ----------- pros written into ProList.txt --------------//
	    for(Professional pro : proList) {
			oosPro.writeObject(pro);
		}
		
	    oosPro.writeObject(null);
	    oosPro.close();
		if (verbose) {
			System.out.println("All pros written into ProList.txt file!");
			System.out.print(proList);
			System.out.println();
			System.out.println();
		}
	    
	    
	    // ----------- sub sessions written into SubSessionList.txt --------------//
	    for(SubSession subSession : subSessionList) {
			oosSubSession.writeObject(subSession);
		}
		
	    oosSubSession.writeObject(null);
	    oosSubSession.close();
		if (verbose) {
			System.out.println("All sub sessions written into SubSessionList.txt file!");
			System.out.print(subSessionList);
			System.out.println();
			System.out.println();
		}
	    
	    
	    
	    // ----------- confirm presence written into ConfirmPresenceList.txt --------------//
	    for(ConfirmPresence confirmPresence : confirmPresenceList) {
			oosConfirmPresence.writeObject(confirmPresence);
		}
		
	    oosConfirmPresence.writeObject(null);
	    oosConfirmPresence.close();
		if (verbose) {
			System.out.println("All confirm presence written into ConfirmPresenceList.txt file!");
			System.out.print(confirmPresenceList);
			System.out.println();
			System.out.println();
		}
	}
}
