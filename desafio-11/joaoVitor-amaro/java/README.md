# Desafio 11 - Primos em Pi
## Solução
Foi utilizado o Crivo de Eratóstenes para pré-computar todos os números primos
entre 2 e 9973. Os dígitos de π são percorridos com uma janela deslizante
que tenta encaixar subsequências de 1 a 4 dígitos formando números primos consecutivos,
guardando a sequência que cobrir o maior número de dígitos.
## Executar
```bash
javac PrimosPI.java
java PrimosPI p1-1000.txt
```
Foi utilizado o Java 21.
 
