# HyperPlanning mock 

Rest web service writen in C# over mono to simulate the behaviour of a web service that provide all graduated student given a graduation year 

This service provide only one route `/getgraduated/{year}` which provide the list of graduated students 
```json
{
    "email":"Brice.Mathieu@etu.unice.fr",
    "firstName":"Brice",
    "graduatingYear":2018,
    "lastName":"Mathieu",
    "speciality":"BAT"
}
```

## build
In order to build this webService you need to have mono installed  
Then use this command `mcs src/*.cs -pkg:wcf -out:service.exe`  


## usage
`./service.exe` will start the service on 'http://localhost:9090'  
The option `--url` should be followed by the listening url.  

## Data Sources
The data in resources/firstNames.txt cames from [wikipedia](https://fr.wikipedia.org/wiki/Liste_de_pr%C3%A9noms_fran%C3%A7ais_et_de_la_francophonie)  
and ressources/lastNames.txt cames from [wikipedia](https://fr.wikipedia.org/wiki/Liste_des_noms_de_famille_les_plus_courants_en_France)