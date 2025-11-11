## 1. Notação de Grafo de Fluxo e Caminhos Básicos

![Grafo de Fluxo do método verificarUsuario](./Imagens/grafo_fluxo.png)

**CAMINHOS BÁSICOS:**
CAMINHO 1 = 1; 2; 3; 4; 6.
CAMINHO 2 = 1; 2; 3; 6.
CAMINHO 3 = 1; 2; 5; 6.

## 2. Cálculo da Complexidade Ciclomática

O cálculo da complexidade ciclomática foi baseado no grafo de fluxo acima, utilizando a fórmula V(G) = E - N + 2P.

E (Arestas): 7

N (Nós): 6

P (Componentes): 1

Cálculo: V(G) = 7 - 6 + (2 * 1) V(G) = 1 + 2 V(G) = 3

