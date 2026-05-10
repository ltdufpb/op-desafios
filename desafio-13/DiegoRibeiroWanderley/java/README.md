# Passeio do Cavalo

Esse programa recebe uma posição inicial no tabuleiro de xadrez e encontra um caminho onde o cavalo visita todas as 64 casas exatamente uma vez.

O passeio do cavalo é um problema clássico onde a peça do cavalo, seguindo suas regras de movimento em "L", deve percorrer todas as casas do tabuleiro sem repetir nenhuma.

# Como funciona

1. O programa recebe a posição inicial no formato de xadrez, como e4, e marca essa casa como visitada
2. A partir da posição atual, calcula todos os movimentos possíveis do cavalo (em "L": 2 casas em uma direção e 1 na perpendicular)
3. Os movimentos são ordenados pela Regra de Warnsdorff: o cavalo prefere ir para a casa que tem menos saídas disponíveis, o que reduz drasticamente o espaço de busca
4. Para cada movimento possível, o algoritmo tenta avançar recursivamente
5. Se chegar em um beco sem saída, desfaz o último movimento e tenta outro caminho (backtracking)
6. Quando a lista de movimentos atinge 64, todas as casas foram visitadas e o passeio está completo
7. Imprime cada posição visitada na ordem em que foi percorrida

# Tecnologias utilizadas

Linguagem: Java

# Execução

Para rodar o programa, basta seguir os seguintes passos:

1. Esteja no diretório onde a classe se encontra 
2. Rode no terminal com uma posição inicial no formato de xadrez:

```bash
$ java MeuPrograma.java e4
```
# Saída

Ao executar o programa você verá a sequência de 64 posições visitadas pelo cavalo.

Ex.: Se a posição inicial for e4:
```text
e4
d2
c4
b2
...

