Libreria únicmante con com.nihek.commons.models.UserModel y com.nihek.repositories.UserRepository para no tener que duplicar código entre
microservicios. Para utilizarlo tengo que primerarmente hacer un maven clean - install (se instala en el repo 
de maven) y añadirlo como una dependencia en los otros pom.xml que necesitan usarlo como una dependencia normal :

<dependency>
<groupId>com.nihek</groupId>
<artifactId>common-lib</artifactId>
<version>1.0.0</version>
</dependency>