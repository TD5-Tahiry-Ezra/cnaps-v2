# TD5 (Cnaps) - Guide d'utilisation

Ce guide explique comment utiliser l'application de l'examen TD5 (Cnaps). Suivez les étapes ci-dessous pour configurer, tester et utiliser l'application.

## Fonctionnement

1. **Clonage/Fork du Projet**  
   Clonez ou forkez le projet depuis ce repository.

2. **Configuration de `application.properties`**  
   Dans le fichier `application.properties`, configurez les bases de données PostgreSQL :
```
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=false


spring.datasource.jdbcurl=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

spring.flyway.locations= #flyway_location check FlywaySlaveInitializer

spring.second-datasource.jdbcurl=${DB_URL_CNAPS}
spring.second-datasource.username=${DB_USERNAME_CNAPS}
spring.second-datasource.password=${DB_PASSWORD_CNAPS}


server.port=8005
spring.flyway.debug=true
```

3. **Lancer les Tests**  
   Lancez les tests pour vérifier l'intégrité de l'application.

4. **Lancer l'Application**  
   Exécutez la classe `Prog4Application` pour lancer l'application.

5. **Ajout d'un Utilisateur via Psql**  
   Ajoutez un utilisateur à la base de données PostgreSQL via Psql.

6. **Authentification avec l'Utilisateur Ajouté**  
   Utilisez les informations de l'utilisateur ajouté pour vous authentifier.

## Fonctionnalités

- **Voir la Liste des Employés**  
  Accédez à la liste des employés en utilisant l'URL `/employee/list`.

- **Ajouter ou Modifier un Employé**  
  Utilisez l'URL `/createOrUpdate` pour ajouter un nouvel employé ou en modifier un existant.

- **Filtrer la Liste des Employés**  
  Appliquez des filtres à la liste des employés en utilisant l'URL `/employeeFilters`.

- **Réinitialiser les Filtres**  
  Effacez les filtres appliqués en accédant à l'URL `/list/filters/clear`.

## Détails Additionnels

Une fois sur la page, vous pouvez accéder aux détails de chaque employé. Pour cela, suivez les instructions fournies dans l'application en fonction de votre utilisation.

## Conclusion

Ce fichier README fournit un aperçu général de l'utilisation de l'application TD5 (Cnaps).
