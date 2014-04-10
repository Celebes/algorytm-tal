Techniki Algorytmiczne - projekt
============

Projekt z przedmiotu TAL (Techniki Algorytmiczne) - "Feedback Vertex Set"

Problem
============

Należy znaleźć minimalny zbiór wierzchołków rozcyklujących graf, tzn. taki zbiór wierzchołków, po których usunięciu graf przestanie być cykliczny. Jest to problem NP-trudny.

Problem ten nazywany jest po ang. "Feedback Vertex Set", co w języku polskim tłumaczy się jako "Rozcyklający zbiór wierzchołków".

Algorytm aproksymacyjny
============

Algorytm warstwowy (oparty na warstwach)

Opis działania i narzędzia
============

* Do tworzenia testowych grafów został użyty program z edytorem graficznym, **yEd** (http://www.yworks.com/en/products_yed_about.html).
* Utworzone w **yEd** grafy zostają zapisane do formatu **.tgf** (http://en.wikipedia.org/wiki/Trivial_Graph_Format)
* Utworzony program przyjmuje na wejście wygenerowane pliki **.tgf**
* Na podstawie sparsowanego pliku tworzony jest w programie graf
* Na grafie wykonuje sie algorytm
* Wynikowy graf jest zapisywany rowniez jako **.tgf**, aby mozna go bylo odpalic w **yEd** i sprawdzic rezultaty
