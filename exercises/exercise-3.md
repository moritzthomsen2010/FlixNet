# Übung 03 - Caching, Metrics und Templating

### Caching und Metriken
Baut simples Caffeine Caching für die Filme ein, sodass wiederholte Anfragen an den gleichen Endpunkt schneller beantwortet werden können.
Stöbert ein bisschen in den Metriken über den Actuator Endpunkt `/actuator/metrics`, nachdem ihr diesen freigeschaltet habt.

    implementation("com.github.ben-manes.caffeine:caffeine")
    implementation("org.springframework.boot:spring-boot-starter-actuator")

Hinweis: Cash-Hits bekommt ihr über Metric Tags, z.B. so:
http://localhost:8080/actuator/metrics/cache.gets?tag=cache:movies&tag=result:hit


### Template-Engine
Im ersten Teil soll die Template-Engine Freemarker der Anwendung hinzugefügt werden.
Die grundlegende Funktionalität wird durch die dazugehörige *-starter-Dependency bereits hinzugefügt.

Nun soll es zwei Endpunkte geben, die ein HTML-Dokument zurückliefern, das mittels Freemarker-Template generiert wird.
Zum einen eine Schnittstelle, die alle Filme zurückliefert und zum anderen eine, die einen einzelnen Film zurückgibt (Parametrisiert mit der id).
Das Besondere soll hier sein, dass die Schnittstellen den gleichen Pfad haben soll, wie ihr jeweiliger REST-Endpunkt.
Beispiel:
GET /movies über dem Browser liefert die HTML-Seite mit allen Filmen.
GET /movies mit einem Content-Type application/json Header liefert dies als JSON, so wie es in der vorherigen Übung bereits implementiert ist.

Wie ihr das HTML-Dokument gestaltet ist euch überlassen, aber experimentiert gern ein bisschen mit der Freemarker-Syntax und CSS.

Zu guter Letzt soll ein 404-Template erzeugt werden, dass im Fehlerfall ausgespielt werden soll. Dies ist unter anderem der Fall, wenn Filme angefragt werden, die gar nicht existieren.

Zusammenfassung:

* Erweiterung um zwei Schnittstellen mit HTML-Ausgabe (Alle Filme anzeigen; Einen einzelnen Film anzeigen)
* HTML- und JSON Ausgabe teilen sich denselben Pfad und unterscheiden sich nur anhand des Content-Type
* 404-Template für Fehlerfälle

### Mögliche Erweiterungen für Schnelle
User-Input hinzufügen, z.B. eine Wishlist, das Ausleihen von Filmen, das Bewerten von Filmen und das Anzeigen der durchschnittlichen Bewertung eines Films.
Hinzufügen von Filmen über ein Formular (Input Validierung berücksichtigen, z.B. mit javax.validation. Wie zeige ich dem User dann Errors an?).