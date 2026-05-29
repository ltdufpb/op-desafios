# Desafio 09 - Big Base
 
## Descricao
 
Programa que converte numeros entre bases de 2 a 62,
lendo as entradas de um arquivo texto.
 
## Linguagem
 
Java (OpenJDK 17)
 
## Como executar
 
### Compilar
 
javac BigBase.java
 
### Executar

java BigBase <caminho-do-arquivo>

### Exemplo

java BigBase baseconv.txt

 
### Entrada
 

10 16 1500
36 10 GOODBYE
2 10 1234

 
### Saida
 

5DC
36320638406
???

 
## Observacoes
 
- Converte entre qualquer base de 2 a 62.
- Digitos usados: 0-9, A-Z, a-z (z = 61).
- Imprime ??? para base invalida, numero negativo, numero invalido ou numero muito grande.
- Limite superior: zzzzzzzzzzzzzzzzzzzzzzzzzzzzzz em base 62.
