# IFT3150

## Mon projet d'informatique en théorie des graphes

Mon superviseur est Gena Hahn (<hahn@iro.umontreal.ca>).

Alors que j'avais prévu faire un projet de recherche sur le problème de Hunter & Rabbit, je vais finalement implémenter une application pour visualiser un graphe et y jouer au problème de Cops & Robbers. Une modification pourrait alors implémenter le problème du chasseur initial.

---

Une fois clonné, il s'assurer d'avoir configuré un accès à la librairie JavaFX. 
Il y a aussi besoin du module javafx.controls : sous IntelliJ, il faut l'ajouter avec ```--module-path "C:\path\to\javafx\lib" --add-modules javafx.controls,javafx.media``` dans les VM-options

---

# 23 février

J'ai un graphe en background. Je ne suis pas sûre de comment le garder en mémoire : matrice d'adjacence ? liste d'edges ?

# 28 février

J'arrive à dessiner un graphe

# 4 mars

Je peux positionner des personnages, mais je ne sais pas encore comment les manipuler.
J'ai créé plusieurs scènes pour :
1. Dessiner et modifier le graphe
2. Initialiser les personnages
3. Jouer

La dernière scène n'est pas au point, c'est ce sur quoi je dois encore travailler.

# 26 mars

J'arrive à déplacer mes personnages ! Ma scène Jouer est fonctionnelle, mais il faut que je trouve une façon d'alterner les tours des cops et du robber.

Il faudrait que je trouve un moyen de toujours mettre un unique cop.

Ma logique/syntaxe est plus proche des conventions.

# 20 avril

Dévut de la rédaction du rapport
