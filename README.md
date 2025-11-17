# Projeto-Back-End-Mais-Agua-Para-Nosso-Povo
Desafio de estudo diretamente tirado de Usina Pernambucana de Inovação do programa Desafios Day.

---

## Público alvo:
O público-alvo é em sua maioria constituída por agricultores familiares do município de Jupi/PE, logo todos são moradores da rural do referido município. 

## Solução esperada:
Através do sistema, esperamos que ao considerar o número de pessoas de cada núcleo familiar haja uma distribuição igualitária, logo, o sistema deverá calcular o número de pessoas residentes na casa x a quantidade de água suficiente para o consumo daquela casa. Após isso, o sistema deverá considerar a capacidade em litros de cada cisterna daquele núcelo familiar. Logo, saberemos com base na capacidade uma previsão de quando será necessário enviar água. 

É de fundamental importância que o sistema contenha as datas em que cada família recebeu água, uma previsão do novo fornecimento, dados de cada família (nome, idade, se há algum membro familiar acamado) e informações no sistema se o núcleo familiar tem sistema de captação de água por calhas. 

---

## Informações complementares:
A Secretaria Municipal de Agricultura, Meio Ambiente e
Desenvolvimento Econômico de Jupi/PE tem feito um georreferenciamento das cisternas da zona rural, atrelado a isto, temos feito um recadastramento com todos os beneficiários que recebem água da zona rural. A ação tem como finalidade criar um banco de dados na secretaria contendo: nome, coordenada
geográfica de cada cisterna e capacidade de cada uma delas em litros, quantidade de moradores por residência, ausência ou presença de calhas, presença de pessoas acamadas, entre outras informações.

O intuito é planejar de modo mais eficiente a distribuição de água
considerando o consumo de cada núcleo familiar e obtendo informações sobre
os envios de água e a previsão de novas remessas, em resumo.

![contexto](assets/contexto.png)


[link-do-desafio](https://desafios.pe.gov.br/challenge?url=mais-agua-para-nosso-povo-1)

---

## Modelagem do banco

```mermaid
erDiagram
    FAMILIA {
        int id_familia PK
        string endereco
        boolean possui_captacao_calhas
        date ultima_entrega
        date proxima_previsao
        decimal latitude
        decimal longitude
    }

    MEMBRO {
        int id_membro PK
        string nome
        int idade
        boolean acamado
        int id_familia FK
    }

    CISTERNA {
        int id_cisterna PK
        int capacidade_litros
        int nivel_atual
        int id_familia FK
    }

    DISTRIBUICAO {
        int id_distribuicao PK
        date data_entrega
        int quantidade_litros
        date previsao_proxima
        string observacoes
        int id_familia FK
    }

    FAMILIA ||--|{ MEMBRO : "tem (1..*)"
    FAMILIA ||--|{ CISTERNA : "possui (0..*)"
    FAMILIA ||--|{ DISTRIBUICAO : "recebe (0..*)"
```

