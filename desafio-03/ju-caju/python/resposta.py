inicio = int(input("Digite o número inicial: "))
fim = int(input("Digite o número final: "))


def eh_palindromo(numero):
    original = numero
    invertido = 0

    while numero > 0:
        digito = numero % 10
        invertido = invertido * 10 + digito
        numero = numero // 10

    return original == invertido


for x in range(inicio, fim + 1):
    if eh_palindromo(x):
        print(x)
