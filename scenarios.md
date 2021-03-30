# Comment utiliser PolyDiploma

## Avant de commencer
Il faut avoir lancé build.sh, run.sh et client.sh pour avoir les 3 CLI et l'application


## 1. Initialisation par le responsable communication
Depuis la CLI Admin, créez la cérémonie (on imagine que c'est la cérémonie de la promotion 2020):
```
new_rdd 2020
```
Vous devez ensuite entrer la date de la cérémonie (nous allons prendre pour date le 20 juillet 2020 à 14:00)
```
set_date 20-07-2020:14:00
```

Puis le nom de la salle où elle se déroulera (on imagine que le nom de la salle est Mozzarella)
```
set_place Mozzarella
```
La cérémonie est maintenant initialisée

## 2. Notification des invités par le responsable communication
Depuis la CLI Admin, ajoutez un VIP
```
addvip Jean Bon jean.bon@gmail.com SI
```
Toujours depuis la CLI Admin, notifiez les invités
```
notifyGuests
```

## 3. Enregistrement d'un étudiant
Nous allons maintenant enregister un étudiant et inviter des accompagnateurs
Depuis la CLI Student, l'étudiant s'enregistre avec son numéro étudiant
```
register 21
```

Il peut ensuite inviter des accompagnateurs
```
addattendant 21 John Dupont
addattendant 21 Joe Smith
```

Il peut vérifier qu'il n'a rien à payer car il a seulement 2 invités
```
getprice 21
```

Il peut ensuite inviter une autre personne
```
addattendant 21 Willy Wonka
```

Et il peut maintenant voir qu'il doit payer pour son invité
```
getprice 21
```

L'étudiant peut payer avec la commande en fournissant ses coordonnées bancaires
```
payforattendant 21 46580654
```

Il n'a maintenant plus rien à payer
```
getprice 21
```

## 4. Création d'une offre d'intermèdes
Depuis la CLI Admin le responsable communication peut créer une offre d'intermède en précisant l'heure à laquelle l'intermède devrait commencer et sa durée
```
createoffer 20-07-2020:16:00 30
```

Depuis la CLI Public, un club peut postuler à cette offre, mais il doit d'abord s'enregistrer dans la base de données en précisant le nom du club et le nombre de membres
```
registerclub BDJ 20
```

Le club peut maintenant voir les offres disponibles
```
getoffers
```
Cette commande retourne la liste des offres avec leur id

