import sys


def carregar_regras(nome_arquivo):
  regras = []
  try:
    with open(nome_arquivo, 'r', encoding='utf-8') as f:
      for linha in f:
        linha = linha.split(';')[0].strip()
        if not linha:
          continue
        partes = linha.split()
        if len(partes) == 5:
          regras.append(partes)
  except FileNotFoundError:
    return None
  return regras


def buscar_regra(regras, estado_atual, simbolo_atual):
  simb_comp = '_' if simbolo_atual == ' ' else simbolo_atual

  melhor_regra = None
  melhor_peso = -1

  for r in regras:
    est_regra, simb_regra = r[0], r[1]

    if est_regra != '*' and est_regra != estado_atual:
      continue
    if simb_regra != '*' and simb_regra != simb_comp:
      continue

    peso = 0
    if est_regra != '*':
      peso += 2
    if simb_regra != '*':
      peso += 1

    if peso > melhor_peso:
      melhor_peso = peso
      melhor_regra = r
      if peso == 3:
        break

  return melhor_regra


def simular_turing(regras, entrada):
  if regras is None:
    return 'ERR'

  fita = list(entrada) if entrada else [' ']
  pos = 0
  estado = '0'

  while True:
    simbolo_atual = fita[pos]
    regra = buscar_regra(regras, estado, simbolo_atual)

    if not regra:
      return 'ERR'

    _, _, novo_simb, direcao, novo_estado = regra

    if novo_simb != '*':
      fita[pos] = ' ' if novo_simb == '_' else novo_simb

    if direcao == 'r':
      pos += 1
      if pos == len(fita):
        fita.append(' ')
    elif direcao == 'l':
      if pos == 0:
        fita.insert(0, ' ')
      else:
        pos -= 1
    elif direcao != '*':
      return 'ERR'

    estado = novo_estado
    if estado.startswith('halt'):
      break

  return ''.join(fita).strip()


def main():
  arq_dados = sys.argv[1] if len(sys.argv) > 1 else 'datafile'

  try:
    with open(arq_dados, 'r', encoding='utf-8') as f:
      linhas = f.readlines()
  except FileNotFoundError:
    print(f"Erro: arquivo '{arq_dados}' não encontrado.")
    return

  cache_regras = {}

  for linha in linhas:
    linha = linha.strip()
    if not linha:
      continue

    partes = linha.split(',', 1)
    if len(partes) != 2:
      continue

    arq_tur = partes[0].strip()
    entrada = partes[1].strip()

    if arq_tur not in cache_regras:
      cache_regras[arq_tur] = carregar_regras(arq_tur)

    resultado = simular_turing(cache_regras[arq_tur], entrada)
    print(f'{arq_tur},{entrada},{resultado}')


if __name__ == '__main__':
  main()