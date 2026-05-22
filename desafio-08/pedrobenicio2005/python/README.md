Os Programadores - Desafio 08: Frações e Números Mistos

O programa lê um arquivo de entrada (uma fração por linha) e aplica as seguintes regras:

1 Frações Próprias: Simplificadas até a menor forma (exemplo: 5/25 vira 1/5).

2 Frações Impróprias: Convertidas para números mistos (exemplo: 14/3 vira 4 2/3).

3 Divisões Exatas: Retornam apenas o número inteiro (exemplo: 2/1 vira 2).

4 Inteiros Puros: Mantidos exatamente como estão (exemplo: 120 vira 120).

5 Erros ou Divisão por Zero: Retornam a string ERR.

Nota: Não foi utilizada nenhuma biblioteca nativa de frações (como o módulo fractions do Python), cumprindo a exigência do desafio.

A solução foi desenvolvida em Python 3 utilizando o Algoritmo de Euclides para calcular o MDC, garantindo eficiência na simplificação dos números. O script recebe o caminho do arquivo de texto diretamente via argumento de linha de comando.

Como Executar:

Ter o Python 3 instalado no sistema operacional.
Ter o arquivo de texto com as frações (exemplo: frac.txt) disponível no disco.

Passo a Passo
Abra o terminal (PowerShell, Prompt de Comando ou Bash) na pasta onde o script está salvo.

Execute o comando passando o caminho completo ou relativo do seu arquivo de texto como argumento:

python desafio08.py "C:\Caminho\Ate\Seu\Arquivo\frac.txt"

Exemplo de Entrada e Saída
Entrada (frac.txt) -> Saída no Terminal
14/3 -> 4 2/3
5/25 -> 1/5
2/1 -> 2
120 -> 120
15/0 -> ERR
