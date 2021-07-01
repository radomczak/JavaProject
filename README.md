1. Podstawowe założenia
  a) Projekt maven’owy z obługą minimalnej liczby zależności zewnętrznych
  b) Paczka wynikowa: jar.
  c) Zgodność źródeł oraz klas wynikowych z JAVA 11.
  d) Aplikacja wielowątkowa z obsługą puli wątków
  e) Obsługa parametrów w wydzielonej klasie: liczba wątków w puli, ścieżka do katalogu źródłowego, ścieżka do katalogu docelowego
  f) Kod obłożony testami jednostkowymi dostarczonymi w projekcie
  g) Kod ma zawierać komentarze do klas, metod i zmiennych składowych
  h) Do projektu należy dołączyć wygenerowany javadoc

2. Opis merytoryczny zadania
Program do wielowątkowego kopiowania zawartości wskazanego katalogu, do katalogu
docelowego zachowując strukturę wewnętrzną katalogów. Każdy plik kopiowany ma być w
oddzielnym wątku należącym do puli. W danym momencie nie może być aktywnych więcej
wątków niż wskazuje na to parametr definiujący maksymalny rozmiar puli. 

Osoby odpowiedzialne za projekt:
Radosław Popielarski
Jacek Trębicki
