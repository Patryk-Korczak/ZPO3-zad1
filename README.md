# ZPO3-zad1

1. (1,5 pkt) Zaimplementuj test ze znajomości słówek angielskich. Baza ma liczyć 10 pytań, z których do testu losujemy bez powtórzeń 5.  „Pytaniem” ma być słowo polskie, odpowiedzią – wpisywane z tzw. palca, ale w okienku dialogowym (z użyciem JavaFX lub Swing), słowo angielskie. Na końcu testu należy podać wynik (tj. ile pytań poprawnych) oraz zużyty czas, z dokładnością do 0,01s. 
 
Jednemu słowu polskiemu może odpowiadać kilka (dozwolonych) tłumaczeń, np. krzyczeć -> shout / cry / scream.  Program powinien być niewrażliwy na małe / wielkie litery (czyli cry / CRY / Cry etc. są równoważne). 
 
Co więcej, dopuszczamy 1 błąd Levenshteina (przypomnij sobie zadanie nr 1 z lab02), ale używamy standardowej (a nie ważonej, jak w tamtym zadaniu) miary Levenshteina.  Jeśli odpowiedź jest z jednym błędem, to dajemy za nią 0,5 pkt. Jeśli jest bezbłędna, to 1 pkt. 
 
Przykłady:   krzyczeć --> cry (1 pkt.)  krzyczeć --> SoUT (0,5 pkt) (gdyż jedna z prawidłowych odpowiedzi to „shout”; małe/duże litery nie mają znaczenia)  krzyczeć --> cri (0,5 pkt)  krzyczeć --> cray (0,5 pkt)  krzyczeć --> crie (0 pkt) (gdyż tu są już 2 błędy w sensie miary Levenshteina) 
 
„Baza” pytań i odpowiedzi, w formacie JSON, zawarta jest w pliku PolEngTest.json (należy go najpierw „ręcznie” utworzyć!  Postać niech będzie czytelna, więc nie jeden bardzo długi wiersz tekstu), który należy odczytać (zdeserializować) przy użyciu biblioteki google-gson: https://github.com/google/gson (lub innej użytecznej biblioteki). Dokumentacja Gson:  http://www.javadoc.io/doc/com.google.code.gson/gson/2.8.6  Format JSON jest przedstawiony np. pod  http://www.tutorialspoint.com/json/json_tutorial.pdf . 
 
Przebieg egzaminu (tj. zbiór par napisów: pytanie-odpowiedź) ma być również zapisany w pliku JSON o nazwie imie_nazwisko.json, w analogicznym formacie jak plik wejściowy. 
 
 Napisz testy JUnit (nie tylko dla sprawdzania odl. Levenshteina).
