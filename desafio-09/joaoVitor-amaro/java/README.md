# Desafio - Big base!

## Solução
O programa converte números entre as bases 2 e 62
usando a base 10 como intermediária. Foi 
utilizado multiplicações para converter 
qualquer base em decimal, pegando cada digito 
a sua esquerda e multiplicando pela base, em seguida
acumulando o resultado e divisões para converter 
de decimal para base, onde O número sucessivamente é dividido e 
cada resto vira o digito que será acumulado do resultado
final. Retorna ??? caso seja numeros negativos ou invalidos.

## Executar
```bash
javac BigBaseConverter.java
java BigBaseConverter entradas.txt