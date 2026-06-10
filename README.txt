Clément		Romain		Arthur		Nino
Sauger		Blaquart	Brajot		Maigret

Les 4 javas que l'on peut lancer se situe respectivement dans src/controller(IHMManager.java);src/controller (IHMPointing.java) src/reseaux (Server.java) et src/utils (Serialisation). 
Il n'y a pas d'ordre précis de lancement, mise à part pour le server qui doit être lancé pour le transfert de données et Sérialisation.java si il n'y a pas les fichiers.ser. Nous avons utilisé la méthode MVC avec les views 
dans leur dossier et controller pour les modèles. 
Il est nécessaire d'indiquer comment fonctionne la modification des pointings. Lorsqu'on arrive sur la page Manage pointing (IHMManager SavedWorkTime), nous pouvons entrer un horaire sous un certain format (gestion d'erreur si ce n'est pas le cas) afin de modifier/détruire le Dernier pointage, ou la création d'un nouveau s'il n'en existe déjà pas deux dans la même journée.
Maintenant voici la liste des contributions de chacun:

Romain: - Front application pointing + charte graphique
	- Aide réseaux (gestion hors connection et délais de connections)
	- Style
	- Confirm, Except, Information Views
Arthur: Toute l'IHM Manager avec créations/modifications des employés ou du dernier pointing.
	- Page 1 à 6
	- Variable
Nino: 	- Création des StaffList,Departement
	- Aide dans Staff
	- Gestion des temps Workschedule et TimeManagement avec les extratimes
Clément:- Grande partie du back de Pointing.java 
	- Staff
	- Les différentes sérialisations
	- Création du Server et du Client pour les requêtes TCP/IP, avec récupération et renvoie des données
	  selon l'objet reçu.
Vous pouvez vous connectés entre 2 PC sur un même réseau UNIQUEMENT sur le port 1234.
Nous avons configuré les chemins pour pouvoir le lancer depuis le terminal ou InteliJ Idea.
Pour finir, le lien de la vidéo youtube est le suivant: https://youtu.be/rppoIfpDRwc
Cordialement,
Les Scribes de Broadway
