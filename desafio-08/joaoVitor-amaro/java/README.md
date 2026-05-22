# Desafio 08 - Fração simples

## Solução

O desafio foi feito lendo linha por linha do 
arquivo .txt, em seguida separa os numeros em 
numerador e denominador por meio de um delimitador
para fazer o calculo, se o resto for zero a
divisão é exata e retona somente a parte inteira,
caso contrário verifica se o resto é maior que 0, pois
vai conter a parte inteira e parte da fração, onde o denominador
é calculado por meio do mdc.

# Executar

```
javac Fracao.java
java Fracao frac.txt
```

## Versão
Java 21
