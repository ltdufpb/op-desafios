# Desafio 07 - Tac
 
## Descricao
 
Implementacao do comando `tac` do Unix, que exibe um arquivo texto
com as linhas invertidas (da ultima para a primeira).
 
## Linguagem
 
Java (OpenJDK 17)
 
## Como executar
 
### Compilar

javac Tac.java
 
### Executar

java Tac <caminho-do-arquivo>

### Exemplo

java Tac arquivo.txt

## Observacoes
 
- Le o arquivo em blocos de 4096 bytes, sem carregar tudo na memoria.
- Funciona com arquivos de qualquer tamanho dentro do limite de 512MB de memoria.
 
