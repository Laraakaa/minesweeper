# Minesweeper

## Vorgehensweise zur Klassenfindung `[1.1 & 1.4]`

### Klassen entwerfen

Zu Beginn der Programmplanung haben wir uns, nach der Berücksichtigung der Minimalanforderungen für das Projekt MineSweeper nach `AB226A-09`, unterschiedliche Substantive überlegt welche wir möglicherweise als Klasse einsetzen könnten:

* Spielfeld
* Benutzeroberfläche
* BenutzerEingabe
* Zelle
* Bombe
* Nachbarzellen

### Attribute von "Klassen" trennen

Nun geht es darum aus unseren Substantiven Klassen zu entwickeln, wobei nicht alle Substantive zu Klassen, sondern einige auch zu Attributen werden.  
Nach weiteren Überlegungen sind wir zudem zum Schluss gekommen folgendes Single-Responsibility-Prinzip für unsere Klassen anzuwenden:

###### Spielfeld

* Ein Spielfeld entspricht einer Spiel-Instanz
* Ein Spielfeld hat mehrere Zellen
* Ein Spielfeld kann mit Operationen wie Feld(er) aufdecken umgehen

###### Benutzeroberfläche

* Die Benutzeroberfläche kann Eingaben einlesen und Ausgaben ausgeben
* Die Benutzeroberfläche

###### BenutzerEingabe

* Die Benutzereingabe parset und hilft beim Verarbeiten eine/r Benutzereingabe

###### InputProcessor

* Der InputProcessor verarbeitet Eingaben
* Der InputProcessor entscheidet was mit Benutzereingaben geschehen soll

###### Zelle

* Eine Zelle definiert ein Feld auf dem Spielfeld
* Eine Zelle kann vom Typ Bombe sein
* Eine Zelle kann sich aufdecken
* Eine Zelle kann die umliegenden Nachbarzellen über das Aufdecken informieren

## Klassendefinitionen `[1.3 & 1.4 & 1.5]`

### Main

Die Main-Klasse enthält die Main-Methode. In dieser wird ein neuer GameLoop erstellt. Danach wird an diesen mit der Methode .run() delegiert, dass er den GameLoop betreten soll.

### GameLoop

Die GameLoop-Klasse enthält die .run() Methode und eine Instanz der UserInterface Klasse. Diese Instanz wird im Konstruktor initialisiert. Die .run() Methode gibt erst eine generelle Spielanleitung aus und springt anschliessend in eine Schlaufe. Bestandteil dieser Schlaufe ist:

* Dem userInterface Objekt delegieren, dass es das Spielfeld anzeigen soll (.display())
* Dem userInterface Objekt delegieren, die nächste Benutzereingabe einzulesen (.read())

### UserInterface

Die UserInterface-Klasse repräsentiert eine Schnittstelle mit dem Nutzer. Diese Schnittstelle könnte theoretisch später gekapselt werden (z.B. um ein UI zu implementieren).
Ein UserInterface enthält folgende Klassenvariablen:

* scanner vom Typ java.util.Scanner: Dient zum Einlesen der Benutzereingabe (STDIN)
* inputProcessor vom Typ InputProcessor: Dient zum Verarbeiten des Inputs, kennt das Spielfeld.
* spielfeld vom Typ Spielfeld: Verwaltet die Spiellogik
* isCancelled vom Typ boolean: Definiert ob das Spiel beendet werden soll (z.B. manueller Abbruch, gewonnen oder verloren).

Im Konstruktor wird:

* Der InputProcessor initialisiert. Als Parameter wird das Spielfeld mitgegeben.
* Der Scanner initialisiert. Als Parameter erhält dieser System.in (STDIN).

Folgende Klassenmethoden existieren:

* read(): Liest (falls vorhanden) die nächste Benutzereingabe aus (pro Zeile). Danach wird diese mit der statischen Methode BenutzerEingabe.parse geparsed. Zurückgegeben wird eine Benutzereingabe. Wenn der Typ `EingabeTyp.EXIT` ist wird die Applikation beendet. Ansonsten wird die BenutzerEingabe zum InputProcessor delegiert.
* revealAll(): Delegiert zu der Methode spielfeld.revealAll()
* isCancelled(): Getter zur Klassenvariable isCancelled
* display(): Delegiert zu der Methode spielfeld.display()

### InputProcessor

Die InputProcessor-Klasse entscheidet, was mit einer Gewissen BenutzerEingabe geschehen soll. Weiter delegiert sie die Entsprechende Methode an das Spielfeld, respektive wirft bei einer falschen Eingabe einen EingabeFehler. Der InputProcessor muss das Spielfeld kannen, um die entsprechenden Aufgaben darauf delegieren zu können. Ein InputProcessor enthält folgende Klassenvariablen:

* spielfeld vom Typ Spielfeld: Verwaltet die Spiellogik

Im Konstruktor wird:

* Die mitgegebene Variabel vom Typ Spielfeld in die lokale Klassenvariable gespeichert.

Folgende Klassenmethoden existieren:

* processInput(BenutzerEingabe): Nimmt eine BenutzerEingabe entgegen und entscheidet basierend auf dem Typ was weiter geschehen soll. Beim Typ `T` wird die Methode `spielfeld.test(x, y)` aufgerufen, beim Typ `M` die Methode `spielfeld.mark(x, y)`. Die Methode gibt true zurück, wenn das Spiel beenden soll. Ansonsten false.

### Spielfeld

Das Spielfeld verwaltet die Spiellogik. Darunter fällt das:

* Erstellen und verwalten von Zellen
* Bestimmen der Positionen der Bomben
* Auslesen einer Zelle an Koordinaten
* Bestimmen ob das Spiel gewonnen ist
* relative Auslesen einer Zelle zu einer anderen
* Auslesen aller Zellen, die sich um eine andere Zelle herum befinden
* Testen(aufdecken) einer Zelle bei einer Koordinate.
* Markieren einer Zelle bei einer Koordinate.
* Formatierte anzeigen von sich selbst (Spielfeld ausgeben)
* Aufdecken aller Zellen (beim Spielende benutzt)

### Zelle

Die Zelle definiert ein Feld auf dem Spielfeld. Die Zelle kennt immer ihr übergeordnetes Spielfeld (assignedMatchfield). Weiter kennt die Zelle ihre X und Y-Koordinaten, weiss ob sie eine Bombe/markiert/aufgedeckt ist. Über das übergeordnete Spielfeld kann sie ihre umliegenden Zellen bestimmen und diese im Falle das sie aufgedeckt werden sollte benachrichtigen.

## Beziehungen zwischen Klassen `[1.2]`

GameLoop:

* Ein GameLoop hat ein UserInterface

UserInterface:

* Ein UserInterface enthält einen InputProcessor
* Ein UserInterface enthält ein Spielfeld

InputProcessor:

* Ein InputProcessor enthält ein Spielfeld (Dies verweist auf die selbe Instanz wie oben)

Spielfeld:

* Ein Spielfeld enthält mehrere (= ein zweidimensionales Array) Zellen

Zelle:

* Eine Zelle enthält ein Spielfeld (sie ist diesem Zugeteilt)

## Referenzierte Kompetenzen `[1.1 - 1.5]`

1. `[1.1]` Ich kann mögliche Klassen, sowie deren Attribute und Operationen, finden, indem ich die Anforderungen nach Substantiven und Verben durchsuche.

2. `[1.2]` Ich kann Beziehungen zwischen Klassen finden, indem ich Anforderungen nach „hat ein/mehrere“ und „kennt ein/mehrere“ Formulierungen absuche.

3. `[1.3]` Ich kann ein klassenbasiertes Software -Design mit einigen Klassen entwerfen. (z.B. Klassendiagramm Minessweeper)

4. `[1.4]` Ich kann meine Klassen so entwerfen, dass die darin enthaltenen Attribute und Operationen genau eine, wohldefinierte Aufgabe erfüllen (single responsibility principle).

5. `[1.5]` Ich kann meine Klassen so entwerfen, dass Aufgaben, welche nicht lokal gelöst werden können, durch Methodenaufrufe an Objekte anderer Klassen delegiert werden.

Auf die Kompetenzen `[3.4]` sowie `[3.5]` wurde in dieser Dokumentation nicht explizit hingewiesen, da diese durch das `.jar` File und den Sourcecode unseres Programmes abgedeckt sind.

## Index

* Gruppe_LarsBärtschi_YvoHofer/Sourcecode  
  Sämtliche Dateien unseres Java Projekts

* Gruppe_LarsBärtschi_YvoHofer/Programmdokumentation  
  Diese Dokumentation  
  Das UML Klassendiagramm

* Gruppe_LarsBärtschi_YvoHofer/Programm als jar  
  Unser Programm zum Ausführen des MineSweeper Programmes in der Konsole

* Gruppe_LarsBärtschi_YvoHofer/JavaDoc  
  Unser automatisch generiertes JavaDoc (zu welchem wir die Kompetenz bereits abgegeben haben)

© by Yvo Hofer & Lars Bärtschi  
Powered by our [github-repository](https://github.com/LarsBaertschi/minesweeper) ![github](https://assets-cdn.github.com/favicon.ico)
