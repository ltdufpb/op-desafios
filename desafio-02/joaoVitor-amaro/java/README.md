# Desafio 2 - Números primos

## Solução

Foi utilizado o Crivo de Eratóstenes para todos os números primos
até 10000. O crivo inicializa um array booleano marcando todos os índices como
verdadeiro e itera marcando os múltiplos de cada primo como falso,
resultando em uma consulta O(1) para verificar se um número é primo.
## executar

```bash
javac java.Primos.java
java java.Primos
```

Foi utilizado o Java 21.