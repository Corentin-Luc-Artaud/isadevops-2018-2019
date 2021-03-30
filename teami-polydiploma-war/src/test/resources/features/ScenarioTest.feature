Feature: Ordering Cookies

  This feature support the way a Customer can order cookies through the TCF system

  Background:
    Given le responsable communication cree une cérémonie depuis la cli admin pour l'année 2020
    And le responsable communication choisit la salle Soleil_levant pour le lieu de la cérémonie
    And le responsable communication choisit la date 12/05/2020 et l'heure 15h20 pour la cérémonie

    And le responsable communication ajoute le VIP Alex Def de la formation SI et d'email aldef@gmail.com
    And le responsable communication notifie les invités : étudiants et VIP
    And le VIP Alex Def prévient de son absence

  Scenario: 3 accompagnateurs, l'etudiant paye
    And un etudiant d'identifiant 2 accepte son invitation sur la cli student
    And un etudiant d'identifiant 2 ajoute un accompagnateur de nom Bon et de prenom Jean sur la cli student
    And un etudiant d'identifiant 2 ajoute un accompagnateur de nom Nes et de prenom Franc sur la cli student
    And un etudiant d'identifiant 2 ajoute un accompagnateur de nom Art et de prenom Coco sur la cli student
    And un etudiant d'identifiant 2 paye pour ses accompagnateurs avec son RIB 1234567890

#    And le planning est genere
#
#    And le responsable communication crée un appel d'offre pour l'interlude a 16h45 pendant 20 minutes
#    And l'association BDE repond a l'appel d'offre, avec 20 membres presents
#    And le responsable communication accepte la candidature BDE pour l'interlude


    #And le professeur récupère la liste de ca filière SI
    #And une personne veut savoir où et quand a lieu la ceremonie
    Then la salle de la ceremonie s'appelle Soleil_levant
    And la ceremonie a lieu le 12/05/2020 a 15h20
    And l'etudiant 2 a bien paye

  Scenario: 3 accompagnateurs, l'etudiant ne paye pas
    And un etudiant d'identifiant 9 accepte son invitation sur la cli student
    And un etudiant d'identifiant 9 ajoute un accompagnateur de nom Bon et de prenom Jean sur la cli student
    And un etudiant d'identifiant 9 ajoute un accompagnateur de nom Nes et de prenom Franc sur la cli student
    And un etudiant d'identifiant 9 ajoute un accompagnateur de nom Art et de prenom Coco sur la cli student

    Then la salle de la ceremonie s'appelle Soleil_levant
    And la ceremonie a lieu le 12/05/2020 a 15h20
    And l'etudiant 9 n'a pas paye

    #And le responsable communication récupère le nombre de personne présente pour informer le centre des congrés