# Exercise 2

Übung 02 besteht darin, unsere Web-App mit einer Datenbank zu verbinden. \
Außerdem bauen wir ein paar neue Endpunkte.

Wir starten zunächst mit einer In-Memory-Datenbank, die wir dann später gegen eine echte Datenbank austauschen werden. \
Das werden wir über Profile steuern.

## Datenbank-Setup

### In-Memory-Datenbank

Wir nehmen H2. \
Fügt dazu folgende Dependencies in der `build.gradle.kts` hinzu:


```gradle
dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("com.h2database:h2")
}
```

### PostgreSQL-Datenbank

Organisiert euch eine PostgreSQL-Datenbank. \
Wir machen das mit Docker, weil wir die Postgres dann einfach wieder wegschmeißen können.

```bash
docker pull postgres:17

docker run -d \
    --name postgres \
    -e POSTGRES_USER=postgres \
    -e POSTGRES_PASSWORD=1234 \
    -p 5432:5432 \
    postgres
```

Dazu brauchen wir die Dependency für PostgreSQL. Auch in der `build.gradle.kts`:

```gradle
dependencies {
    runtimeOnly("org.postgresql:postgresql")
}
```

## Aufgabenstellung

Erstellt nun eine Service-Klasse, die ein Movie-Repository verwendet. \
Wie das Movie Repository genau aussieht, müsst ihr selbst rausfinden.
```kotlin
@Service
class MovieService(
    private val movieRepository: MovieRepository
) {
    ...
}
```

In den Tests findet ihr einen `MovieServiceTest`, der euch zeigt, was die MovieService-Klasse können muss.

Stellt auch entsprechende Endpunkte in der `MovieController`-Klasse zur Verfügung, die die MovieService-Klasse verwenden. \
Beim Controller solltet ihr auch darauf achten, dass sinnvolle Informationen zurückgegeben werden. \
Z.B. der Fehler, wenn einer passiert (GET von unbekanntem Film) oder Informationen zu der erfolgten Aktion.

Es kann auch sinnvoll sein, die User-Eingaben zu validieren (z.B. kein Film mit leerem Titel), seht das aber eher als
Extra-Aufgabe.

## Links / Hilfestellung

- Spring mit H2 DB: https://www.baeldung.com/spring-boot-h2-database
- Anfragen mit vielen Parametern / Body werden einfacher mit Postman: https://www.postman.com
