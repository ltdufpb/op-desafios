import sys


def tokenizar(expr):
    tokens = []
    i = 0
    while i < len(expr):
        if expr[i].isspace():
            i += 1
        elif expr[i] in '+-*/^()':
            tokens.append(expr[i])
            i += 1
        elif expr[i].isdigit():
            num = ''
            while i < len(expr) and expr[i].isdigit():
                num += expr[i]
                i += 1
            tokens.append(int(num))
        else:
            raise SyntaxError()
    return tokens


def aplicar_op(op, b, a):
    if op == '+':
        return a + b
    if op == '-':
        return a - b
    if op == '*':
        return a * b
    if op == '/':
        if b == 0:
            raise ZeroDivisionError()
        return int(a / b)
    if op == '^':
        return int(a ** b)


def avaliar(expr):
    tokens = tokenizar(expr)
    if not tokens:
        return None

    valores = []
    ops = []
    prec = {'+': 1, '-': 1, '*': 2, '/': 2, '^': 3}

    
    espera_operando = True

    for token in tokens:
        if isinstance(token, int):
            if not espera_operando:
                raise SyntaxError()
            valores.append(token)
            espera_operando = False

        elif token == '(':
            if not espera_operando:
                raise SyntaxError()
            ops.append(token)
            espera_operando = True

        elif token == ')':
            if espera_operando:
                raise SyntaxError()
            while ops and ops[-1] != '(':
                if len(valores) < 2:
                    raise SyntaxError()
                valores.append(aplicar_op(ops.pop(), valores.pop(), valores.pop()))
            if not ops or ops[-1] != '(':
                raise SyntaxError()
            ops.pop()
            espera_operando = False

        elif token in prec:
            if espera_operando:
                raise SyntaxError()
            while (ops and ops[-1] != '(' and
                   (prec[ops[-1]] > prec[token] or
                    (prec[ops[-1]] == prec[token] and token != '^'))):
                if len(valores) < 2:
                    raise SyntaxError()
                valores.append(aplicar_op(ops.pop(), valores.pop(), valores.pop()))
            ops.append(token)
            espera_operando = True

    if espera_operando:
        raise SyntaxError()

    while ops:
        op = ops.pop()
        if op == '(' or len(valores) < 2:
            raise SyntaxError()
        valores.append(aplicar_op(op, valores.pop(), valores.pop()))

    if len(valores) != 1:
        raise SyntaxError()

    return valores[0]


def main():
    nome_arq = sys.argv[1] if len(sys.argv) > 1 else 'd14.txt'

    try:
        with open(nome_arq, 'r', encoding='utf-8') as f:
            linhas = f.readlines()
    except FileNotFoundError:
        print(f"Erro: arquivo '{nome_arq}' não encontrado.")
        return

    for linha in linhas:
        texto = linha.strip()
        if not texto:
            continue

        try:
            res = avaliar(texto)
            if res is not None:
                print(res)
        except ZeroDivisionError:
            print('ERR DIVBYZERO')
        except SyntaxError:
            print('ERR SYNTAX')


if __name__ == '__main__':
    main()