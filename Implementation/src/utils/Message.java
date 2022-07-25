package utils;

public class Message {
	public static final String CHOOSE_UI = "Vous êtes 1 - Agent de la réception,  2 - Membre,   3 - Professionnel";
	public static final String WARNING_MESSAGE = "Veuillez entrer un chiffre parmi les options données.";
	public static final String SEPERATOR = "=================================================================";
	public static final String ACCESS = "1. Demander l'accès à la GYM";
	public static final String INSCRIPTION_SEANCE = "2. Inscire à une séance";
	
	public static final String SHOW_INSCRIPTION_SERSSIONS = "1. Consulter la liste de l'inscription des séances";	
	public static final String CONFIRME = "2. Le membre veut confirmer la présence d'une séance avec son compte FB";
	
	public static final String BACK_TO_MAIN = "Retourner au menu principal";
	
	
	//------------- Menu ----------------
	public static final String SEEDING = "0. Initialiser des données par défault";
	public static final String ADD_CLIENT = "1. Ajouter un nouveau client";
	public static final String MODIFY_CLIENT = "2. Mettre à jour un client";
	public static final String DELETE_CLIENT = "3. Supprimer un client";
	public static final String ADD_SERVICE = "4. Ajouter un nouveau service";
	public static final String MODIFY_SERVICE = "5. Mettre à jour un service";
	public static final String DELETE_SERVICE = "6. Supprimer un service";		
	public static final String PRINT_TEF = "7. Imprimer les fichier TEF";
	public static final String PRINT_PAYMENT_REPORT = "8. Imprimer le rapport de paiement";	
	public static final String EXIT_MESSAGE = "Quitter le menu";
	public static final String CHOOSE = "Veuillez choisir une option: ";
	
	
	//-------------- Client operation ---------
	// 1. -------------- Switch type -------------
	public static final String ADD_CHOOSE = "Vous voulez ajouter: 1 - Membre, 2 - Professional";
	public static final String UPDATE_CHOOSE = "Vous voulez mettre à jour: 1 - Membre, 2 - Professional";
	public static final String DELETE_CHOOSE = "Vous voulez supprimer: 1 - Membre, 2 - Professional";
	public static final String ACCESS_CHOOSE = "1 - Membre 2 - Profesionnel demande d'accès";
	
	// 2. ------------- Ask Email ---------------
	public static final String ASK_MEMBER_EMAIL = "Veuillez entrer le courriel de membre (pour login via Facebook si le client utilise l'application mobile): ";
	public static final String ASK_PRO_EMAIL = "Veuillez entrer le courriel de professionel (pour login via Facebook si le client utilise l'application mobile): ";
	
	
	// 3. ------------- Create ---------------
	public static final String ADD_CLIENT_NOM = "Nom: ";
	public static final String ADD_CLIENT_EMAIL = "Courriel: ";
	public static final String ADD_MEMBER_ASK_PAY = "Est-ce que le membre a payé le frais? 1 - Oui, 2 - Non";
	public static final String ADD_CLIENT_PROVINCE = "Province: ";
	public static final String ADD_CLIENT_CITY = "Ville: ";
	public static final String ADD_CLIENT_ADDRESS = "Adresse: ";
	public static final String ADD_CLIENT_ZIPCODE = "Code postal: ";
	
	
	// 4. ------------- Update -------------------
	public static final String MODIFY_ASK_MEMBER = "Vous voulez modifier: 0 - Nom, 1 - Courriel, 2 - Province, 3 - Ville, 4 - Code Postal  5 - Adresse";
	public static final String MODIFY_ASK_PRO = "Vous voulez modifier: 0 - Nom, 1 - Courriel, 2 - Province, 3 - Ville, 4 - Code Postal  5 - Adresse";
	public static final String NEW_NAME = "Nouveau nom: "; 
	public static final String NEW_EMAIL = "Nouveau courriel: "; 
	public static final String NEW_PROVINCE = "Nouvelle province: ";
	public static final String NEW_CITY = "Nouvelle ville: ";
	public static final String NEW_ADDRESS = "Nouvelle adresse: ";
	public static final String NEW_ZIP_CODE = "Nouveau code postal: ";
	
	public static final String SUCCESS_MODIFY = "Vous avez bien mis à jour les info!";
	
	// 5. ------------ Member account state ----------
	public static final String NOT_EXISTE = "Le client n'existe pas."; 
	public static final String MEMBER_INVALID = "Invalide!";
	public static final String MEMBER_SUSPENDED = "Membre suspendu!";
	public static final String MEMBER_VALID = "Valide!";
	
	
	// 6. ---------- Show ---------------------
	public static final String SHOW_MEMBER_LIST = "Voici la liste des membres";
	public static final String SHOW_PRO_LIST = "Voici la liste des professionnels";
	public static final String SHOW_ID = "Voici votre numéro de client: ";
	
	// 7. ---------- Delete --------------------
	public static final String SUCCESS_DELETE = "Vous avez bien supprimé ce compte!";
	public static final String DELETE_SERVICE_ASSOC = "Les services associés à ce compte ont été aussi supprimés.";
	
	// 8. ---------- Show bill / pay ------------------
	public static final String SHOW_BILL = "3. Factures en ligne.";
	public static final String SHOW_PAY = "3. Avis de paiement.";
	
	//-------------- Service operation ---------
	// 1. Show
	public static final String SHOW_SERVICE_LIST = "Voici la liste des services";
	public static final String SHOW_SESSION_LIST = "Voici la liste des sessions du service choisi";
	public static final String SHOW_CODE = "Voici le code du service: ";
	
	// 2. Ask code
	public static final String ASK_SERVICE_CODE = "Veuillez entrer le code du service";
	
	// 3. Create 
	public static final String ADD_SERVICE_START_DATE = "Date de début du service(JJ-MM-AAAA)";
	public static final String ADD_SERVICE_END_DATE = "Date de fin du service(JJ-MM-AAAA)";
	public static final String ADD_SERVICE_HOUR = "Heure du service (HH:MM)";
	public static final String ADD_SERVICE_Recurrence = "Récurrence hebdomadaire des séances ex: 1,2,3 sans espace";
	public static final String ADD_MAX_CAP = "Capacité maximale (30 recommendé)";
	public static final String ADD_PROID = "Numéro du professionnel (9 chiffres)";
	public static final String ADD_COST = "Frais du service (jusqu'à 100.00$)"; 
	public static final String ADD_COMMENT = "Veuillez entrer les commentaires:";
	public static final String ADD_SERVICE_NAME = "Veuillez entrer le nom du service";
	
	// 4. Service and Session exist	
	public static final String SERVICE_NOT_EXISTE = "Le service n'existe pas."; 
	public static final String SESSION_NOT_EXISTE = "La session n'existe pas."; 
	
	
	// 5. Update
	public static final String MODIFY_ASK_SERVICE = "Vous voulez modifier 1 - Nom du service, 8 - Capacité, 10 - Frais par session, 11 - Commentaire, 4 - Une session particulière";
	public static final String NEW_MAXCAP = "Nouvelle capacité: "; 
	public static final String NEW_COST = "Nouveau frais: "; 
	public static final String NEW_COMMENT = "Nouveaux commentaiaries: "; 
	public static final String CHOOSE_SESSION_TO_MODIFY = "Veuillez entrer le code de session que vous voulez mettre à jour";
	
	public static final String CHOOSE_CRUD_SESSION = "1 - Supprimer une session, 2 - Ajouter une session, 3 - Mettre à jour une session"; 
	public static final String ADD_START_DATE = "Nouvelle date de début de session (JJ-MM-AAAA): ";
	public static final String ADD_END_DATE = "Nouvelle date de fin (JJ-MM-AAAA): ";
	public static final String ADD_HOUR = "Nouvelle heure (HH:MM): ";
	public static final String ADD_DAY = "Jour de la semaine: ";
		
	public static final String MODIFY_ASK_SESSION = "Vous voulez modifier 1 - Heure, 2 - Jour de la Semaine, 3 - Date de début, 4 - Date de fin \r\n Entrer l'option en premier et ensuite la nouvelle valeur.";
	public static final String NEW_HOUR_SESSION = "Nouvelle heure de session(HH:MM)";
	public static final String NEW_DAYOFWEEK_SESSION = "Nouveau jour de la semaine(1 - 7)";
	public static final String NEW_START_DATE_SESSION = "Nouvelle date de début de session (JJ-MM-AAAA)";
	public static final String NEW_END_DATE_SESSION = "Nouvelle date de fin (JJ-MM-AAAA)";
	public static final String ASK_SESSION_ID = "Veuillez entrer le code de session";
	
	// 6. Delete
	public static final String DELETE_SESSION = "Vous avez bien supprimé cette séance";
	public static final String DELETE_SERVCE_OK = "Vous avez bien supprimé ce service";
	
	//---------------- Subscription Session ------------------
	public static final String CHOOSE_SERVICE = "Veuillez entrer le code de service que vous voulez choisir";
	public static final String CHOOSE_SESSION = "Veuillez entrer le code de session que vous voulez choisir";
	public static final String MAX_CAP = "La capacité maximale est atteinte.";
	public static final String ENTER_DATE = "Veuillez entrer la date d'aujour d'hui(JJ-MM-AAAA)";
	public static final String ENTER_COMMENT = "Veuillez entrer les commentaires";
	public static final String SHOW_SUB_LIST = "Voici la liste de toutes les incriptions des sessions";
	
	//---------------- Confirmation presence -------------------
	public static final String REFUSE_CONFIRMATION = "Vous n'êtes pas inscrit à cette séance";
	public static final String CONFIRMATION_PRESENCE = "Vous avez bien confirmé votre présence!";
	public static final String SHOW_CONFIRMATION_LIST = "Voici la liste des confirmations des présences";
	public static final String SESSION_CODE_INVALID = "Le code de session est invalide!";
	
	// --------------- Synthesis ---------------------
	public static final String SHOW_SYNTHESIS = "=========Synthesis=============";
	
	// --------------- TEF ---------------------
	public static final String SHOW_TEF = "=========TEF=============";

	// Common
    public static final String ASK_NEWVALUE = "Veuillez entrer la nouvelle valeur.";
    public static final String PRESS_ENTER = "Appuyez sur enter si l'application ne répond pas.";
}
