# Project Management System

Aplikacja pomagająca w zarządzaniu wydatkami na obsługę systemów. 

## Konfiguracja

### Baza danych

W pliku [src/main/resources/hibernate.properties](src/main/resources/hibernate.properties) znajduje się konfiguracja bazy danych.
```
jdbc.url=jdbc:postgresql://localhost:5432/postgres
jdbc.user=postgres
jdbc.pass=pass
```

### Import plików

Aplikacja pozwala importować pliki `.xslx`.
W pliku [src/main/resources/import.properties](src/main/resources/import.properties) znajduje się konfiguracja nazw kolumn importowanego pliku.
W importowanym pliku nagłówki kolumn muszą znajdować się w pierwszym wierszu. Kolejność wierszy nie ma znaczenia.

### Logowanie komunikatów aplikacji

W pliku [src/main/resources/log4j.properties](src/main/resources/log4j.properties) znajduje się konfiguracja logowania komunikatów.

## Budowanie aplikacji

Przejdź do folderu i wykonaj polecenie:

```
mvn clean package
```
Po udanej kompilacji i testowaniu wynikowy plik `.war` powinien się pojawić w folderze [target](target).


## Uruchomienie

Do poprawnego działania aplikacji wymagana jest uruchomiona baza danych PostgreSQL.

Aby zainicjalizawać bazę przykładowymi danymi wykonaj polecenie (uaktualnionym o prawidłowe dane):

```
psql -h localhost -p 5432 -U postgres < init.sql
```

Deplojować plik `.war` do tomcata.

## Uruchomienie za pomocą Docker-Compose

Aby uruchmić za pomocą docker-compose wykonaj (nie potrzeba konfigurować bazy danych):

```
docker-compose up
```



## Author

* **Artur Siembab** - [GitHub](https://github.com/ajtuss), [linkedIn](https://www.linkedin.com/in/artur-siembab/)

