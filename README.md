# Introduction à Spring MVC

## 0. Récupérer le code source du TP

### Cloner le dépot git

> Terminal

Dans le *workspace* :

```bash
git clone https://github.com/romain-warnan/formation-spring-mvc.git
```

### Importer le projet dans Eclipse

> Eclipse

* File
* Import…
* Existing Maven Project
* Root directory : D:\idep\Mes Documents\eclipse_workspace\formation-spring-mvc
* Finish

## 1. Mise en place

```bash
git checkout -b tp1 origin/tp1
```

### Configurer le chemin d’accès aux pages

> application.properties

```properties
spring.mvc.view.prefix=/WEB-INF/views/
spring.mvc.view.suffix=.jsp
```

### Créer le package contenant les contrôleurs

> fr.insee.springmvc.controller

### Créer le contrôleur

> AccueilController.java

Ajouter l’annotation `@Controller`.
Créer une méthode qui retourne la vue `accueil` quand on accède à l’URL `/accueil`.
Cette méthode ajoute au modèle un objet `message` de type qui vaut `"Hello world"`.

### Créer une JSP qui affiche le message

> accueil.jsp

Éditer la JSP `accueil.jsp` dans le répertoire `/WEB-INF/views/` et afficher l’objet `message`.

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h1><c:out value="${message}" /></h1>
```

Pour tester, exécuter la méthode `main` de la classe `SpringMvcApplication` puis se rendre à l’adresse `http://localhost:8080/accueil` dans un navigateur.

### Utiliser un fichier de propriétés

> src/main/resources/application.properties

```properties
welcome.message=Spring MVC
```

> AccueilController.java

Ajouter un attribute de type `String` dans le contrôleur et l’annoter avec `@Value("${welcome.message}")` pour récupérer la valeur de la clé `welcome.message`.
Paramétrer le message avec cet attribut.

Tester.

### Afficher la page d’accueil lorsque l’url racine est appelée

> AccueilController.java

Faire en sorte que l’URL racine `/` retourne également la page d’accueil.

Tester en avec l’URL `http://localhost:8080` dans le navigateur.





















## 2. Navigation

> Terminal

```bash
git checkout -b tp2 tp2
```

### 2.1. Liste de tous les clients

#### 2.1.1. Créer un contrôleur qui permet d’afficher la liste de tous les clients

> ClientsController.java

Ce contrôleur possède une méthode qui est appelée à l’URL « /clients ».
Il récupère la liste de tous les clients dans la base de donnée et l’ajoute au modèle.

```java
	@Autowired
	private ClientDao clientDao;
	…
	List<Client> clients = clientDao.findAll();
```

Il lance la génération de la vue `/jsp/clients.jsp`.

#### 2.1.2. Afficher la liste des clients

> clients.jsp

En itérant sur la liste des clients avec le tag `<c:forEach>`, afficher la liste de tous les clients (nom et email) dans un tableau :

> Rappel : structure d’un tableau HTML

```html
	<table>
		<tr> <!-- Ligne entête -->
			<th>Entête 1</th>
			<th>Entête 2</th>
			<th>Entête 3</th>
		</tr>
		<tr> <!-- Ligne 1 -->
			<td>Cellule 1.1</td>
			<td>Cellule 1.2</td>
			<td>Cellule 1.3</td>
		</tr>
		<tr> <!-- Ligne 2 -->
			<td>Cellule 2.1</td>
			<td>Cellule 2.2</td>
			<td>Cellule 2.3</td>
		</tr>
	</table>
```

#### 2.1.3. Ajouter un lien vers la page d’accueil

> clients.jsp

Grace au tag `<c:url>` créer une variable qui pointe vers la page d’accueil.
Utiliser cette variable dans un lien qui redirige vers la page d’accueil.

#### 2.1.4. Sur la page d’accueil, ajouter un lien vers la page de la liste des clients

> accueil.jsp

### 2.2. Détails pour un client donné

#### 2.2.1. Créer un contrôleur qui permet d’afficher les informations concernant un client donné

> ClientController.java

Ce contrôleur possède une méthode qui est appelée à l’URL « /client/{id} ».
À l’aide de l’annotation `@PathVariable`, récupérer la valeur de l’identifiant passé dans l’URL.
Dans la base, récupérer le client associé à cet identifiant.
Ajouter le client au modèle.
Diriger vers la page `/jsp/client.jsp`.

#### 2.2.2. Créer la page client.jsp

> client.jsp

Y afficher les informations relatives au client : identifiant, nom, email et date de naissance.
Pour formater la date, utiliser le tag `<fmt:formatDate>` et le format `dd/MMMM/yyyy`.
Ajouter un lien vers la page d’accueil.

#### 2.2.3. Faire le lien entre la page clients et les sous-pages client

> clients.jsp

Autour de chaque nom de client, ajouter un lien qui pointe vers l’URL `/client/{id}`.
De cette manière, l’utilisateur peut cliquer sur le nom d’un client pour en voir le détail.

### 2.3. Utilisation d’un convertisseur

#### 2.3.1. Créer le nouveau convertisseur

> ClientConverter.java

Dans  le package `fr.insee.bar.converter`, créer une classe `ClientConverter` qui implémente de l’interface `Converter<String, Client>`.
Ne pas oublier le stéréotype `@Component` sur la classe. 
Implémenter la méthode `convert` avec un appel à `clientDao.find(id)`.

#### 2.3.2. Simplifier le contrôleur

> ClientController.java

Modifier la signature de la méthode pour remplacer le `Short` par un `Client`.
Supprimer le DAO du contrôleur.

#### 2.3.3. Enregistrer le convertissuer

> dispatcher-servlet.xml

Déclarer ce nouveau convertisseur auprès de la servlet de Spring MVC :

```xml
<mvc:annotation-driven conversion-service="conversionService" />
<bean id="conversionService" class="org.springframework.format.support.FormattingConversionServiceFactoryBean">
	<property name="converters">
		<set>
			<bean class="fr.insee.bar.converter.ClientConverter" />
		</set>
	</property>
</bean>
 ```
 
 Tester que l’application fonctionne toujours.
 
 ## 3. Intercepteurs
 
 ### 3.1. 