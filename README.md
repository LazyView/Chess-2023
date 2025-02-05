# Java Chess Game

Implementace šachové hry v Javě je snad můj první projekt, podle toho i vypadá sám kód, ale každý někde začínal.

## Funkce
- Plně funkční šachová hra pro dva hráče
- Drag-and-drop pohyb figurek
- Animace pohybu figurek
- Validace tahů pro všechny typy figurek
- Detekce šachu a matu
- Speciální tahy: rošáda, braní mimochodem (en passant)
- Historie tahů s možností vrácení
- Export pozice do PNG
- Graf časů tahů
- Možnost hrát za černého hráče

## Implementace

### Hlavní komponenty
- `Chess_SP_2023`: Správa herní logiky a stavu šachovnice
- `Sachovnice_a_sachy`: Vykreslování šachovnice a GUI implementace
- `PiecesMovement`: Validace a generování možných tahů
- `Graf`: Vizualizace časů tahů
- Třídy pro jednotlivé figurky: `Pawn`, `Rook`, `Bishop`, `Knight`, `King`, `Queen`

### Reprezentace šachovnice
Šachovnice je implementována jako 2D pole Stringů, kde:
- Malá písmena reprezentují černé figurky
- Velká písmena reprezentují bílé figurky
- `P/p`: Pěšec, `R/r`: Věž, `B/b`: Střelec, `K/k`: Kůň, `Q/q`: Královna, `A/a`: Král

### Poznámky k implementaci
- Detekce šachových situací funguje spolehlivě pro bílého krále
- Implementace braní mimochodem vyžaduje další optimalizaci
- Animace pohybu figurek je plně funkční, ale výpočetně náročnější

## Budoucí vylepšení
- Vylepšení detekce šachových situací pro černého krále
- Optimalizace pravidel pro braní mimochodem
- Vylepšení stability při detekci matu
