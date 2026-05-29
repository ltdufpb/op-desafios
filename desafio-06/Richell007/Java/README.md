# Desafio 06 - Anagramas
 
## Descricao
 
Programa que encontra todos os anagramas possiveis de uma expressao (palavra ou frase),
usando apenas palavras validas do arquivo words.txt.
 
## Linguagem
 
Java (OpenJDK 17)
 
## Como executar
 
### Compilar

javac Anagrama.java

 
### Executar

java Anagrama "<expressao>"

 
### Exemplos

java Anagrama "cobra"
# Saida:
# BARCO
# COBRA
 
java Anagrama "oi gente"
# Saida:
# EGO I NET
# EGO I TEN
# ...

 
## Observacoes
 
- Apenas letras de A a Z sao aceitas (espacos sao ignorados).
- Caracteres invalidos (numeros, acentos, pontuacao) encerram o programa com erro.
- O arquivo words.txt deve estar no mesmo diretorio da execucao.
- O arquivo words.txt nao esta incluido no repositorio.
