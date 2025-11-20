# Spring Boot Tutorium 2025

## Exercise 1 // Einstieg & Controller

Als Beispiel-Projekt zum Kennenlernen von Spring Boot wollen wir eine kleine digitale Videotheks-Applikation bauen (Netflix für Boomer).

In dieser ersten Übung geht es darum, das Spring Boot Projekt mit Kotlin aufzusetzen und den ersten Spring Boot Starter auszuprobieren.

### Aufgabenstellung

Erzeugt ein erstes Spring Boot Projekt und fügt als weitere Dependency "spring-boot-starter-web" hinzu.
Dies sorgt dafür, dass wir eine Web-Anwendung erzeugen, die gestartet bleibt, bis die Anwendung explizit gestoppt wird.

Benutzt beim Spring Initializer die Kotlin und die Java Version 21 (aktuelle LTS) sowie die bereits vorausgewählte Spring Boot Version.
Damit das Projekt starten kann, muss auf eurem System ein JDK installiert sein (z.B. OpenJDK oder AdoptOpenJDK).

Wird die Spring Boot Anwendung gestartet, kann im Browser über `localhost:8080` darauf zugegriffen werden.
Die digitale Videothek wird zum Start über keine externe Datenanbindung verfügen, weswegen die Änderungen nicht dauerhaft gespeichert werden.

Zum Start wollen wir HTTP-Schnittstellen hinzufügen, mit denen wir spezifische Filme anzeigen (GET), hinzufügen (POST), ändern (PUT), löschen (DELETE) und alle Filme auflisten (GET) wollen.
Schaut mal, wie der Web-Server die Daten zurückgibt, wenn ihr die URL `localhost:8080/<eure-get-url>` aufruft. (Hübsch machen kommt in einer späteren Session)

Ein Film hat dabei mindestens folgende Datenstruktur:

* Eindeutige ID
* Titel
* Filmlänge in Minuten
* Genre
* Verleihstatus

Benutzt sinnvolle Annotations, um eure Klassen von Spring autowiren zu lassen.
Überlegt euch dabei eine geeignete Package-Struktur und passende Datentypen, um die Filme (in Memory) zu speichern.
Nutzt dabei die Vorteile von Kotlin, z.B. data classes.

Beispiel für eine Get-Schnittstelle zum Abrufen eines Films anhand der ID:

```kotlin
@Controller
@RequestMapping("/movies")
class MovieController(
    private val movieService: MovieService
) {
    @GetMapping("/{id}")
    fun getMovie(
        @PathVariable("id") id: String
    ): ResponseEntity<Movie> {
        return ok(movieService.findById(id))
    }
}
```

### Links / Hilfestellung

Zum Testen der Schnittstellen könnt ihr zum Beispiel in IntelliJ unter Tools -> HTTP Client Requests anlegen und die Endpunkte aufrufen.

Spring Initializr: https://start.spring.io/

https://spring.io/guides/gs/rest-service/ (basic)

https://spring.io/guides/tutorials/rest/ (extended)


