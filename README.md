# ToDoGood

## Prérequis

Node, Java8, Tomcat8 & Mysql


## Installation / run du projet :

```
npm install --global vue-cli
```

Lancer le front :
```
cd frontend
npm run dev
```

Lancer le back :
```
mvn clean install
mvn --projects backend spring-boot:run
```

URL : 
```
http://localhost:8080/#/
```

## Architechture du projet

```
SpringVue
├─┬ backend     → backend Java
│ ├── src
│ └── pom.xml
├─┬ frontend    → frontend Vue.js
│ └── pom.xml
└── pom.xml     → Maven parent pom 
└── todo.sql    → Export db
```

## Screens

2 images et une vidéo dans le dossier /screens

![alt text](https://github.com/JacquesCeChirac/screens/ToDoGood/todogood.png)
![alt text](https://github.com/JacquesCeChirac/screens/ToDoGood/todogood-modification.png)