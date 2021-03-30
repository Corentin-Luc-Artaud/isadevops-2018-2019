# Projet Polydiploma

## Team i
- Corentin ARTAUD
- François NESPOULOUS
- Kienan BACHWA 
- Alexis DEFRANOUX	

## Documents

- **[architecture.pdf](https://github.com/pns-isa-devops-1819/projet-isadevops-19-isa-devops-19-i/blob/master/architecture.pdf)** : rapport d'architecture logicielle de début de projet.
- **[composants.pdf](https://github.com/pns-isa-devops-1819/projet-isadevops-19-isa-devops-19-i/blob/master/components.pdf)** : le diagramme de composants du projet actuel.
- **[archi.md](https://github.com/pns-isa-devops-1819/projet-isadevops-19-isa-devops-19-i/blob/master/archi.md)** : les explications de l'architecture du projet actuel.
- **[scenarios.md](https://github.com/pns-isa-devops-1819/projet-isadevops-19-isa-devops-19-i/blob/master/scenarios.md)** : les scénarios actuellement pris en charge par le projet.

## Scripts

- build.sh : build tout le projet et crée les dockers associés.
- run.sh : run le projet (database, dépendance, server).
- student.sh : lance la CLI student.
- public.sh : lance la CLI public.
- stop.sh : stop le service.
- jenkins.sh : lance un container jenkins et les plans de build. Ou depuis notre serveur [jenkins](http://vps663090.ovh.net:8080/) avec l'identifiant `prof` et en mot de passe `1705069397`