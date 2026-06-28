import sys

DIGITOS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
LIMITE_MAXIMO = (62 ** 30) - 1

def converte_para_base_saida(base10, base_saida):
    if base10 == 0:
        return "0"
    
    result = ""
    while base10 > 0:
        resto = base10 % base_saida
        result = DIGITOS[resto] + result
        base10 = base10 // base_saida
    return result

def converte_para_base10(numero_str, base_entrada):
    if numero_str.startswith("-"):
        raise ValueError("Número negativo")
        
    base10 = 0
    for char in numero_str:
        if char not in DIGITOS:
            raise ValueError("Caractere inválido")
        valor = DIGITOS.index(char)
        if valor >= base_entrada:
            raise ValueError("Dígito inválido para a base")
        base10 = base10 * base_entrada + valor
    return base10

def processa_linha(linha):
    try:
        partes = linha.split()
        if len(partes) != 3:
            raise ValueError("Formato inválido")
            
        base_entrada = int(partes[0])
        base_saida = int(partes[1])
        numero_entrada = partes[2]
        
        if not (2 <= base_entrada <= 62) or not (2 <= base_saida <= 62):
            raise ValueError("Base inválida")
            
        base10 = converte_para_base10(numero_entrada, base_entrada)
        
        if base10 > LIMITE_MAXIMO:
            raise ValueError("Número muito grande")
            
        return converte_para_base_saida(base10, base_saida)
    except ValueError:
        return "???"

import sys

def main():
    if len(sys.argv) < 2:
        print("Erro, sintaxe: python main.py nomedoarquivo")
        return

    try:
        with open(sys.argv[1], "r") as arq:
            for linha in arq:
                linha = linha.strip()
                if not linha:
                    continue
                resultado = processa_linha(linha)
                print(resultado)
    except IOError:
        print("Erro ao abrir o arquivo")

if __name__ == "__main__":
    main()
