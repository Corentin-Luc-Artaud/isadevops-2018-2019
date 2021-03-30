# Architecture

## I. Vue d'ensemble

### 1. CLI

Actuellement, nous avons trois CLI :
- Une CLI Admin, accessible aux personnes qui gèrent la cérémonie.
- une CLI Student, accessible aux étudiants.
- Une CLI Public, accessible à toutes les personnes présentes à la cérémonie.

Les VIP n'ont pas accès à leur propre CLI. Ils sont automatiquement considérés comme présent, et pour prévenir de leur absence ils envoient un email au responsable communication. D'après le sujet les VIP ont un agenda chargé, nous avons donc voulu leur faciliter la tâche en laissant le responsable communication s'en occuper (pour ne pas trop compliqué sa tâche, il est plus simple de signaler les personnes qui ne viennent pas que celles qui viennent).

### 2. Fonctionnalités non couvertes

Les fonctionnalités non couvertes par notre projet sont : 
- La gestion du planning :
    - La gestion des conflits entre les événements n'est pas gérée. Nous avons une vérification lors de l'ajout d'événements mais pas lors du décalage.
- Gestion des factures : 
    - Le composant qui gère les factures de la cérémonie n'est pas fait, mais cela ne devrait pas poser de problème pour l'ajouter dans notre architecture actuelle.
- L'envoi des invitations :
    - Les invitations devraient être envoyées par email. Mais pour l'instant, nous affichons des messages sur les CLI.
- Le service externe de la banque et celui de l'impression sont mockés : 
    - Nous avons fait le service hyperplanning en C#, cela ne devrait pas être compliqué de faire la même chose pour la banque et le service d'impression.
- Pas possible de supprimer un accompagnateur d'étudiant : 
    - Vu qu'on l'a fait pour les VIP et les étudiants, nous devrions pouvoir l'implémenter rapidement dans notre architecture.
    
### 3. Fonctionnalités couvertes

Les fonctionnalités couvertes par notre projet : 
- La gestion des invités : 
    - Importation automatique des diplômés  de l'année
    - Gestion (ajouter, modifier, supprimer) des invités (VIP, étudiants)
    - Obtenir le nombre d'étudiants présents par filière
- La gestion des cérémonies : 
    - Créer une cérémonie par an
    - Préciser la date et le lieu. 
    - Une date minimale de la cérémonie venant de la livraison des diplômes
    - Une deadline des inscriptions
- La gestion des intermèdes : 
    - Créer des offres
    - Postuler pour une offre
    - Accepter une candidature
- La gestion des invitations : 
    - Créer et envoyer les invitations aux VIP et étudiants (en console seulement)
- La gestion des inscriptions : 
    - Les étudiants peuvent confirmer leur présence
    - Les étudiants peuvent ajouter des accompagnateurs
    - Les étudiants peuvent payer s'ils ont plus de deux accompagnateurs
- La gestion du planning : 
    - Génère un planning par défaut avec les filières et les VIP
    - Gérer (ajouter, déplacer, supprimer) un événement dans le planning

### 4. MOM

Nous n'avons pas de MOM dans notre architecture actuelle. Cependant, nous avons prévu d'ajouter deux MOM : 
- Entre nos composants et le service externe banque, afin de traiter les demandes bancaires séquentiellement  pour réduire les risques d'erreur.
- Entre nos composants et le service externe d'impression, si jamais il y a un traitement asynchrone, comme par exemple une requête qui nécessite une validation humaine.

## II. Composants 

Le component Bills qui gère les factures n'est pas fait, mais nous l'avons prévu dans l'architecture.

Lorsque le responsable communication créé une nouvelle cérémonie depuis la CLI admin à l'aide de l'interface "CeremonyManager" : 
- Cela va créer la cérémonie, mais aussi faire une requête au composant "GuestManager" en passant par l'interface "PresentStudent"
- "GuestManager" va demander la liste des diplômés au service externe "Hyperplanning"
- "GuestManager" va demander la date de livraison des diplômes et va set la date minimale à cérémonie.

## III.  Points faibles de notre architecture

Le nom des composants ne respecte pas la convention de nommage. Cela devrait être une action avec un verbe à l'intérieur.


## IV. Points forts de notre architecture

Chaque fonctionnalité a son propre submodule (la gestion de l'enregistrement à la cérémonie est dans le submodule teami-register par exemple)
Nous n'avons pas d'objet Stateful, ils sont tous Stateless, tous nos utilisateurs vont donc se partager la même pool d'EJB stateless. Avec des EJB stateful nous aurions eu une instance par utilisateur et cela aurait potentiellement pu utiliser plus de mémoire.
Nous avons fait attention à ce que le nom de toutes nos classes et variables soient explicites.
Nous n'avons pas de godclass qui regroupe toutes les responsabilités, elles sont bien réparties dans chacune de nos classes.