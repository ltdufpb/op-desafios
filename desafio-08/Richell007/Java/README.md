# Desafio 08 - Fracoes Simples
 
## Descricao
 
Programa que le uma lista de fracoes de um arquivo texto e imprime
a versao simplificada de cada uma.
 
## Linguagem
 
Java (OpenJDK 17)
 
## Como executar
 
### Compilar

javac Fracoes.java

### Executar

java Fracoes < frac.txt
 
### Exemplos

Entrada:   Saida:
14/3    -> 4 2/3
3/8     -> 3/8
4/8     -> 1/2
5       -> 5
10/0    -> ERR
48/12   -> 4
 
## Observacoes
 
- Numeros inteiros assumem denominador 1.
- Divisoes exatas imprimem apenas o inteiro.
- Fracao imprópria vira numero misto (ex: 4 2/3).
- Divisao por zero imprime ERR.
- Entradas invalidas imprimem ERR.
